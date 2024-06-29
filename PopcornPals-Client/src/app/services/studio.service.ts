import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Studio } from '../models/studio';

@Injectable({
  providedIn: 'root'
})
export class StudioService {
  private studioUrl: string;

  constructor(private http: HttpClient) {
    this.studioUrl = 'http://localhost:8080/api/studios/';
  }

  public getStudios(pageNo: number, pageSize: number, name: string): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize)
                                      .append("name", name);
    return this.http.get<any>(this.studioUrl, { params: queryParams });
  }

  public getStudio(id: string | number): Observable<Studio> {
    return this.http.get<Studio>(this.studioUrl + `${id}`);
  }

  public createStudio(studio: any): Observable<Studio> {
    return this.http.post<Studio>(this.studioUrl, studio);
  }

  public updateStudio(id: number | string, studio: any): Observable<Studio> {
    return this.http.put<Studio>(this.studioUrl + `${id}`, studio);
  }

  public getMedia(id: number | string, pageNo: number, pageSize: number) {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.studioUrl + `${id}/media`, { params: queryParams });
  }

  public deleteStudio(id: number | string) {
    return this.http.delete(this.studioUrl + `${id}`);
  }
}
