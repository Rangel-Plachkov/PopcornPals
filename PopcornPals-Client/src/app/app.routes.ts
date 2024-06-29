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
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailsComponent } from './user/user-details/user-details.component';
import { UserCreateComponent } from './user/user-create/user-create.component';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { UserPlaylistComponent } from './user/user-playlist/user-playlist.component';

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
        path: 'api/users',
        children: [
            {
                path: '',
                title: 'PopcornPals | Users',
                component: UserListComponent
            },
            {
                path: 'create',
                title: 'PopcornPals | Create User',
                component: UserCreateComponent
            },
            {
                path: ':id',
                children: [
                    {
                        path: '',
                        component: UserDetailsComponent
                    },
                    {
                        path: 'update',
                        title: 'PopcornPals | Update User',
                        component: UserUpdateComponent
                    },
                    {
                        path: 'playlists',
                        component: UserPlaylistComponent
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
