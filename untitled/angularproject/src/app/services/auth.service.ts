import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _isAuthenticated = signal(true);

  public readonly isAuthenticated = this._isAuthenticated.asReadonly();

  login() {
    this._isAuthenticated.set(true);
  }

  logout() {
    this._isAuthenticated.set(false);
  }

  toggle() {
    this._isAuthenticated.update((val) => !val);
  }
}
