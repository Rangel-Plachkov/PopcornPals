import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { ActorService } from '../../services/actor.service';
import { Actor } from '../../models/actor';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-actor-list',
  standalone: true,
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css'],
  imports: [ MaterialModule ]
})
export class ActorListComponent implements OnInit {
  title: string = "Actors";
  displayedColumns: string[] = ['name', 'description', 'birthdate', 'details'];
  actors: Actor[] = [];

  pageNo: number = 0;
  pageSize: number = 5;
  name: string = "";
  totalItems: number = 0;
  
  constructor(private actorService: ActorService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() : void {
    this.loadActors();
  }

  loadActors() {
    this.actorService.getActors(this.pageNo, this.pageSize, this.name).subscribe( data => {
      console.log(data);
      this.actors = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadActors();
  }

  search(){
    this.loadActors();
  }

  viewDetails(id: number) {
    console.log(id);
    this.router.navigate([`./${id}`], {relativeTo: this.activatedRoute});
  }
}
