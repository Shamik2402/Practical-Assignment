import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private authenticationUrl: string;

  constructor(private http: HttpClient) {
    this.authenticationUrl = `${environment.apiUrl}/authenticate`;
  }

  authenticateUser(user: User) {
    return this.http.post(this.authenticationUrl, user);
 }
}
