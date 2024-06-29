import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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
}
