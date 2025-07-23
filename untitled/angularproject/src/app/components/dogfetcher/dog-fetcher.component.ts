import { Component, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-dog-fetcher',
  imports: [CommonModule, MatButtonModule],
  templateUrl: './dog-fetcher.component.html',
  styleUrl: './dog-fetcher.component.scss',
})
export class DogFetcherComponent {
  // @ts-ignore
  public readonly dogImageUrl = signal<string>();
  public readonly loading = signal(false);
  // @ts-ignore
  public readonly errorMessage = signal<string>();

  private readonly http = inject(HttpClient);

  showDog() {
    this.loading.set(true);
    // @ts-ignore
    this.errorMessage.set(undefined);

    this.http.get<{ message: string }>('https://dog.ceo/api/breeds/image/random').subscribe({
      next: (res) => {
        this.dogImageUrl.set(res.message);
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set('Failed to fetch dog image');
        this.loading.set(false);
      },
    });
  }
}
