import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {LoginService} from "./login/login.service";
import {AuthGuard} from "./auth.guard";
import {routes} from "./app-route.module";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {CookieModule, CookieService} from "ngx-cookie";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routes),
    CookieModule.forRoot(),
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy},LoginService,AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
