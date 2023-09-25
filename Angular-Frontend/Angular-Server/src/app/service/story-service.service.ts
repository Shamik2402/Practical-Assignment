import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Story } from '../model/story';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StoryService {

  private storyUrl: string;
  private statusUrl: string;
  private priorityUrl: string;
  private storyTypeUrl: string;

  constructor(private http: HttpClient) { 
    this.storyUrl = 'http://localhost:8080/stories';
    this.statusUrl = 'http://localhost:8080/statuses';
    this.priorityUrl = 'http://localhost:8080/priorities';
    this.storyTypeUrl = 'http://localhost:8080/types';
   }

   getAllStories() {
      return this.http.get(this.storyUrl);
   }

   deleteStoryById(id: string) {
      return this.http.delete(this.storyUrl + "/" + id);
   }

   createStory(story: Story) {
      return this.http.post(this.storyUrl, story);
   }

   getStoryById(id: string) {
      return this.http.get(this.storyUrl + "/" + id);
   }

   updateStoryById(id: string, story: Story) {
      return this.http.put(this.storyUrl + "/" + id, story);
   }

   getAllStatuses() {
      return this.http.get(this.statusUrl);
   }

   getAllPriorities() {
      return this.http.get(this.priorityUrl);
   }

   getAllStoryTypes() {
      return this.http.get(this.storyTypeUrl);
   }
}
