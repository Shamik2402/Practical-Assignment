import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { User } from '../model/user';
import { LoginService } from '../service/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { pipe, throwError } from 'rxjs';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
  standalone: true,
  imports: [FormsModule, CommonModule]
})
export class LoginPageComponent implements OnInit{
  
  constructor(private cookie: CookieService, private loginService: LoginService,
    private route: ActivatedRoute, private router: Router) { }

    user: User = new User;
    token: any;
    errorMessage: any;
    
  ngOnInit() {
    this.route.queryParams.subscribe((params)=>{
      this.errorMessage = params;
      console.log(this.errorMessage.error);
    })

  }

  OnLogin() {
    this.cookie.set("username", this.user.username, { expires: 1 });
    this.cookie.set("password", this.user.password, { expires: 1 });
    this.loginService.authenticateUser(this.user)
    .pipe(
      catchError((error: any)=>{
        if(error.status === 404) {
          this.router.navigate(['login'], {
            queryParams: {error: "Incorrect Username or Password"}
          });
        }
        return error;
      })
    )
      .subscribe((response) => {
      this.token = response;
      console.log(this.token);
      this.cookie.set("Bearer", this.token.jwt);
      this.router.navigate(['']);
    });
  }
}
