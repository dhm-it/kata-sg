import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {JwtHelper} from "angular2-jwt";
import {CookieService} from "ngx-cookie";
import {LoginService} from "./login/login.service";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private _cookieService: CookieService,
              private loginService: LoginService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let jwtHelper = new JwtHelper() ;
    //si le token n'existe pas on teste sur le remember me token
    if (this._cookieService.get('datr') == null || jwtHelper.isTokenExpired(this._cookieService.get('datr'))) {
      this.router.navigate(['/login'])
      return false;
    }
    return true;
  }
}
