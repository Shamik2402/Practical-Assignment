import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginPageComponent } from './login-page.component';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from '../service/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../model/user';

describe('LoginPageComponent', () => {
  let component: LoginPageComponent;
  let cookieService: CookieService;
  let router: Router;
  let fixture: ComponentFixture<LoginPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [FormsModule,CommonModule, LoginPageComponent, HttpClientModule, RouterTestingModule],
      providers: [CookieService,LoginService,Router,UserService, HttpClient]
    }).compileComponents();
    fixture = TestBed.createComponent(LoginPageComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    cookieService = TestBed.inject(CookieService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('check login functionality', ()=>{
    let user: User = {
      username: "",
      password: ""
    };
    
  })
});


