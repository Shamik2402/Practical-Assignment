import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardPageComponent } from './dashboard-page/dashboard-page.component';
import { CreateStoryComponent } from './create-story/create-story.component';

const routes: Routes = [
  {path: '', component: DashboardPageComponent},
  {path: 'add', component: CreateStoryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
