import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Story } from '../model/story';
import { environment } from 'src/environment/environment';


@Injectable({
  providedIn: 'root'
})
export class StoryService {

  private storyUrl: string;
  private statusUrl: string;
  private priorityUrl: string;
  private storyTypeUrl: string;

  constructor(private http: HttpClient) { 
    this.storyUrl = `${environment.apiUrl}/stories`;
    this.statusUrl = `${environment.apiUrl}/statuses`;
    this.priorityUrl = `${environment.apiUrl}/priorities`;
    this.storyTypeUrl = `${environment.apiUrl}/types`;
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
