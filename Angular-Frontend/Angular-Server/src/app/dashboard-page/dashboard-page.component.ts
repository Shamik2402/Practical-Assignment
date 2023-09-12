import { Component, OnInit } from '@angular/core';
import { Story } from '../model/story';
import { StoryService } from '../service/story-service.service';

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent implements OnInit {

  stories: any;

  constructor(private storyService: StoryService) {}
  
  
  ngOnInit(): void {
    this.storyService.getAllStories().subscribe((data: any)=>{
      this.stories = data;
      console.log(this.stories);
    });
  }
}
