import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { MediaService } from '../../services/media.service';
import { Router } from '@angular/router';
import { Studio } from '../../models/studio';
import { StudioService } from '../../services/studio.service';
import { MediaType } from '../../models/mediaType';
import { Genre } from '../../models/genre';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-media-create',
  standalone: true,
  templateUrl: './media-create.component.html',
  styleUrls: ['./media-create.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class MediaCreateComponent implements OnInit {
  mediaForm: FormGroup = this.formBuilder.group({
    title: ['', Validators.required],
    type: ['', Validators.required],
    genre: ['', Validators.required],
    description: ['', Validators.required],
    length: ['', Validators.required],
    releaseDate: [],
    //studio: []
  });

  studioList: Studio[] = [];
  mediaTypes = Object.values(MediaType).filter(value => typeof value === 'string');
  genres = Object.values(Genre).filter(value => typeof value === 'string');

  constructor(private formBuilder: FormBuilder,
              private mediaService: MediaService,
              private studioService: StudioService,
              private router: Router) {
    this.loadStudios();
  }

  ngOnInit() {
  }

  loadStudios() {
    this.studioService.getStudios(0, 5, '').subscribe((data: any) => {
      this.studioList = data.content;
    });
  }

  createMedia() {
    //this.mediaForm.value.studio = this.mediaForm.value.studio?.id;
    console.log(this.mediaForm.value);
    this.mediaService.createMedia(this.mediaForm.value).subscribe((data) => {
      this.router.navigate([`api/media`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
