import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Actor } from '../models/actor';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  private actorUrl : string;

  constructor(private http: HttpClient) {
    this.actorUrl = 'http://localhost:8080/api/actors/';
  }
  public getActors(pageNo: number, pageSize: number, name: string): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize)
                                      .append("name", name);
    return this.http.get<any>(this.actorUrl, { params: queryParams });
  }

  public getActor(id: number | string ): Observable<Actor> {
    return this.http.get<Actor>(this.actorUrl + `${id}`);
  }

  public createActor(actor: any): Observable<any> {
    return this.http.post<Actor>(this.actorUrl, actor); 
  }
}
