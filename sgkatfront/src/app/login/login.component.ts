import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "./login.service";
import {CookieService} from "ngx-cookie";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  error ;

  constructor(private  loginService: LoginService, private router: Router, private _cookieService: CookieService) {
  }

  ngOnInit() {
    if (this.router.url == "/login") {
      this.loginService.logout()
    }
    if (this._cookieService.get('datr') != null)
      this.router.navigate(['/'])
  }

  login() {
    this.loginService.login(this.model.username.toLowerCase(), this.model.password)
      .subscribe(
        res => {
          this.router.navigate(['/home']);
        }, error => {
          this.error = error._body;
          console.log(error._body);
        }
      )
  }
}
