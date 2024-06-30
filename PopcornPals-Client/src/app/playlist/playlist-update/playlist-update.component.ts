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
import { Location } from '@angular/common';
import { Playlist } from '../../models/playlist';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-playlist-update',
  standalone: true,
  templateUrl: './playlist-update.component.html',
  styleUrls: ['./playlist-update.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class PlaylistUpdateComponent implements OnInit {
  mediaControl = new FormControl<Media[]>([]);
  playlistForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    creator: [''],
    content: this.mediaControl
  });

  id!: string;
  playlist!: Playlist;
  mediaList: Media[] = [];
  title: string = '';

  constructor(private formBuilder: FormBuilder,
              private playlistService: PlaylistService, 
              private mediaService: MediaService, 
              private router: Router, 
              private activatedRoute: ActivatedRoute, 
              private location: Location) {
    this.playlist = {};
    this.mediaList = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['playlistId'];
    this.loadPlaylist();
    this.setForm();
  }

  loadPlaylist() {
    this.playlistService.getPlaylist(parseInt(this.id)).subscribe(( data: Playlist ) => {
      console.log(data);
      this.playlist = data;
      this.playlist.content?.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      });
      this.getMedia();
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  setForm() {
    this.playlistForm.patchValue({ name: this.playlist.name });
    this.playlistForm.patchValue({ creator: this.playlist.creator?.id });
    if (this.playlist.content && this.mediaList) {
      const selectedMedia = this.playlist.content.map(selected =>
        this.mediaList.find(media => 
          media.title === selected.title && media.releaseDate.getTime() === selected.releaseDate.getTime()
        )
      ).filter((media): media is Media => media !== undefined);
      this.mediaControl.setValue(selectedMedia);
      console.log("mediacontrol:", this.mediaControl.value);
    }
  }

  getMedia() {
    this.mediaService.getMedia(this.title).subscribe((data: Media[]) => {
      this.mediaList = data;
      this.mediaList.forEach(media => {
        media.releaseDate = new Date(media.releaseDate);
      })
    });
  }

  updatePlaylist() {
    console.log(this.playlistForm.value);
    this.playlistForm.value.content = this.mediaControl.value?.map(media => media.id);
    this.playlistService.updatePlaylist(this.id, this.playlistForm.value).subscribe((data) => {
      this.location.back();
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
