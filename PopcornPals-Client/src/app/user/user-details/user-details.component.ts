import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-details',
  standalone: true,
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css'],
  imports: [ MaterialModule ]
})
export class UserDetailsComponent implements OnInit {
  user!: User;
  id!: string;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.user = {};
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadUser();
  }

  loadUser() {
    this.userService.getUser(parseInt(this.id)).subscribe(( data: User ) => {
      this.user = data;
    }, (error: HttpErrorResponse) => {
      console.log(error.error.message);
      this.router.navigate([`**`]);
    });
  }

  updateUser() {
    this.router.navigate([`./update`], {relativeTo: this.activatedRoute});
  }

  deleteUser() {
    this.userService.deleteUser(this.id).subscribe();
    this.router.navigate([`api/users`]);
  }
}
