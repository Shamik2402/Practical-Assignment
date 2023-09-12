import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'

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
}
