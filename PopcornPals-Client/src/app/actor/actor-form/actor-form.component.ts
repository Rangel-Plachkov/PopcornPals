import { Component } from '@angular/core';
import { MaterialModule } from "../../material/material.module";
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ActorService } from '../../services/actor.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import { MediaService } from '../../services/media.service';
import { Media } from '../../models/media';

@Component({
  selector: 'app-actor-form',
  standalone: true,
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './actor-form.component.html',
  styleUrl: './actor-form.component.css'
})
export class ActorFormComponent {
  mediaControl = new FormControl<Media[]>([]);
  actorForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [],
    birthdate: [],
    starsIn: this.mediaControl
  });

  mediaList: Media[] = [];
  title: string = '';
  errorMessages: string[] = [];

  constructor(private formBuilder: FormBuilder, 
              private actorService: ActorService, 
              private mediaService: MediaService) {
    this.getMedia();
  }

  getMedia() {
    this.mediaService.getMedia(this.title).subscribe((data: Media[]) => {
      console.log(data);
      this.mediaList = data;
      this.mediaList.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      })
    });
  }

  createActor() {
    this.actorForm.value.starsIn = this.mediaControl.value?.map(media => media.id);
    console.log(this.actorForm.value);
    this.actorService.createActor(this.actorForm.value).subscribe((data) => {
      console.log(data);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
