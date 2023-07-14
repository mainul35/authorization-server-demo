import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../models/user.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  public addUser(user: UserModel) : Observable<any> {
    return this.httpClient.post(`${environment.BASE_URL}users/add`, user);
  }
}
