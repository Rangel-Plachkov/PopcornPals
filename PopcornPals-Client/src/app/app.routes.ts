import { Routes } from '@angular/router';
import { ActorDetailsComponent } from './actor/actor-details/actor-details.component'
import { ActorListComponent } from './actor/actor-list/actor-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppComponent } from './app.component';
import { ActorFormComponent } from './actor/actor-form/actor-form.component';
import { ActorMediaComponent } from './actor/actor-media/actor-media.component';
import { ActorUpdateComponent } from './actor/actor-update/actor-update.component';
import { ProducerListComponent } from './producer/producer-list/producer-list.component';
import { ProducerDetailsComponent } from './producer/producer-details/producer-details.component';
import { ProducerCreateComponent } from './producer/producer-create/producer-create.component';
import { ProducerMediaComponent } from './producer/producer-media/producer-media.component';
import { ProducerUpdateComponent } from './producer/producer-update/producer-update.component';
import { StudioListComponent } from './studio/studio-list/studio-list.component';
import { StudioDetailsComponent } from './studio/studio-details/studio-details.component';
import { StudioCreateComponent } from './studio/studio-create/studio-create.component';
import { StudioUpdateComponent } from './studio/studio-update/studio-update.component';
import { StudioMediaComponent } from './studio/studio-media/studio-media.component';

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
            },
            {
                path: 'create',
                component: ProducerCreateComponent,
                title: 'Create Producer'
            },
            {
                path: ':id',
                children: [
                    {
                        path: '',
                        component: ProducerDetailsComponent
                    },
                    {
                        path: 'media',
                        component: ProducerMediaComponent
                    },
                    {
                        path: 'update',
                        component: ProducerUpdateComponent
                    }
                ]
            }
        ]
    },
    {
        path: 'api/studios',
        children: [
            {
                path: '',
                title: 'Studios',
                component: StudioListComponent
            },
            {
                path: 'create',
                title: 'Create Studio',
                component: StudioCreateComponent
            },
            {
                path: ':id',
                children: [
                    {
                        path: '',
                        component: StudioDetailsComponent
                    },
                    {
                        path: 'update',
                        component: StudioUpdateComponent
                    },
                    {
                        path: 'media',
                        component: StudioMediaComponent
                    }
                ]
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
