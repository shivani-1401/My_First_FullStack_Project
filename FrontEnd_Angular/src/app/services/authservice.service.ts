import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {


  constructor(private httpClient: HttpClient) { }

  authenticateUser(user: any) {
    return this.httpClient.post("http://localhost:8765/authenticationservice/auth/login", user);
  }

  setBearerToken(token: string) {
    localStorage.setItem('bearer token', token);
  }

  getBearerToken() {
    return localStorage.getItem('bearer token');
  }

  setUserId(userId: string) {
    localStorage.setItem('userId', userId);
  }

  getUserId() {
    return localStorage.getItem('userId');
  }

  isUserAuthenticated(): boolean {
    let token = localStorage.getItem('bearer token');
    if (token != undefined && token != null) {
      return true;
    }
    else {
      return false;
    }
  }
}
