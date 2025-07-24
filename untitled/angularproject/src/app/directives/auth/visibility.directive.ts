import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appIfAuthenticated]',
})
export class IfAuthenticatedDirective {
  private isVisible = false;
  private isAuthenticated = true;

  constructor(
    private templateRef: TemplateRef<unknown>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input() set appIfAuthenticated(condition: boolean | '') {
    const show = condition === '' || condition === true;

    if (this.isAuthenticated && show && !this.isVisible) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.isVisible = true;
    } else if ((!this.isAuthenticated || !show) && this.isVisible) {
      this.viewContainer.clear();
      this.isVisible = false;
    }
  }
}
