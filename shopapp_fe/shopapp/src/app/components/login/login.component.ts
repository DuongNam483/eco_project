import { TokenService } from './../../services/token.service';
import { Component, ViewChild } from '@angular/core';
import { LoginDTO } from '../../dtos/user/login.dto';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { LoginResponse } from '../../responses/user/LoginResponse';
import { RoleService } from '../../services/role.service';
import { Role } from '../../models/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  @ViewChild('registerForm') loginForm !: NgForm;

  phoneNumber: string = '';
  password: string = '';

  roles: Role[] = [];
  rememberMe: boolean = true;
  selectedRole: Role | undefined;

  constructor(private router: Router,
    private userService: UserService,
    private tokenService: TokenService,
    private roleService: RoleService
  ){}

  ngOnInit() {
    debugger
    this.roleService.getRoles().subscribe({
      next: (roles: Role[]) => {
        debugger
        this.roles = roles;
        this.selectedRole = roles.length > 0 ? roles[0] : undefined;
      },
      error: (error: any) => {
        debugger
        console.error('Error getting roles', error);
      }
    })
  }

  onPhoneChange(){
    console.log('Phone type: ' + this.phoneNumber);
  }

  login(){
    const message = `phone: ${this.phoneNumber}`+
    `password: ${this.password}`;
    // alert(message);
    debugger
    const loginDTO: LoginDTO ={
      phone_number : this.phoneNumber,
      password : this.password,
      role_id: this.selectedRole?.id?? 1
    }
    this.userService.login(loginDTO).subscribe({
      next: (response: LoginResponse) => {
        debugger
        const {token} = response;
        if(this.rememberMe){
          this.tokenService.setToken(token)
        }
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        debugger;
          alert(`Không thể đăng nhập, ${error.error.message}`);
        }
      }
    );
    // this.http.post(apiUrl, registerData, {headers})
    // .subscribe({

    // );
  }

  // checkPasswordMatch(){
  //   if(this.password !== this.retypePassword){
  //     this.registerForm.form.controls['retypePassword'].setErrors({'passwordMissMatch' : true});
  //   }else{
  //     this.registerForm.form.controls['retypePassword'].setErrors(null);
  //   }
  // }
}
