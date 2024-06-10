import { UserService } from '../../services/user.service';
import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterDTO } from '../../dtos/user/register.dto';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  @ViewChild('registerForm') registerForm !: NgForm;
  //Khai báo biến
  phoneNumber: string;
  password: string;
  retypePassword: string;
  fullName: string;
  address: string;
  isAccepted: boolean;
  dateOfBirth: Date;

  constructor(private router: Router, private userService: UserService) {
    this.phoneNumber = '';
    this.password = '';
    this.retypePassword = '';
    this.fullName = '';
    this.address = '';
    this.isAccepted = true;
    this.dateOfBirth = new Date();
    this.dateOfBirth.setFullYear(this.dateOfBirth.getFullYear() - 18)
  }

  onPhoneChange(){
    console.log('Phone type: ' + this.phoneNumber);
  }

  register(){
    const message = `phone: ${this.phoneNumber}`+
    `password: ${this.password}` +
    `retypePassword: ${this.retypePassword}` +
    `address: ${this.address}`+
    `fullName: ${this.fullName}` +
    `isAccepted: ${this.isAccepted}` +
    `dateOfBirth: ${this.dateOfBirth}`
    // alert(message);


    const registerDto:RegisterDTO ={
      "fullname" : this.fullName,
      "phone_number" : this.phoneNumber,
      "address" : this.address,
      "password" : this.password,
      "retype_password" : this.retypePassword,
      "date_of_birth" : this.dateOfBirth,
      "facebook_account_id" : 0,
      "google_account_id" : 0,
      "role_id" : 1
    }
    this.userService.register(registerDto).subscribe({
      next: (response: any) => {
        debugger
        if(response && (response.status === 200 || response.status ===201)){
          //Đăng ký thành công
          this.router.navigate([
            '/login'
          ]);
        }else{

        }
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        debugger;
          alert(`Không thể tạo tài khoản, ${error.error};
          }`);

        }
      }
    );
    // this.http.post(apiUrl, registerData, {headers})
    // .subscribe({

    // );
  }

  checkPasswordMatch(){
    if(this.password !== this.retypePassword){
      this.registerForm.form.controls['retypePassword'].setErrors({'passwordMissMatch' : true});
    }else{
      this.registerForm.form.controls['retypePassword'].setErrors(null);
    }
  }

  checkAge(){
    const today = new Date();
    const birthDate = new Date(this.dateOfBirth);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() -birthDate.getMonth();
    if(monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())){
      age-- ;
    }
    console.log(age);
    if(age < 18){
      this.registerForm.form.controls['dateOfBirth'].setErrors({'invalidAge' : true})
    }else{
      this.registerForm.form.controls['dateOfBirth'].setErrors(null);
    }

  }
}
