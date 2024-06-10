import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})

export class TokenService {
  private readonly TOKEN_KEY = 'access_token';
  constructor(){}
  getToken(): string | null {
    if (this.isBrowser()) {
      return localStorage.getItem(this.TOKEN_KEY);
    } else {
      // Xử lý trong môi trường không phải trình duyệt
      return null;
    }
  }
  setToken(token: string):void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }
  removeToken():void {
    localStorage.removeItem(this.TOKEN_KEY);
  }
  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
  }
}
