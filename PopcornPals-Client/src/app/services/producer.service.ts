import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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

}
