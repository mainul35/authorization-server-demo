import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import {AppConstants} from "../AppConstants";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor() {}

  allowedPaths: string[] = [
    'login',
    'secured',
  ]

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    debugger
    // @ts-ignore
    this.allowedPaths.forEach(path => {
      if (request.url.includes(path)) {
        return next.handle(request);
      }
    })

    const userInfoStr = localStorage.getItem(AppConstants.ACCESS_TOKEN);
    // @ts-ignore
    const userInfo = JSON.parse(userInfoStr);
    const jwt = userInfo.jwtToken;

    // request.headers['headers'] = [{"Authorization": `Bearer ${jwt}`}]

    request = request.clone({
      setHeaders: {'authorization': 'Bearer '+jwt}
    });

    return next.handle(request);
  }
}
