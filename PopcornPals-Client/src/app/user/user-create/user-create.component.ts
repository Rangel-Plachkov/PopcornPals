import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-create',
  standalone: true,
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class UserCreateComponent implements OnInit {
  userForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    password: ['', Validators.required],
    email: ['', Validators.required],
    description: [],
    birthday: []
  });

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router) { }

  ngOnInit() {
  }

  createUser() {
    this.userService.createUser(this.userForm.value).subscribe((data) => {
      console.log(data);
      this.router.navigate([`api/users`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
