import { Directive, effect, Input, signal, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appIfAuthenticated]',
})
export class IfAuthenticatedDirective {
  private isAuthenticated = signal(true);

  constructor(
    private templateRef: TemplateRef<unknown>,
    private viewContainer: ViewContainerRef
  ) {
    effect(() => {
      if (this.isAuthenticated()) {
        this.viewContainer.createEmbeddedView(this.templateRef);
      } else {
        this.viewContainer.clear();
      }
    });
  }

  @Input() set appIfAuthenticated(condition: boolean) {
    this.isAuthenticated.set(condition);
  }
}
