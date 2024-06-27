import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Media } from '../models/media'

@Injectable({
  providedIn: 'root'
})
export class MediaService {
  private mediaUrl: string;

  constructor(private http: HttpClient) {
    this.mediaUrl = 'http://localhost:8080/api/media/';
  }

  public getMedia(title: string): Observable<Media[]> {
    let queryParams = new HttpParams().append("title", title);
    return this.http.get<Media[]>(this.mediaUrl, { params: queryParams });
  }
}
