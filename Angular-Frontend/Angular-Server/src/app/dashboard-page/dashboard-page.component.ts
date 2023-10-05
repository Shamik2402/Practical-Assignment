import { Component, Injectable, OnInit } from '@angular/core';
import { StoryService } from '../service/story.service';
import { MatTableModule, MatTable } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { Router, RouterModule } from '@angular/router';
import { Story } from '../model/story';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.scss'],
  standalone: true,
  imports: [MatButtonModule, MatTableModule, MatIconModule, MatDialogModule, RouterModule, SidebarComponent, CommonModule],
})

@Injectable({
  providedIn: 'root',
})

export class DashboardPageComponent implements OnInit {

  stories: any;
  displayedColumns: string[] = ['Id', 'Title', 'Description', 'Status', 'Priority', 'Story Type', 'Created On', 'Options'];
  constructor(private storyService: StoryService) { }


  ngOnInit(): void {
    this.storyService.getAllStories().subscribe((data: any) => {
      this.stories = data;
      console.log(this.stories);
    });
  }


  deleteStory(story_id: any) {

    if (confirm("Are you sure you want to delete?")) {
      console.log(story_id);
      this.storyService.deleteStoryById(story_id).subscribe((data: any) => {
        console.log(data);
        this.ngOnInit();
      });
    }

  }

  updateStory(story: Story) {
    console.log("story to be updated!");
    console.log(story);
  }
}
