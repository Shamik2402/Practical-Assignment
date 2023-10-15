import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { User } from '../model/user';
import { LoginService } from '../service/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
  standalone: true,
  imports: [FormsModule, CommonModule]
})
export class LoginPageComponent implements OnInit{
  
  constructor(private cookie: CookieService, private loginService: LoginService,
    private route: ActivatedRoute, private router: Router, private userService: UserService) { }

    user: User = new User;
    token: any;
    errorMessage: any;
    users: any[] = [];
    
  ngOnInit() {
    this.route.queryParams.subscribe((params)=>{
      this.errorMessage = params;
      console.log(this.errorMessage.error);
    });

  }

  OnLogin() {
    this.cookie.set("username", this.user.username, { expires: 1 });
    this.cookie.set("password",this.user.password, {expires:1});
    this.loginService.authenticateUser(this.user)
    .pipe(
      catchError((error: any)=>{
        if(error.status === 404) {
          this.router.navigate([''], {
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
      let userOfTeam = this.cookie.get("username");
      this.userService.getUserByUsername(userOfTeam).subscribe((data:any)=>{
        this.cookie.set("team", data.team.name);
        this.router.navigate(['dashboard']);
      })
    });
  }
}
