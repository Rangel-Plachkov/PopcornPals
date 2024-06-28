import { Component, OnInit } from '@angular/core';
import { Media } from '../../models/media';
import { ProducerService } from '../../services/producer.service';
import { ActivatedRoute } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';
import { MaterialModule } from '../../material/material.module';

@Component({
  selector: 'app-producer-media',
  standalone: true,
  templateUrl: './producer-media.component.html',
  styleUrls: ['./producer-media.component.css'], 
  imports: [ MaterialModule ]
})
export class ProducerMediaComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['title', 'genre', 'releaseDate'];
  media: Media[] = [];

  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private producerService: ProducerService, private activatedRoute: ActivatedRoute) {
    this.media = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadMedia();
  }

  loadMedia() {
    this.producerService.getMedia(this.id, this.pageNo, this.pageSize).subscribe( (data: any) => {
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
