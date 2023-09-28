import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { Story } from '../model/story';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class StoryService {

  private storyUrl: string;
  private statusUrl: string;
  private priorityUrl: string;
  private storyTypeUrl: string;

  constructor(private http: HttpClient, private cookie: CookieService) { 
    this.storyUrl = 'http://localhost:8080/stories';
    this.statusUrl = 'http://localhost:8080/statuses';
    this.priorityUrl = 'http://localhost:8080/priorities';
    this.storyTypeUrl = 'http://localhost:8080/types';
   }

   getAllStories() {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.get(this.storyUrl,{headers: header});
   }

   deleteStoryById(id: string) {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.delete(this.storyUrl + "/" + id, {headers:header});
   }

   createStory(story: Story) {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.post(this.storyUrl, story, {headers:header});
   }

   getStoryById(id: string) {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.get(this.storyUrl + "/" + id, {headers:header});
   }

   updateStoryById(id: string, story: Story) {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.put(this.storyUrl + "/" + id, story, {headers:header});
   }

   getAllStatuses() {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.get(this.statusUrl, {headers:header});
   }

   getAllPriorities() {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.get(this.priorityUrl, {headers:header});
   }

   getAllStoryTypes() {
      let header = new HttpHeaders().set("Authorization", "Bearer " + this.cookie.get("Bearer"));
      return this.http.get(this.storyTypeUrl, {headers:header});
   }
}
