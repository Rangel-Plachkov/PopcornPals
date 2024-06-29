import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Media } from '../../models/media';
import { StudioService } from '../../services/studio.service';
import { ActivatedRoute } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-studio-media',
  standalone: true,
  templateUrl: './studio-media.component.html',
  styleUrls: ['./studio-media.component.css'], 
  imports: [ MaterialModule ]
})
export class StudioMediaComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['title', 'genre', 'releaseDate'];
  media: Media[] = [];
  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private studioService: StudioService, private activatedRoute: ActivatedRoute) {
    this.media = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadMedia();
  }

  loadMedia() {
    this.studioService.getMedia(this.id, this.pageNo, this.pageSize).subscribe( (data: any) => {
      console.log(data);
      this.media = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadMedia();
  }
}
