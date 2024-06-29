import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { StudioService } from '../../services/studio.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-studio-create',
  standalone: true,
  templateUrl: './studio-create.component.html',
  styleUrls: ['./studio-create.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class StudioCreateComponent implements OnInit {
  studioForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [],
    foundingDate: []
  });

  constructor(private formBuilder: FormBuilder, private studioService: StudioService, private router: Router) { }

  ngOnInit() {
  }

  createStudio() {
    this.studioService.createStudio(this.studioForm.value).subscribe((data) => {
      console.log(data);
      this.router.navigate([`api/studios`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
