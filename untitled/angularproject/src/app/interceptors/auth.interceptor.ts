import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';

export const AuthInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  if (req.url.includes('dog.ceo')) {
    return next(req);
  }

  const mockToken = 'MOCK_TOKEN_123';

  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${mockToken}`,
    },
  });

  return next(authReq);
};
