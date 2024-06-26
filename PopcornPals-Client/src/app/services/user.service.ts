import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userUrl: string;

  constructor(private http: HttpClient) {
    this.userUrl = 'http://localhost:8080/api/users/';
  }

  public getUsers(pageNo: number, pageSize: number, name: string, username: string): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize)
                                      .append("name", name)
                                      .append("username", username);
    return this.http.get<any>(this.userUrl, { params: queryParams });
  }

  public getUser(id: number | string): Observable<User> {
    return this.http.get<User>(this.userUrl + `${id}`);
  }

  public createUser(user: any): Observable<User> {
    return this.http.post<User>(this.userUrl, user);
  }

  public updateUser(id: number | string, user: any): Observable<User> {
    return this.http.put<User>(this.userUrl + `${id}`, user);
  }

  public getPlaylists(id: number | string, pageNo: number, pageSize: number) {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.userUrl + `${id}/playlists`, { params: queryParams });
  }

  public deleteUser(id: number | string) {
    return this.http.delete(this.userUrl + `${id}`);
  }
}
