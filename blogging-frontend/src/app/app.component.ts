import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {environment} from "../environments/environment";
import {AppConstants} from "./AppConstants";
import {AppRoutingModule} from "./app-routing.module";

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
      if (location.pathname) {
        this.router.navigateByUrl(location.pathname)
      } else {
        this.router.navigateByUrl('/secured')
      }
    }
  }
}
