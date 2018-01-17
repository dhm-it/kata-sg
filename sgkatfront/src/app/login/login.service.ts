import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie";
import {environment} from "../../environments/environment";

@Injectable()
export class LoginService {
  apiUrl = environment.apiUrl;
  constructor(private http: Http, private router: Router,
              private _cookieService: CookieService) { }
  login(username: string, password: string) {
    let body = {
      "username": username,
      "password": password,
    }
    return this.http.post(this.apiUrl + "/login", body)
      .map(response => {
        var today = new Date();
        var expiresValue = new Date(today);
        let remembermeExpires = new Date(today);
        remembermeExpires.setDate(today.getDay() + 14);
        let parameters = JSON.parse(response["_body"]);
        let token = parameters.token;
        expiresValue.setSeconds(today.getSeconds() + 3600);//1 heure
        this._cookieService.put('datr', token, {'expires': expiresValue});
        return true;
      }, error => {
        return false;
      })
  }

  logout() {
    this._cookieService.remove('datr');
  }

}
