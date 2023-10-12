import { TestBed, fakeAsync } from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { LoginService } from './login.service';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { environment } from 'src/environment/environment';

describe('LoginService', () => {
  let service: LoginService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LoginService]
    });
    service = TestBed.inject(LoginService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeDefined();
  });

  it('test authenticate api', fakeAsync(()=> {
    let user: User = {
      username: "admin",
      password: "password"
    };
    let jwt = "jwt";
    service.authenticateUser(user).subscribe((data)=>{
      expect(data).toEqual(jwt);
    });

    const req = httpController.expectOne(`${environment.apiUrl}/authenticate`);
    expect(req.request.method).toEqual('POST');
    req.flush(jwt);

  }));
});
