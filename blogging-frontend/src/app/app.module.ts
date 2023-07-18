import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import { SecuredComponent } from './components/secured/secured.component';
import { LogoutComponent } from './components/logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SecuredComponent,
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
