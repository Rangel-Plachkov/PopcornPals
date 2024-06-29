import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { Studio } from '../../models/studio';
import { StudioService } from '../../services/studio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-studio-list',
  standalone: true,
  templateUrl: './studio-list.component.html',
  styleUrls: ['./studio-list.component.css'],
  imports: [ MaterialModule ]
})
export class StudioListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'description', 'foundingDate', 'details'];
  studios: Studio[] = [];

  pageNo: number = 0;
  pageSize: number = 5;
  name: string = "";
  totalItems: number = 0;

  constructor(private studioService: StudioService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadStudios();
  }

  loadStudios() {
    this.studioService.getStudios(this.pageNo, this.pageSize, this.name).subscribe( data => {
      console.log(data);
      this.studios = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadStudios();
  }

  search(){
    this.loadStudios();
  }

  viewDetails(id: number) {
    this.router.navigate([`./${id}`], {relativeTo: this.activatedRoute});
  }
}
