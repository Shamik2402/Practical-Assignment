import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { StoryService } from '../service/story.service';
import { Story } from '../model/story';
Chart.register(...registerables);

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.scss']
})
export class ChartsComponent implements OnInit {

  statusLabels = ['To-Do', 'In-Progress', 'Completed'];
  toDoStatuses: number = 0;
  inProgressStatuses: number = 0;
  completedStatuses: number = 0;

  constructor(private story: StoryService) {}

  ngOnInit() {
    this.story.getAllStories().subscribe((data:any)=>{
      for(var i=0;i<Object.keys(data).length; i++) {
        if(data[i].status.statusName == this.statusLabels[0]) this.toDoStatuses++;
        else if(data[i].status.statusName == this.statusLabels[1]) this.inProgressStatuses++;
        else this.completedStatuses++;
      }
      this.createChart(this.statusLabels, [this.toDoStatuses,this.inProgressStatuses,this.completedStatuses], "piechart", "pie");
      this.createChart(this.statusLabels, [this.toDoStatuses,this.inProgressStatuses,this.completedStatuses], "barchart", "bar");
    });
  }

  createChart(labels:any, data:any, id: any, type: any) {
    var myChart = new Chart(id, {
      type: type,
      data: {
        labels: labels,
        datasets: [{
          label: '# of Stories',
          data: data,
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

}
