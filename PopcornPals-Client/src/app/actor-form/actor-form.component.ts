import { Component } from '@angular/core';
import { MaterialModule } from "../material/material.module";
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ActorService } from '../services/actor.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-actor-form',
  standalone: true,
  imports: [ MaterialModule, ReactiveFormsModule, NgIf ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './actor-form.component.html',
  styleUrl: './actor-form.component.css'
})
export class ActorFormComponent {
  actorForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: [],
    birthdate: []
  });
  errorMessages:string[] = [];

  constructor(private formBuilder: FormBuilder, private actorService: ActorService) {

  }

  createActor() {
    console.log(this.actorForm.value);
    this.actorService.createActor(this.actorForm.value).subscribe((data) => {
      console.log(data);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
