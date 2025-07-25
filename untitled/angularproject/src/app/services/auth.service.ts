import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private _isAuthenticated = signal(true);

  public readonly isAuthenticated = this._isAuthenticated.asReadonly();

  public login(email: string, password: string) {
    this.http
      .post<{
        token: string;
        roles: string[];
      }>('http://localhost:8080/auth/login', { email, password })
      .subscribe({
        next: (res) => {
          localStorage.setItem('token', res.token);
          const payload = JSON.parse(atob(res.token.split('.')[1]));
          const username = payload.username;
          localStorage.setItem('username', username);

          this._isAuthenticated.set(true);
        },
        error: () => {
          alert('Login failed!');
          this._isAuthenticated.set(false);
        },
      });
  }

  logout() {
    localStorage.removeItem('token');
    this._isAuthenticated.set(false);
  }

  toggle() {
    this._isAuthenticated.update((val) => !val);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUsername(): string | null {
    return localStorage.getItem('username');
  }
}
