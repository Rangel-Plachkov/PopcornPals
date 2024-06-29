import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { StudioService } from '../../services/studio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Studio } from '../../models/studio';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-studio-details',
  standalone: true,
  templateUrl: './studio-details.component.html',
  styleUrls: ['./studio-details.component.css'], 
  imports: [ MaterialModule ]
})
export class StudioDetailsComponent implements OnInit {
  studio!: Studio;
  id!: string;

  constructor(private studioService: StudioService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.studio = {};
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadStudio();
  }

  loadStudio() {
    this.studioService.getStudio(parseInt(this.id)).subscribe( (data: Studio) => {
      this.studio = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  viewMedia() {
    this.router.navigate([`./media`], {relativeTo: this.activatedRoute});
  }

  updateStudio() {
    this.router.navigate([`./update`], {relativeTo: this.activatedRoute});
  }

  deleteStudio() {
    this.studioService.deleteStudio(this.id).subscribe();
    this.router.navigate([`api/studios`]);
  }

}
