import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private reviewUrl: string;

  constructor(private http: HttpClient) {
    this.reviewUrl = 'http://localhost:8080/api/review/';
  }

  public getReview(id: number): Observable<Review> {
    return this.http.get<Review>(this.reviewUrl + `${id}`);
  }
}
