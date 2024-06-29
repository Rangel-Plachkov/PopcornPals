import { Component, OnInit } from '@angular/core';
import { Producer } from '../../models/producer';
import { ProducerService } from '../../services/producer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { MaterialModule } from "../../material/material.module";

@Component({
  selector: 'app-producer-details',
  standalone: true,
  templateUrl: './producer-details.component.html',
  styleUrls: ['./producer-details.component.css'],
  imports: [ MaterialModule ]
})
export class ProducerDetailsComponent implements OnInit {
  producer!: Producer;
  id!: string;

  constructor(private producerService: ProducerService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadProducer();
  }

  loadProducer() {
    this.producerService.getProducer(parseInt(this.id)).subscribe( data => {
      this.producer = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  viewProducedMedia() {
    this.router.navigate([`./media`], {relativeTo: this.activatedRoute});
  }

  updateProducer() {
    this.router.navigate([`./update`], {relativeTo: this.activatedRoute});
  }

  deleteProducer() {
    this.producerService.deleteProducer(this.id).subscribe();
    this.router.navigate([`api/producers`]);
  }
}
