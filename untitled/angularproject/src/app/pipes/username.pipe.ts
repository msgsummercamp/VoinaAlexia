import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'username',
})
export class UsernamePipe implements PipeTransform {
  transform(value: string | null | undefined): string {
    if (!value) return '@Anonymous';
    const formatted = value.charAt(0).toUpperCase() + value.slice(1);
    return `@${formatted}`;
  }
}
