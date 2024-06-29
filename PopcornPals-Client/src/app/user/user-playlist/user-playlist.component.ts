import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Playlist } from '../../models/playlist';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-user-playlist',
  standalone: true,
  templateUrl: './user-playlist.component.html',
  styleUrls: ['./user-playlist.component.css'],
  imports: [ MaterialModule ]
})
export class UserPlaylistComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['name', 'details', 'edit', 'delete'];
  playlists: Playlist[] = [];

  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute) {
    this.playlists = [];
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadPlaylists();
  }

  loadPlaylists() {
    this.userService.getPlaylists(this.id, this.pageNo, this.pageSize).subscribe( (data: any) => {
      this.playlists = data.content;
      this.totalItems = data.totalElements;
    });
  }

  viewContent(playlistId: number) {

  }

  editPlaylist(playlistId: number) {

  }

  deletePlaylist(playlistId: number) {

  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPlaylists();
  }
}
