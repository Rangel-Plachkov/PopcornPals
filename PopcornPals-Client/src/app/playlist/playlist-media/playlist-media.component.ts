import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Media } from '../../models/media';
import { PlaylistService } from '../../services/playlist.service';
import { ActivatedRoute } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-playlist-media',
  standalone: true,
  templateUrl: './playlist-media.component.html',
  styleUrls: ['./playlist-media.component.css'],
  imports: [ MaterialModule ]
})
export class PlaylistMediaComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['title', 'genre', 'releaseDate'];
  media: Media[] = [];
  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private playlistService: PlaylistService, private activatedRoute: ActivatedRoute) {
    this.media = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['playlistId'];
    this.loadMedia();
  }

  loadMedia() {
    this.playlistService.getContent(this.id, this.pageNo, this.pageSize).subscribe( (data: any) => {
      this.media = data.content;
      this.totalItems = data.totalElements;
      console.log(data);
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadMedia();
  }
}
