import { Component, OnInit } from '@angular/core';
import { StoryService } from '../service/story.service';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { RouterModule, Routes, UrlSegment } from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Story } from '../model/story';
import { ActivatedRoute } from '@angular/router';
import { concatMap } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { CreateStoryDialogComponent } from '../create-story-dialog/create-story-dialog.component';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';

@Component({
  selector: 'app-update-story',
  templateUrl: './update-story.component.html',
  styleUrls: ['./update-story.component.scss'],
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, 
    RouterModule, MatCardModule, MatIconModule, 
    MatButtonModule, FormsModule, DatePipe, CommonModule, SidebarComponent]
})
export class UpdateStoryComponent implements OnInit {

  constructor(private storyService: StoryService, private router: ActivatedRoute,
    public dialog: MatDialog) {}

  public story: Story = new Story;
  selectedStatus: string = "";
  selectedPriority: string = "";
  selectedType: string = "";
  id: any;

  ngOnInit() {
    this.router.url.pipe(
      concatMap((UrlSegment) => {
        this.id = UrlSegment[2].path;
        return this.storyService.getStoryById(this.id);
      })
    ).subscribe((data: any) => {
      this.story.title = data.title;
      this.story.description = data.description;
      this.story.status.statusId = data.status.statusId;
      this.story.priority.priorityId = data.priority.priorityId;
      this.story.type.storyTypeId = data.type.storyTypeId;
      this.story.createdDate = data.createdDate;
      console.log(this.story);
      this.selectedStatus = this.story.status.statusId.toString();
      this.selectedPriority = this.story.priority.priorityId.toString();
      this.selectedType = this.story.type.storyTypeId.toString();
    });
  }

  updateStoryFormSubmission() {
    if(confirm("Are sure you want to save?")) {
      var status = this.story.status.statusId;
      var priority = this.story.priority.priorityId;
      var type = this.story.type.storyTypeId;
      this.selectedStatus = status.toString();
      this.selectedPriority = priority.toString();
      this.selectedType = type.toString();
      this.story.status.statusId = parseInt(status.toString());
      this.story.priority.priorityId = parseInt(priority.toString());
      this.story.type.storyTypeId = parseInt(type.toString());
      console.log(this.story);
      this.storyService.updateStoryById(this.id,this.story).subscribe((data:any)=>{
        console.log("data saved successfully");
        this.dialog.open(CreateStoryDialogComponent, {
          width: '300px',
          enterAnimationDuration:'0ms',
          exitAnimationDuration: '0ms',
        });
        console.log(data);
      });
    }
  }

}
