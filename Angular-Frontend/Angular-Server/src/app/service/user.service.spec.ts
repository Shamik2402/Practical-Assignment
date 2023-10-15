import { HttpClient } from "@angular/common/http";
import { UserService } from "./user.service";
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { User } from "../model/user";
import { environment } from "src/environment/environment";

describe('UserService', () => {
    let service: UserService;
    let http: HttpClient;
    let httpController: HttpTestingController;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [UserService]
      });
      service = TestBed.inject(UserService);
      http = TestBed.inject(HttpClient);
      httpController = TestBed.inject(HttpTestingController);
    });
  
    it('should be created', () => {
      expect(service).toBeTruthy();
    });
  
    it('should get all users', ()=>{
        let user: User = new User;
        service.getUsersByTeam().subscribe((data)=>{
            expect(data).toEqual(user);
        });
        const req = httpController.expectOne(`${environment.apiUrl}/user`);
        expect(req.request.method).toEqual('GET');
        req.flush(user);
    });

    it('should get user by username', ()=>{
        let user = {
            id:1,
            username: "Shamik",
            team: {
                id:1,
                name:""
            }
        };
        service.getUserByUsername(user.username).subscribe((data)=>{
            expect(data).toEqual(user);
        });
        const req = httpController.expectOne(`${environment.apiUrl}/user/username?username=` + user.username);
        expect(req.request.method).toEqual('GET');
        req.flush(user);
    })
  });