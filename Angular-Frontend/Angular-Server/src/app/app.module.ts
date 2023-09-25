import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardPageComponent } from './dashboard-page/dashboard-page.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { CreateStoryComponent } from './create-story/create-story.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { CreateStoryDialogComponent } from './create-story-dialog/create-story-dialog.component';
import { UpdateStoryComponent } from './update-story/update-story.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ChartsComponent } from './charts/charts.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginPageComponent,
        ChartsComponent,
        
    ],
    providers: [DatePipe],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        DashboardPageComponent,
        CreateStoryComponent,
        MatIconModule,
        RouterModule,
        FormsModule,
        CreateStoryDialogComponent,
        UpdateStoryComponent
    ]
})
export class AppModule { }
