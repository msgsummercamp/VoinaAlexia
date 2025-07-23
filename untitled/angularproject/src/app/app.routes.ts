import { Routes } from '@angular/router';
import { DogFetcherComponent } from './components/dogfetcher/dog-fetcher.component';

export const routes: Routes = [
  { path: '', redirectTo: 'dog', pathMatch: 'full' },
  { path: 'dog', component: DogFetcherComponent },
  { path: '**', redirectTo: 'dog' },
];
