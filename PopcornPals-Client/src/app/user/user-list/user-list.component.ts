import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-user-list',
  standalone: true,
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  imports: [ MaterialModule ]
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'username', 'description', 'birthday', 'details'];
  users: User[] = [];

  pageNo: number = 0;
  pageSize: number = 5;
  username: string = "";
  totalItems: number = 0;

  constructor(private userService: UserService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers(this.pageNo, this.pageSize, "", this.username).subscribe( data => {
      console.log(data);
      this.users = data.content;
      this.totalItems = data.totalElements;
    });
  }

  getPageData(event: PageEvent) {
    this.pageNo = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadUsers();
  }

  search(){
    this.loadUsers();
  }

  viewDetails(id: number) {
    this.router.navigate([`./${id}`], {relativeTo: this.activatedRoute});
  }
}
