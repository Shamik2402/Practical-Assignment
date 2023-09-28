import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private authenticationUrl: string;

  constructor(private http: HttpClient) {
    this.authenticationUrl = 'http://localhost:8080/authenticate';
  }

  getJwtToken(user: User) {
    return this.http.post(this.authenticationUrl, user);
 }
}
