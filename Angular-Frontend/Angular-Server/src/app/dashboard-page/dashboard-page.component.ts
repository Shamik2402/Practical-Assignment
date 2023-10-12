import { Component, Injectable, OnInit, ViewChild } from '@angular/core';
import { StoryService } from '../service/story.service';
import { MatTableModule, MatTable, MatTableDataSource } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { Router, RouterModule } from '@angular/router';
import { Story } from '../model/story';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { CommonModule } from '@angular/common';
import { UserService } from '../service/user.service';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.scss'],
  standalone: true,
  imports: [MatButtonModule, MatTableModule, MatIconModule, MatDialogModule, 
    RouterModule, SidebarComponent, CommonModule, MatPaginatorModule, MatSortModule, 
    MatFormFieldModule, MatInputModule, FormsModule],
})

@Injectable({
  providedIn: 'root',
})

export class DashboardPageComponent implements OnInit {

  stories: any;
  datasource: any;
  filterValue: string = '';
  displayedColumns: string[] = ['Id', 'Title', 'Description', 'Status', 'Priority', 'Story Type', 
  'Created On','Created By','Assigned To', 'Options'];

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  constructor(private storyService: StoryService, private cookie: CookieService) { }


  ngOnInit(): void {
    let team = this.cookie.get("team");
    this.storyService.getAllStoriesByTeam(team).subscribe((data: any) => {
      this.stories = data;
      this.datasource = new MatTableDataSource<Story>(this.stories);
      console.log(this.datasource);
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
      this.datasource.sortingDataAccessor = (item:any, property:any) => {
        // Handle nested properties
        const nestedProperties = property.split('.');
        let value = item;
        for (const prop of nestedProperties) {
          if (value.hasOwnProperty(prop)) {
            value = value[prop];
          } else {
            return '';
          }
        }
        return value;
      };
    });
  }

  Filterchange() {
    this.datasource.data = this.customFilter(this.stories, this.filterValue);
  }

  customFilter(data: Story[], filter: string): Story[] {
    filter = filter.toLowerCase();
    return data.filter((item: Story) => {
      // Check if the filter string exists in any of the columns you want to filter
      return (
        item.title.toLowerCase().includes(filter) ||
        item.description.toLowerCase().includes(filter) ||
        item.status.statusName.toLowerCase().includes(filter) ||
        item.priority.priorityLevel.toLowerCase().includes(filter) ||
        item.type.storyType.toLowerCase().includes(filter)
      );
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
}
