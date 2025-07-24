import { Routes } from '@angular/router';
import { DogFetcherComponent } from './components/dogfetcher/dog-fetcher.component';
import { NotFoundComponent } from './components/notfound/not-found.component';

export const routes: Routes = [
  { path: '', redirectTo: 'dog', pathMatch: 'full' },
  { path: 'dog', component: DogFetcherComponent },
  { path: '**', component: NotFoundComponent },
];
