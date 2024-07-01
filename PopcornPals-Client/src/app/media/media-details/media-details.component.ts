import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Media } from '../../models/media';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { Studio } from '../../models/studio';

@Component({
  selector: 'app-media-details',
  standalone: true,
  templateUrl: './media-details.component.html',
  styleUrls: ['./media-details.component.css'],
  imports: [ MaterialModule ]
})
export class MediaDetailsComponent implements OnInit {
  media!: Media;
  id!: string;

  constructor(private mediaService: MediaService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadMedia();
  }

  loadMedia() {
    this.mediaService.getSingleMedia(parseInt(this.id)).subscribe( (data: Media) => {
      this.media = data;
      this.loadStudioOfMedia();
      console.log(data);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  loadStudioOfMedia() {
    this.mediaService.getStudioOfMedia(parseInt(this.id)).subscribe( (data: Studio) => {
      this.media.studio = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  updateMedia() {

  }

  deleteMedia() {

  }
}
