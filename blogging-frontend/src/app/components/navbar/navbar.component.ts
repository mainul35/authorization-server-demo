import { Component, OnInit } from '@angular/core';
import {AppConstants} from "../../AppConstants";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  imageUrl ?: string;
  name ?: string;
  constructor() { }

  ngOnInit(): void {
    // @ts-ignore
    this.imageUrl = localStorage.getItem('image_url');
    // @ts-ignore
    this.name = localStorage.getItem('name');
  }

  logout() {
    localStorage.removeItem(AppConstants.ACCESS_TOKEN)
    location.href = `${environment.BASE_URL}logout`
  }
}
