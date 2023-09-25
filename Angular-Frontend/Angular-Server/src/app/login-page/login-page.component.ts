import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit{

  constructor(private http: HttpClient,private sanitizer: DomSanitizer) {}

  loginForm: SafeHtml = '';

    ngOnInit() {
    this.http.get("http://localhost:8080/users", {responseType: 'text'}).subscribe((response)=>{
      this.loginForm = this.sanitizer.bypassSecurityTrustHtml(response);
    });
    
  }
}
