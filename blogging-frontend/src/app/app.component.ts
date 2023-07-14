import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'user-management-app';

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
  }
  ngOnInit(): void {
    // let params = window.location.href.split('?')[1];
    // params.split('&').forEach(value => {
    //   if (value.includes('access_token')) {
    //     console.log(value.split('=')[1])
    //   }
    // })

    let params = new URLSearchParams( window.location.search);
    // @ts-ignore
    let authorizationCode = params.get("access_token")  ? params.get("access_token") : '';
    // @ts-ignore
    localStorage.setItem('access_token', authorizationCode);

  }
}
