import {Routes} from "@angular/router";
import {AuthGuard} from "./auth.guard";

export const routes: Routes = [
  {
    path: 'login', loadChildren: './login/login.module#LoginModule'
  },
  {path: '', loadChildren: './home/home.module#HomeModule', canActivate: [AuthGuard]}
  ]
