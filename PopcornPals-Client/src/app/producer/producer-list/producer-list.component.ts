import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Producer } from '../../models/producer';
import { ProducerService } from '../../services/producer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-producer-list',
  standalone: true,
  templateUrl: './producer-list.component.html',
  styleUrls: ['./producer-list.component.css'],
  imports: [ MaterialModule ]
})
export class ProducerListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'description', 'birthdate', 'details'];
  producers: Producer[] = [];

  pageNo: number = 0;
  pageSize: number = 5;
  name: string = "";
  totalItems: number = 0;

  constructor(private producerService: ProducerService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.loadProducers();
  }

  loadProducers() {
    this.producerService.getProducers(this.pageNo, this.pageSize, this.name).subscribe( data => {
      console.log(data);
      this.producers = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadProducers();
  }

  search() {
    this.loadProducers();
  }

  viewDetails(id: number) {
    this.router.navigate([`./${id}`], {relativeTo: this.activatedRoute});
  }
}
