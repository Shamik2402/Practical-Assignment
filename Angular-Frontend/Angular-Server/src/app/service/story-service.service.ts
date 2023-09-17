import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Story } from '../model/story';

@Injectable({
  providedIn: 'root'
})
export class StoryService {

  private storyUrl: string;

  constructor(private http: HttpClient) { 
    this.storyUrl = 'http://localhost:8080/stories';
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
}
