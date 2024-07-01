import { Component, Input } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { MaterialModule } from "./material/material.module";
import { CdkColumnDef } from '@angular/cdk/table';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, MaterialModule ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers:[CdkColumnDef]
})
export class AppComponent {
  title = "PopcornPals";

}
