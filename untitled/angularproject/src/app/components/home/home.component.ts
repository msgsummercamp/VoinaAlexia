import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(
    public auth: AuthService,
    private router: Router
  ) {}
  handleClick() {
    if (this.auth.isAuthenticated()) {
      this.auth.logout();
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
