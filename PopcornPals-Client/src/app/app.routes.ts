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
import { StudioListComponent } from './studio/studio-list/studio-list.component';
import { StudioDetailsComponent } from './studio/studio-details/studio-details.component';
import { StudioCreateComponent } from './studio/studio-create/studio-create.component';
import { StudioUpdateComponent } from './studio/studio-update/studio-update.component';
import { StudioMediaComponent } from './studio/studio-media/studio-media.component';
import { PlaylistCreateComponent } from './playlist/playlist-create/playlist-create.component';
import { PlaylistUpdateComponent } from './playlist/playlist-update/playlist-update.component';
import { PlaylistMediaComponent } from './playlist/playlist-media/playlist-media.component';
import { MediaListComponent } from './media/media-list/media-list.component';
import { MediaDetailsComponent } from './media/media-details/media-details.component';
import { MediaCreateComponent } from './media/media-create/media-create.component';
import { MediaUpdateComponent } from './media/media-update/media-update.component';
import { MediaActorsComponent } from './media/media-actors/media-actors.component';
import { MediaProducersComponent } from './media/media-producers/media-producers.component';
import { MediaReviewsComponent } from './media/media-reviews/media-reviews.component';
import { ReviewDetailsComponent } from './review/review-details/review-details.component';

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
                title: 'PopcornPals | Actors',
                component: ActorListComponent
            },
            {
                path: 'create',
                title: 'PopcornPals | Create Actor',
                component: ActorFormComponent
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
                        title: 'PopcornPals | Update Actor',
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
                title: 'PopcornPals | Producers',
                component: ProducerListComponent
            },
            {
                path: 'create',
                title: 'PopcornPals | Create Producer',
                component: ProducerCreateComponent
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
                        title: 'PopcornPals | Update Producer',
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
                        title: 'PopcornPals | Users',
                        component: UserDetailsComponent
                    },
                    {
                        path: 'update',
                        title: 'PopcornPals | Update User',
                        component: UserUpdateComponent
                    },
                    {
                        path: 'playlists/create',
                        title: 'PopcornPals | Create Playlist',
                        component: PlaylistCreateComponent
                    },
                    {
                        path: 'playlists',
                        children: [
                            {
                                path: '',
                                title: 'PopcornPals | User Playlists',
                                component: UserPlaylistComponent
                            },
                            {
                                path: ':playlistId/update',
                                title: 'PopcornPals | Update Playlist',
                                component: PlaylistUpdateComponent
                            },
                            {
                                path: ':playlistId',
                                title: 'PopcornPals | Playlist',
                                component: PlaylistMediaComponent
                            }
                        ]
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
                title: 'PopcornPals | Studios',
                component: StudioListComponent
            },
            {
                path: 'create',
                title: 'PopcornPals | Create Studio',
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
                        title: 'PopcornPals | Update Studio',
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
        path: 'api/media',
        children: [
            {
                path: '',
                title: 'PopcornPals | Media',
                component: MediaListComponent
            },
            {
                path: 'create',
                title: 'PopcornPals | Create Media',
                component: MediaCreateComponent
            },
            {
                path: ':id',
                children: [
                    {
                        path: '',
                        component: MediaDetailsComponent
                    },
                    {
                        path: 'update',
                        title: 'PopcornPals | Update Media',
                        component: MediaUpdateComponent
                    },
                    {
                        path: 'actors',
                        title: 'PopcornPals | Media | Actors',
                        component: MediaActorsComponent
                    },
                    {
                        path: 'producers',
                        title: 'PopcornPals | Media | Producers',
                        component: MediaProducersComponent
                    },
                    {
                        path: 'reviews',
                        title: 'PopcornPals | Media | Reviews',
                        component: MediaReviewsComponent
                    }
                ]
            }
        ]
    },
    {
        path: 'api/reviews/:id',
        component: ReviewDetailsComponent
    },
    {
        path: '**',
        pathMatch: 'full',
        title: 'PopcornPals | Page Not Found',
        component: PageNotFoundComponent
    }
];
