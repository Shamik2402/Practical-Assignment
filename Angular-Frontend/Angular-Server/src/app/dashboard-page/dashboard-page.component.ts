import { Component, OnInit } from '@angular/core';
import { Story } from '../model/story';
import { StoryService } from '../service/story-service.service';
import {MatTableModule, MatTable} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.scss'],
  standalone: true,
  imports: [MatButtonModule, MatTableModule, MatIconModule],
})
export class DashboardPageComponent implements OnInit {

  stories: any;
  displayedColumns: string[] = ['Id','Title','Description','Status','Priority','Story Type', 'Created On','Options'];
  constructor(private storyService: StoryService) {}
  
  
  ngOnInit(): void {
    this.storyService.getAllStories().subscribe((data: any)=>{
      this.stories = data;
      console.log(this.stories);
    });
  }

  deleteStory() {
    var id: any;
    id = document.getElementById("story_id");
    var value = id.textContent
    console.log(typeof(value));
    this.storyService.deleteStoryById(value).subscribe((data:any)=>{
      console.log("deleted data " + data);
    });

    this.storyService.getAllStories().subscribe((data:any)=>{
      this.stories = data;
    })
  }
}
