import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  role: string = '';

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getToken().authPermission;
    }
  }

  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        if (data) {
          this.tokenStorage.saveToken(data);

          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.role = this.tokenStorage.getToken().authPermission;
          window.location.reload();
        } else {
          this.isLoginFailed = true;
          this.errorMessage = 'Unrecognized user or wrong password'
        }
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }


}
