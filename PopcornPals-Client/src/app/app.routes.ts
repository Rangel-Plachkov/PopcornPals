import { Routes } from '@angular/router';
import { ActorDetailsComponent } from './actor/actor-details/actor-details.component'
import { ActorListComponent } from './actor/actor-list/actor-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppComponent } from './app.component';
import { ActorFormComponent } from './actor/actor-form/actor-form.component';
import { ActorMediaComponent } from './actor/actor-media/actor-media.component';
import { ActorUpdateComponent } from './actor/actor-update/actor-update.component';
import { ProducerListComponent } from './producer/producer-list/producer-list.component';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'api'
    },
    {
        path: 'api',
        title: 'PopcornPals',
        component: AppComponent
    },
    { 
        path: 'api/actors',
        children: [
            { 
                path: '',
                component: ActorListComponent,
                title: 'Actors'
            },
            {
                path: 'create',
                component: ActorFormComponent,
                title: 'Create Actor'
            },
            {
                path: ':id',
                children: [
                    {
                        path: '',
                        component: ActorDetailsComponent
                    },
                    {
                        path: 'media',
                        component: ActorMediaComponent
                    },
                    {
                        path: 'update',
                        component: ActorUpdateComponent
                    }
                ]
            }
        ]
    },
    {
        path: 'api/producers',
        children: [
            {
                path: '',
                component: ProducerListComponent,
                title: 'Producers'
            }
        ]
    },
    {
        path: '**',
        pathMatch: 'full',
        component: PageNotFoundComponent,
        title: 'Page Not Found'
    }
];
