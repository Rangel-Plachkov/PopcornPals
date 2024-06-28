import { Component, Input, OnInit } from '@angular/core';
import { MaterialModule } from "../../material/material.module";
import { Actor } from "../../models/actor"
import { ActorService } from '../../services/actor.service';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-actor-details',
  standalone: true,
  imports: [ MaterialModule ],
  templateUrl: './actor-details.component.html',
  styleUrl: './actor-details.component.css'
})
export class ActorDetailsComponent implements OnInit {
  actor!: Actor;
  id!: string;

  constructor(private actorService : ActorService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.actor = {};
  }
  ngOnInit() : void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadActor();
  }

  loadActor() {
    this.actorService.getActor(parseInt(this.id)).subscribe(( data: Actor ) => {
      console.log(data);
      this.actor = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  viewMedia() {
    this.router.navigate([`./media`], {relativeTo: this.activatedRoute});
  }

  updateActor() {
    this.router.navigate([`./update`], {relativeTo: this.activatedRoute});
  }

  deleteActor() {
    this.actorService.deleteActor(this.id).subscribe();
    this.router.navigate([`api/actors`]);
  }
}
