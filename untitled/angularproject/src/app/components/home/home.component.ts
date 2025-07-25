import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UsernamePipe } from '../../pipes/username.pipe';

@Component({
  selector: 'app-home',
  imports: [CommonModule, UsernamePipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  public readonly auth = inject(AuthService);
  public readonly username = this.auth.getUsername();
  private readonly router = inject(Router);

  public handleClick() {
    if (this.auth.isAuthenticated()) {
      this.auth.logout();
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
