import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Media } from '../../models/media';
import { ProducerService } from '../../services/producer.service';
import { NgIf, NgFor } from '@angular/common';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Producer } from '../../models/producer';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-producer-update',
  standalone: true,
  templateUrl: './producer-update.component.html',
  styleUrls: ['./producer-update.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgFor, NgIf ],
  providers: [provideNativeDateAdapter()]
})
export class ProducerUpdateComponent implements OnInit {
  mediaControl = new FormControl<Media[]>([]);
  producerForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [], 
    birthdate: [],
    producedMedia: this.mediaControl
  });

  id!: string;
  producer!: Producer;
  mediaList: Media[] = [];
  title: string = '';

  constructor(private formBuilder: FormBuilder,
              private producerService: ProducerService,
              private mediaService: MediaService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
    this.producer = {};
    this.mediaList = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadProducer();
    this.setForm();
  }

  loadProducer() {
    this.producerService.getProducer(parseInt(this.id)).subscribe((data: Producer) => {
      this.producer = data;
      this.producer.producedMedia?.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      });
      this.getMedia();
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  setForm() {
    this.producerForm.patchValue({ name: this.producer.name });
    this.producerForm.patchValue({ description: this.producer.description });
    this.producerForm.patchValue({ birthdate: this.producer.birthdate });
    if(this.producer.producedMedia && this.mediaList) {
      const selectedMedia = this.producer.producedMedia.map(selected =>
        this.mediaList.find(media =>
          media.title === selected.title //&& media.releaseDate.getTime() === selected.releaseDate.getTime()
        )
      ).filter((media): media is Media => media !== undefined);
      this.mediaControl.setValue(selectedMedia);
    }
  }

  getMedia() {
    this.mediaService.getMedia(this.title).subscribe((data) => {
      this.mediaList = data.content;
      this.mediaList.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      });
      this.setForm();
    });
  }

  updateProducer() {
    this.producerForm.value.producedMedia = this.mediaControl.value?.map(media => media.id);
    this.producerService.updateProducer(this.id, this.producerForm.value).subscribe((data) => {
      this.router.navigate([`api/producers`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
