import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Actor } from '../../models/actor';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-media-actors',
  standalone: true,
  templateUrl: './media-actors.component.html',
  styleUrls: ['./media-actors.component.css'],
  imports: [ MaterialModule ]
})
export class MediaActorsComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['name', 'description', 'birthdate', 'details'];
  actors: Actor[] = [];
  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private mediaService: MediaService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadActors();
  }

  loadActors() {
    this.mediaService.getActors(parseInt(this.id), this.pageNo, this.pageSize).subscribe((data: any) => {
      this.actors = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadActors();
  }

  viewDetails(actorId: number) {
    this.router.navigate([`api/actors/${actorId}`]);
  }
}
