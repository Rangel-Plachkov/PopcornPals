import { Component, OnInit } from '@angular/core';
import { MaterialModule } from "../../material/material.module";
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { Media } from '../../models/media';
import { ProducerService } from '../../services/producer.service';
import { MediaService } from '../../services/media.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-producer-create',
  standalone: true,
  templateUrl: './producer-create.component.html',
  styleUrls: ['./producer-create.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [ provideNativeDateAdapter() ]
})
export class ProducerCreateComponent implements OnInit {
  mediaControl = new FormControl<Media[]>([]);
  producerForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [],
    birthdate: [],
    producedMedia: this.mediaControl
  });

  mediaList: Media[] = [];
  title: string = '';

  constructor(private formBuilder: FormBuilder,
              private producerService: ProducerService,
              private mediaService: MediaService,
              private router: Router) {
    this.loadMedia();
  }

  ngOnInit() {
  }

  loadMedia() {
    this.mediaService.getMedia(this.title).subscribe((data: Media[]) => {
      console.log(data);
      this.mediaList = data;
      this.mediaList.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      })
    });
  }

  createProducer() {
    this.producerForm.value.producedMedia = this.mediaControl.value?.map(media => media.id);
    this.producerService.createProducer(this.producerForm.value).subscribe( data => {
      this.router.navigate([`api/producers`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
