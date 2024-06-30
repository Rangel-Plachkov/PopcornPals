import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Playlist } from '../models/playlist';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {
  private playlistUrl: string;

  constructor(private http: HttpClient) {
    this.playlistUrl = 'http://localhost:8080/api/playlists/';
  }

  public getPlaylist(id: number | string): Observable<Playlist> {
    return this.http.get<Playlist>(this.playlistUrl + `${id}`);
  }

  public createPlaylist(playlist: any): Observable<Playlist> {
    return this.http.post<Playlist>(this.playlistUrl, playlist);
  }

  public updatePlaylist(id: number | string, playlist: any): Observable<Playlist> {
    return this.http.put<Playlist>(this.playlistUrl + `${id}`, playlist);
  }

  public getContent(id: number | string, pageNo: number, pageSize: number): Observable<any> {
    let queryParams = new HttpParams().append("pageNo", pageNo)
                                      .append("pageSize", pageSize);
    return this.http.get<any>(this.playlistUrl + `${id}/media/`, { params: queryParams });
  }

  public deletePlaylist(id: number | string) {
    return this.http.delete(this.playlistUrl + `${id}`);
  }
}
