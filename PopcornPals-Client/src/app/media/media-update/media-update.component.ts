import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Studio } from '../../models/studio';
import { MediaService } from '../../services/media.service';
import { StudioService } from '../../services/studio.service';
import { Genre } from '../../models/genre';
import { MediaType } from '../../models/mediaType';
import { Media } from '../../models/media';
import { ActivatedRoute, Router } from '@angular/router';
import { title } from 'process';

@Component({
  selector: 'app-media-update',
  standalone: true,
  templateUrl: './media-update.component.html',
  styleUrls: ['./media-update.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class MediaUpdateComponent implements OnInit {
  studioControl = new FormControl<Studio>({});
  mediaForm: FormGroup = this.formBuilder.group({
    title: ['', Validators.required],
    type: ['', Validators.required],
    genre: ['', Validators.required],
    description: ['', Validators.required],
    length: ['', Validators.required],
    releaseDate: []
  });
  
  id!: string;
  media!: Media;
  studio!: Studio;

  studioList: Studio[] = [];
  mediaTypes = Object.values(MediaType).filter(value => typeof value === 'string');
  genres = Object.values(Genre).filter(value => typeof value === 'string');

  constructor(private formBuilder: FormBuilder,
              private mediaService: MediaService,
              private studioService: StudioService, 
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.studioList = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadMedia();
    this.setForm();
  }

  loadMedia() {
    this.mediaService.getSingleMedia(parseInt(this.id)).subscribe((data: Media) => {
      this.media = data;
      console.log(data);
      this.loadStudioOfMedia();
      this.loadStudios();
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  loadStudioOfMedia() {
    this.mediaService.getStudioOfMedia(parseInt(this.id)).subscribe( data => {
      this.studio = data;
      console.log(data);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  loadStudios() {
    this.studioService.getStudios(0, 5, '').subscribe((data: any) => {
      this.studioList = data.content;
    });
  }

  setForm() {
    this.mediaForm.patchValue({
      title: this.media.title,
      type: this.media.type,
      genre: this.media.genre,
      description: this.media.description,
      length: this.media.length,
      releaseDate: this.media.releaseDate
    });
    if(this.studio) {
      this.studioControl.setValue(this.studio);
    }
  }

  updateMedia() {
    this.mediaService.updateMedia(this.id, this.mediaForm.value).subscribe((data) => {
      this.router.navigate([`api/media`]);
      if(this.studioControl.value != this.media.studio && this.studioControl.value?.id) {
        this.mediaService.assignStudio(this.id, this.studioControl.value.id).subscribe();
      }
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
