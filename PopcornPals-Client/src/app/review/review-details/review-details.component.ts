import { Component, OnInit } from '@angular/core';
import { Review } from '../../models/review';
import { MaterialModule } from '../../material/material.module';
import { ReviewService } from '../../services/review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { parse } from 'path';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-review-details',
  standalone: true,
  templateUrl: './review-details.component.html',
  styleUrls: ['./review-details.component.css'],
  imports: [ MaterialModule ]
})
export class ReviewDetailsComponent implements OnInit {
  id!: string;
  review!: Review;

  constructor(private reviewService: ReviewService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadReview();
  }

  loadReview() {
    this.reviewService.getReview(parseInt(this.id)).subscribe( data => {
      this.review = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }
}
