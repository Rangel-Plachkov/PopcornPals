import { Routes } from '@angular/router';
import {ActorDetailsComponent} from './actor/actor-details/actor-details.component'
import { ActorListComponent } from './actor/actor-list/actor-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppComponent } from './app.component';
import { ActorFormComponent } from './actor/actor-form/actor-form.component';
import { ActorMediaComponent } from './actor/actor-media/actor-media.component';

export const routes: Routes = [
    {
        path: '',
        component: AppComponent
    },
    { 
        path: 'actors', 
        component: ActorListComponent,
        title: 'Actors'
    },
    { 
        path: 'actors/:id', 
        component: ActorDetailsComponent,
        title: 'Actor'
    },
    {
        path: 'actors/:id/media',
        component: ActorMediaComponent
    },
    {
        path: 'create',
        component: ActorFormComponent
    },
    {
        path: '**',
        pathMatch: 'full',
        component: PageNotFoundComponent
    }
];
