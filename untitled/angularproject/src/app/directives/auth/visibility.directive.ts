import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appIfAuthenticated]',
})
export class IfAuthenticatedDirective {
  private isAuthenticated = true;

  constructor(
    private templateRef: TemplateRef<unknown>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input() set appIfAuthenticated(condition: boolean) {
    if (this.isAuthenticated) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else if (!this.isAuthenticated || condition) {
      this.viewContainer.clear();
    }
  }
}
