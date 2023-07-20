import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {environment} from "../environments/environment";
import {AppConstants} from "./AppConstants";
import {routes} from "./app-routing.module";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'blogging-app';
  access_token?: string;

  constructor(private router: Router) {
  }
  ngOnInit(): void {
    let params = new URLSearchParams( window.location.search);
    // @ts-ignore
    this.access_token = params.get(AppConstants.ACCESS_TOKEN)  ? params.get(AppConstants.ACCESS_TOKEN) : localStorage.getItem(AppConstants.ACCESS_TOKEN);
    if (!this.access_token) {
      location.href = environment.BASE_URL + 'login';
    } else {
      // @ts-ignore
      localStorage.setItem(AppConstants.ACCESS_TOKEN, this.access_token);
      if (params.get('name')) {
        // @ts-ignore
        localStorage.setItem('name', params.get('name'));
      }
      if (params.get('username')) {
        // @ts-ignore
        localStorage.setItem('username', params.get('username'));
      }

      if (params.get('image_url')) {
        // @ts-ignore
        localStorage.setItem('image_url', params.get('image_url'));
      }

      routes.forEach(route => {
        if (location.pathname !== '/' && route.path !== '**' && location.pathname.search(route.path)) {
          this.router.navigateByUrl(location.pathname)
        }
      })
      this.router.navigateByUrl('/secured')
    }
  }
}
