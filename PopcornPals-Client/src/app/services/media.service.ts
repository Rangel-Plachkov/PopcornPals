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

  public updateMedia(id: number | string, media: any): Observable<Media> {
    return this.http.put<Media>(this.mediaUrl + `${id}`, media);
  }

  public assignStudio(id: number | string, studioId: string | number):Observable<Media> {
    return this.http.patch<any>(this.mediaUrl + `${id}/studio/${studioId}`, {});
  }

  public getActors(id: number, pageNo: number, pageSize: number): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.mediaUrl + `${id}/actors`, { params: queryParams });
  }

  public getProducers(id: number, pageNo: number, pageSize: number): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.mediaUrl + `${id}/producers`, { params: queryParams });
  }

  public getReviews(id: number | string, pageNo: number, pageSize: number): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.mediaUrl + `${id}/reviews`, { params: queryParams });
  }

  public deleteMedia(id: number | string) {
    return this.http.delete(this.mediaUrl + `${id}`);
  }
}
