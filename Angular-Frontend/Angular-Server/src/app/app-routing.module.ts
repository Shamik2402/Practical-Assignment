import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardPageComponent } from './dashboard-page/dashboard-page.component';
import { CreateStoryComponent } from './create-story/create-story.component';
import { UpdateStoryComponent } from './update-story/update-story.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ChartsComponent } from './charts/charts.component';

const routes: Routes = [
  {path: '', component: DashboardPageComponent},
  {path: 'login', component:LoginPageComponent},
  {path: 'add', component: CreateStoryComponent},
  {path: 'update/:id', component: UpdateStoryComponent},
  {path: 'charts', component: ChartsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
