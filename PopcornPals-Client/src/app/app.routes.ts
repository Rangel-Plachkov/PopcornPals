import { Routes } from '@angular/router';
import { ActorDetailsComponent } from './actor/actor-details/actor-details.component'
import { ActorListComponent } from './actor/actor-list/actor-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppComponent } from './app.component';
import { ActorFormComponent } from './actor/actor-form/actor-form.component';
import { ActorMediaComponent } from './actor/actor-media/actor-media.component';
import { ActorUpdateComponent } from './actor/actor-update/actor-update.component';

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
        component: ActorDetailsComponent
    },
    {
        path: 'actors/:id/media',
        component: ActorMediaComponent
    },
    {
        path: 'actors/:id/update',
        component: ActorUpdateComponent
    },
    {
        path: 'create',
        component: ActorFormComponent,
        title: 'Create Actor'
    },
    {
        path: '**',
        pathMatch: 'full',
        component: PageNotFoundComponent,
        title: 'Page Not Found'
    }
];
