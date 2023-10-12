import { TestBed } from '@angular/core/testing';

import { StoryService } from './story.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Story } from '../model/story';
import { environment } from 'src/environment/environment';

describe('StoryServiceService', () => {
  let service: StoryService;
  let http: HttpClient;
  let httpController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [StoryService]
    });
    service = TestBed.inject(StoryService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all stories', ()=>{
    let story = {
      title: "This is sample story",
      description: "description"
    };
    service.getAllStories().subscribe((data)=>{
      console.log(data);
      expect(data).toEqual(story);
    });

    const req = httpController.expectOne(`${environment.apiUrl}/stories`);
    expect(req.request.method).toEqual("GET");
    req.flush(story);
  });

  it('should delete story by id', ()=>{
    let deletedStory = {
      id: 1,
      title: "Story to delete"
    }
    service.deleteStoryById(deletedStory.id.toString()).subscribe((data)=>{
      expect(data).toEqual(deletedStory);
    });
    const req = httpController.expectOne(`${environment.apiUrl}/stories/1`);
    expect(req.request.method).toEqual('DELETE');
    req.flush(deletedStory);
  });

  it('should create story', ()=>{
    let story: Story =  {
      title: "",
      description: '',
      status: {
        statusName: "",
        statusId: 0
      },
      priority: {
        priorityLevel: "",
        priorityId: 0
      },
      type: {
        storyType: "",
        storyTypeId: 0
      },
      createdDate: "",
      createdBy: {
        id: 0,
        password: '',
        reportsTo: '',
        role: {
          id: 0,
          role: ''
        },
        team: {
          id: 0,
          name: ''
        },
        username: ''
      },
      assignedTo: {
        id: 0,
        password: '',
        reportsTo: '',
        role: {
          id: 0,
          role: ''
        },
        team: {
          id: 0,
          name: ''
        },
        username: ''
      },
      team: {
        id: 0,
        name: ''
      }
    }
    service.createStory(story).subscribe((data)=>{
      expect(data).toEqual(story);
    })
    const req = httpController.expectOne(`${environment.apiUrl}/stories`);
    expect(req.request.method).toEqual('POST');
    req.flush(story);
  });

  it('should get story by its Id', ()=>{
    let story = {
      id: 1,
      title: "Sample Story",
      description: "Handle this bug"
    };
    service.getStoryById(story.id.toString()).subscribe((data)=>{
      expect(data).toEqual(story);
    })
    const req = httpController.expectOne(`${environment.apiUrl}/stories/1`);
    expect(req.request.method).toEqual('GET');
    req.flush(story);
  });

  it('should update story by story id', ()=>{
    let story: Story =  {
      title: "",
      description: '',
      status: {
        statusName: "",
        statusId: 0
      },
      priority: {
        priorityLevel: "",
        priorityId: 0
      },
      type: {
        storyType: "",
        storyTypeId: 0
      },
      createdDate: "",
      createdBy: {
        id: 0,
        password: '',
        reportsTo: '',
        role: {
          id: 0,
          role: ''
        },
        team: {
          id: 0,
          name: ''
        },
        username: ''
      },
      assignedTo: {
        id: 0,
        password: '',
        reportsTo: '',
        role: {
          id: 0,
          role: ''
        },
        team: {
          id: 0,
          name: ''
        },
        username: ''
      },
      team: {
        id: 0,
        name: ''
      }
    };
    var storyToUpdate = 1;
    service.updateStoryById(storyToUpdate.toString(),story).subscribe((data)=>{
      expect(data).toEqual(story);
    });
    const req = httpController.expectOne(`${environment.apiUrl}/stories/` + storyToUpdate.toString());
    expect(req.request.method).toEqual('PUT');
    req.flush(story);
  });

  it('should return all stories by team', ()=>{
    var team = "Bose";
    let story = {
      id: 1,
      title: "Sample story",
      description: "This is description of sample story"
    };
    service.getAllStoriesByTeam(team).subscribe((data)=>{
      console.log(data);
      expect(data).toEqual(story);
    });
    const req = httpController.expectOne(`${environment.apiUrl}/stories/team?team=` + team);
    expect(req.request.method).toEqual('GET');
    req.flush(story);
  });
  afterEach(() => {
      httpController.verify();
    });
});