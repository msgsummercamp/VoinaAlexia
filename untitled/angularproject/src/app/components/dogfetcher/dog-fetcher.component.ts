import { Component, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-dog-fetcher',
  standalone: true,
  imports: [CommonModule, MatButtonModule],
  templateUrl: './dog-fetcher.component.html',
  styleUrls: ['./dog-fetcher.component.scss'],
})
export class DogFetcherComponent {
  dogImageUrl = signal<string | null>(null);
  loading = signal(false);

  constructor(private http: HttpClient) {}

  showDog() {
    this.loading.set(true);
    this.http.get<{ message: string }>('https://dog.ceo/api/breeds/image/random').subscribe({
      next: (res) => {
        this.dogImageUrl.set(res.message);
        this.loading.set(false);
      },
      error: () => {
        alert('Failed to fetch dog image.');
        this.loading.set(false);
      },
    });
  }
}
