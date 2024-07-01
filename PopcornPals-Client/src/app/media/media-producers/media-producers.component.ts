import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Producer } from '../../models/producer';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-media-producers',
  standalone: true,
  templateUrl: './media-producers.component.html',
  styleUrls: ['./media-producers.component.css'],
  imports: [ MaterialModule ]
})
export class MediaProducersComponent implements OnInit {
  id!: string;
  displayedColumns: string[] = ['name', 'description', 'birthdate', 'details'];
  producers: Producer[] = [];
  pageNo:number = 0;
  pageSize:number = 5;
  totalItems:number = 0;

  constructor(private mediaService: MediaService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadProducers();
  }

  loadProducers() {
    this.mediaService.getProducers(parseInt(this.id), this.pageNo, this.pageSize).subscribe((data: any) => {
      this.producers = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadProducers();
  }

  viewDetails(producerId: number) {
    this.router.navigate([`api/producers/${producerId}`]);
  }
}
