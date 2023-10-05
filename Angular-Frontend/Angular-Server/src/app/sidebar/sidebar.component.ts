import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
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

  constructor(private cookie: CookieService) {
    this.username = this.cookie.get("username");
  }

  logOut() {
    this.cookie.deleteAll();
  }
}
