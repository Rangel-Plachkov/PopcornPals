import { Component, OnInit } from '@angular/core';
import { MaterialModule } from "../../material/material.module";
import { ActivatedRoute } from '@angular/router';
import { ActorService } from '../../services/actor.service';
import { PageEvent } from '@angular/material/paginator';
import { Media } from '../../models/media';

@Component({
  selector: 'app-actor-media',
  standalone: true,
  imports: [ MaterialModule ],
  templateUrl: './actor-media.component.html',
  styleUrl: './actor-media.component.css'
})
export class ActorMediaComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['title', 'genre', 'releaseDate'];
  media: Media[] = [];
  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private actorService: ActorService, private activatedRoute: ActivatedRoute) {
    this.media = [];
  }
  ngOnInit() : void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadMedia();
  }

  loadMedia() {
    this.actorService.getMedia(this.id, this.pageNo, this.pageSize).subscribe( (data: any) => {
      console.log(data);
      this.media = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadMedia();
  }
}
