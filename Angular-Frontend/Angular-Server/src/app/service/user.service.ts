import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl: string;

  constructor(private http: HttpClient) {
    this.userUrl = `${environment.apiUrl}/user`;
  }

  getUsersByManager() {
    return this.http.get(this.userUrl);
  }
}
