import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producer } from '../models/producer';

@Injectable({
  providedIn: 'root'
})
export class ProducerService {
  private producerUrl: string;

  constructor(private http: HttpClient) {
    this.producerUrl = 'http://localhost:8080/api/producers/';
  }

  public getProducers(pageNo: number, pageSize: number, name: string): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize)
                                      .append("name", name);
    return this.http.get<any>(this.producerUrl, { params: queryParams });
  }

  public getProducer(id: string | number): Observable<Producer> {
    return this.http.get<Producer>(this.producerUrl + `${id}`); 
  }

  public createProducer(producer: any): Observable<Producer> {
    return this.http.post<Producer>(this.producerUrl, producer);
  }

  public updateProducer(id: number | string, producer: any): Observable<Producer> {
    return this.http.put<Producer>(this.producerUrl + `${id}`, producer);
  }

  public getMedia(id: number | string, pageNo: number, pageSize: number): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.producerUrl + `${id}/media/`, { params: queryParams });
  }

  public deleteProducer(id: string | number) {
    return this.http.delete(this.producerUrl + `${id}`);
  }
}
