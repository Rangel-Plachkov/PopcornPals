import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Review } from '../../models/review';
import { PageEvent } from '@angular/material/paginator';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-media-reviews',
  standalone: true,
  templateUrl: './media-reviews.component.html',
  styleUrls: ['./media-reviews.component.css'],
  imports: [ MaterialModule ]
})
export class MediaReviewsComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['rating', 'description', 'details'];
  reviews: Review[] = [];
  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private mediaService: MediaService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadReviews();
  }

  loadReviews() {
    this.mediaService.getReviews(parseInt(this.id), this.pageNo, this.pageSize).subscribe((data: any) => {
      this.reviews = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadReviews();
  }

  viewDetails(id: number) {
    
  }
}
