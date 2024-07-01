import { Component, OnInit } from '@angular/core';
import { MaterialModule } from "../../material/material.module";
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ActorService } from '../../services/actor.service';
import { NgIf, NgFor } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Media } from '../../models/media';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Actor } from '../../models/actor';

@Component({
  selector: 'app-actor-update',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './actor-update.component.html',
  styleUrl: './actor-update.component.css'
})
export class ActorUpdateComponent implements OnInit {
  mediaControl = new FormControl<Media[]>([]);
  actorForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [],
    birthdate: [],
    starsIn: this.mediaControl
  });
  
  id!: string;
  actor!: Actor;
  mediaList: Media[] = [];
  title: string = '';
  errorMessages: string[] = [];

  constructor(private formBuilder: FormBuilder, 
              private actorService: ActorService, 
              private mediaService: MediaService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
    this.actor = {};
    this.mediaList = [];
  }

  ngOnInit() : void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadActor();
    this.setForm();
  }

  loadActor() {
    this.actorService.getActor(parseInt(this.id)).subscribe(( data: Actor ) => {
      console.log(data);
      this.actor = data;
      this.actor.starsIn?.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      });
      this.getMedia();
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  setForm() {
    this.actorForm.patchValue({ name : this.actor.name });
    this.actorForm.patchValue({ description : this.actor.description });
    this.actorForm.patchValue({ birthdate : this.actor.birthdate });
    if (this.actor.starsIn && this.mediaList) {
      const selectedMedia = this.actor.starsIn.map(selected =>
        this.mediaList.find(media => 
          media.title === selected.title && media.releaseDate.getTime() === selected.releaseDate.getTime()
        )
      ).filter((media): media is Media => media !== undefined);
      this.mediaControl.setValue(selectedMedia);
      console.log("mediacontrol:", this.mediaControl.value);
    }
  }

  getMedia() {
    this.mediaService.getMedia(this.title).subscribe((data) => {
      console.log(data);
      this.mediaList = data.content;
      this.mediaList.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      });
      this.setForm();
    });
  }

  updateActor() {
    this.actorForm.value.starsIn = this.mediaControl.value?.map(media => media.id);
    console.log(this.actorForm.value);
    this.actorService.updateActor(this.id, this.actorForm.value).subscribe((data) => {
      console.log(data);
      this.router.navigate([`api/actors`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
