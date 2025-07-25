import { Routes } from '@angular/router';
import { DogFetcherComponent } from './components/dogfetcher/dog-fetcher.component';
import { NotFoundComponent } from './components/notfound/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then((m) => m.LoginComponent),
  },
  { path: 'dog', component: DogFetcherComponent, canActivate: [authGuard] },
  { path: '**', component: NotFoundComponent },
];
