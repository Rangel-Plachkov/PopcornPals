import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Media } from '../../models/media';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-media-list',
  standalone: true,
  templateUrl: './media-list.component.html',
  styleUrls: ['./media-list.component.css'],
  imports: [ MaterialModule ]
})
export class MediaListComponent implements OnInit {
  displayedColumns: string[] = ['title', 'genre', 'type', 'releaseDate', 'details'];
  media: Media[] = [];
  
  pageNo: number = 0;
  pageSize: number = 5;
  title: string = "";
  totalItems: number = 0;

  constructor(private mediaService: MediaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadMedia();
  }

  loadMedia() {
    this.mediaService.getMedia(this.title, this.pageNo, this.pageSize).subscribe( data => {
      this.media = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadMedia();
  }

  search() {
    this.loadMedia();
  }

  viewDetails(id: number) {
    this.router.navigate([`./${id}`], { relativeTo: this.activatedRoute });
  }
}
