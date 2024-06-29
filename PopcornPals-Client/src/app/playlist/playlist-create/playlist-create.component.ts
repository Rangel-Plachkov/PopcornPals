import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { PlaylistService } from '../../services/playlist.service';
import { MediaService } from '../../services/media.service';
import { Media } from '../../models/media';
import { HttpErrorResponse } from '@angular/common/http';
import { Location } from '@angular/common';

@Component({
  selector: 'app-playlist-create',
  standalone: true,
  templateUrl: './playlist-create.component.html',
  styleUrls: ['./playlist-create.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor],
  providers: [provideNativeDateAdapter()]
})
export class PlaylistCreateComponent implements OnInit {
  mediaControl = new FormControl<Media[]>([]);
  playlistForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    creator: [''],
    content: this.mediaControl
  });

  id!: string;
  mediaList: Media[] = [];
  title: string = '';

  constructor(private formBuilder: FormBuilder,
              private playlistService: PlaylistService,
              private mediaService: MediaService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private location: Location) {
    this.getMedia();
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
  }

  getMedia() {
    this.mediaService.getMedia(this.title).subscribe((data: Media[]) => {
      this.mediaList = data;
      this.mediaList.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      })
    });
  }

  createPlaylist() {
    this.playlistForm.value.creator = parseInt(this.id);
    this.playlistForm.value.content = this.mediaControl.value?.map(media => media.id);
    this.playlistService.createPlaylist(this.playlistForm.value).subscribe((data) => {
      this.location.back();
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
