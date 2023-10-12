import { Component, OnInit } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { RouterModule, Routes } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { StoryService } from '../service/story.service';
import { FormsModule } from '@angular/forms';
import { Story } from '../model/story';
import { DatePipe } from '@angular/common';
import { MatDialog, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { CreateStoryDialogComponent } from '../create-story-dialog/create-story-dialog.component';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrls: ['./create-story.component.scss'],
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule,
    RouterModule, MatCardModule, MatIconModule,
    MatButtonModule, FormsModule, DatePipe, CommonModule, SidebarComponent, MatChipsModule]
})
export class CreateStoryComponent implements OnInit {

  story: Story = new Story;
  statuses: any;
  priorities: any;
  types: any;
  users: any[] = [];
  allUsers: any[] = [];
  selectedUserId: number = 0; // Initialize with a default value or null


  constructor(private storyService: StoryService, private datepipe: DatePipe,
    public dialog: MatDialog, private userService: UserService, private cookie: CookieService) { }

  ngOnInit() {
    this.storyService.getAllStatuses().subscribe((data: any) => {
      this.statuses = data;
    });

    this.storyService.getAllPriorities().subscribe((data: any) => {
      this.priorities = data;
    });

    this.storyService.getAllStoryTypes().subscribe((data: any) => {
      this.types = data;
    });

    this.userService.getUsersByTeam().subscribe((data: any) => {
      console.log(data);
      for(var i=0;i<Object.keys(data).length;i++)  {
        const teamKey = Object.keys(data)[i];
        this.allUsers.push(data[teamKey]);
      }
      this.allUsers.forEach((element:any) => {
        element.forEach((subuser:any) => {
          if(subuser.team.name === this.cookie.get("team")) {
            this.users.push(subuser);
          }
        });
      });
    })
  }

  createStoryFormSubmission() {

    if (confirm("Are sure you want to save?")) {
      var date = new Date();
      var status = this.story.status.statusId;
      var priority = this.story.priority.priorityId;
      var type = this.story.type.storyTypeId;
      this.story.createdDate = this.datepipe.transform(date, "yyyy-MM-dd");
      this.story.status.statusId = parseInt(status.toString());
      this.story.priority.priorityId = parseInt(priority.toString());
      this.story.type.storyTypeId = parseInt(type.toString());
      this.users.forEach((user: any) => {
        if (user.username === this.cookie.get("username")) {
          this.story.createdBy.id = user.id;
          this.story.team.id = user.team.id;
        }
      
      });
      this.story.assignedTo.id = this.selectedUserId;
      console.log(this.story);
      this.storyService.createStory(this.story).subscribe((data: any) => {
        console.log("data saved successfully");
        this.dialog.open(CreateStoryDialogComponent, {
          width: '300px',
          enterAnimationDuration: '0ms',
          exitAnimationDuration: '0ms',
        });
        console.log(data);
      });
    }
  }
}
