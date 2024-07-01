import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Media } from '../models/media'
import { Studio } from '../models/studio';

@Injectable({
  providedIn: 'root'
})
export class MediaService {
  private mediaUrl: string;

  constructor(private http: HttpClient) {
    this.mediaUrl = 'http://localhost:8080/api/media/';
  }

  public getMedia(title: string, pageNo: number = 0, pageSize: number = 5): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize)
                                      .append("title", title);
    return this.http.get<any>(this.mediaUrl, { params: queryParams });
  }

  public getSingleMedia(id: number | string): Observable<Media> {
    return this.http.get<Media>(this.mediaUrl + `${id}`);
  }

  public getStudioOfMedia(id: number | string): Observable<Studio> {
    return this.http.get<Studio>(this.mediaUrl + `${id}/studio`);
  }

  public createMedia(media: any): Observable<Media> {
    return this.http.post<Media>(this.mediaUrl, media);
  }
}
