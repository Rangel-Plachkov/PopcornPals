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

  public createPlaylist(playlist: any): Observable<Playlist> {
    return this.http.post<Playlist>(this.playlistUrl, playlist);
  }

}
