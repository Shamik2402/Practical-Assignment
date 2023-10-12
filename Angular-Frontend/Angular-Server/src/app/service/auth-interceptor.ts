// auth.interceptor.ts

import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { environment } from 'src/environment/environment';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router, private cookie: CookieService) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if (req.url === `${environment.apiUrl}/authenticate`) {
      return next.handle(req);
    }

    let token = this.getTokenFromCookie();

    // Clone the request and set the Authorization header
    const authReq = req.clone({
      setHeaders: {
        Authorization: "Bearer " + token,
      },
    });

    return next.handle(authReq).pipe(
      tap(
        (event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            // Do nothing on successful responses
          }
        },
        (error) => {
          if (error.status === 404) {
            // Unauthorized: Redirect to the login page
            this.router.navigate([''], {
              queryParams: { error: 'Session expired. Please log in again.' },
            });
          }
        }
      )
    );
  }

  getTokenFromCookie() {
    return this.cookie.get("Bearer");
  }
}
