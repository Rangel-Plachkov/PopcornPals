import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ReactiveFormsModule, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from '../../models/user';
import { parse } from 'path';

@Component({
  selector: 'app-user-update',
  standalone: true,
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css'],
  imports: [ MaterialModule, ReactiveFormsModule, NgIf, NgFor ],
  providers: [provideNativeDateAdapter()]
})
export class UserUpdateComponent implements OnInit {
  userForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    password: ['', Validators.required],
    email: ['', Validators.required],
    description: [],
    birthday: []
  });

  id!: string;
  user!: User;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
    this.user = {};
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadUser();
    this.setForm();
  }

  loadUser() {
    this.userService.getUser(parseInt(this.id)).subscribe((data: User) => {
      this.user = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  setForm() {
    this.userForm.patchValue({ name : this.user.name });
    this.userForm.patchValue({ username : this.user.username });
    this.userForm.patchValue({ description : this.user.description });
    this.userForm.patchValue({ birthday : this.user.birthday });
  }

  updateUser() {
    this.userService.updateUser(this.id, this.userForm.value).subscribe((data) => {
      console.log(data);
      this.router.navigate([`api/users`]);
    }, (error: HttpErrorResponse) => {
      console.log(error.error.errors);
    });
  }
}
