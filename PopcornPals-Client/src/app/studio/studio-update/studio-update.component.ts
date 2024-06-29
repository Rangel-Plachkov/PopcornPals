import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Studio } from '../../models/studio';
import { StudioService } from '../../services/studio.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-studio-update',
  standalone: true,
  templateUrl: './studio-update.component.html',
  styleUrls: ['./studio-update.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class StudioUpdateComponent implements OnInit {
  studioForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [],
    foundingDate: []
  });

  id!: string;
  studio!: Studio;

  constructor(private formBuilder: FormBuilder,
              private studioService: StudioService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
    this.studio = {};
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadStudio();
    this.setForm();
  }

  loadStudio() {
    this.studioService.getStudio(parseInt(this.id)).subscribe((data: Studio) => {
      this.studio = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }
  
  setForm() {
    this.studioForm.patchValue({ name: this.studio.name });
    this.studioForm.patchValue({ description: this.studio.description });
    this.studioForm.patchValue({ foundingDate: this.studio.foundingDate });
  }

  updateStudio() {
    console.log(this.studioForm.value);
    this.studioService.updateStudio(this.id, this.studioForm.value).subscribe((data) => {
      console.log(data);
      this.router.navigate([`api/studios`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
