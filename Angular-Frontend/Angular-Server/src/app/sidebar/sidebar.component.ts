import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
  standalone: true,
  imports: [RouterModule]
})
export class SidebarComponent{

  username: string = "";

  constructor(private cookie: CookieService, private router: Router) {
    this.username = this.cookie.get("username");
  }

  logOut() {
    this.cookie.deleteAll();
    this.router.navigate(['']);
  }
}
