import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-create-story-dialog',
  templateUrl: './create-story-dialog.component.html',
  styleUrls: ['./create-story-dialog.component.scss'],
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatIconModule, RouterModule],
})
export class CreateStoryDialogComponent {

  constructor(public dialogRef: MatDialogRef<CreateStoryDialogComponent>) {}

  closeDialog() {
    this.dialogRef.close();
  }

}
