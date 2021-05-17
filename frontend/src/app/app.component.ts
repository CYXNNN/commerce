import {Component, TemplateRef} from '@angular/core';
import {TokenStorageService} from "./service/token-storage.service";
import {AuthService} from "./service/auth.service";
import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import Swal from "sweetalert2";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private role: string;
  isLoggedIn = false;
  showAdminBoard = false;
  username: string;
  modalRef: BsModalRef;
  form: FormGroup;
  errorMessage = '';

  constructor(private tokenStorageService: TokenStorageService,
              private authService: AuthService,
              private router: Router,
              private modalService: BsModalService,
              private fb: FormBuilder) {

    const token = this.tokenStorageService.getToken();

    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });

    if (token) {
      this.authService.tokenvalidity(token.authToken, token.authId).subscribe(res => {
        if (res === true) {
          this.setLoginData();
        } else {
          this.logout();
        }
      })
    }
  }

  ngOnInit(): void {

  }

  logout(): void {
    this.authService.logout();
    this.tokenStorageService.signOut();
    this.setLoginData();
    this.router.navigate(['home'])
  }

  setLoginData(): void {
    const token = this.tokenStorageService.getToken();

    this.isLoggedIn = !!token;

    if (this.isLoggedIn) {
      this.role = token.authPermission;
      this.showAdminBoard = this.role.includes('ROLE_ADMIN');
      this.username = token.authId;
    } else {
      this.isLoggedIn = false;
      this.showAdminBoard = false;
      this.username = undefined;
    }
  }


  submit(): void {
    if (!this.form.valid) {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Form is not valid',
        showConfirmButton: true,
        confirmButtonText: 'Fuck'
      })
      return;
    }
    this.authService.login(this.form.value).subscribe(
      data => {
        if (data) {
          this.tokenStorageService.saveToken(data);

          this.isLoggedIn = true;
          this.setLoginData();
          this.close();
        } else {
          this.errorMessage = 'Unrecognized user or wrong password'
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Login failed',
            text: this.errorMessage,
            showConfirmButton: true,
            confirmButtonText: 'Fuck'
          })
        }
      },
      err => {
        this.errorMessage = err.error.message;
      }
    );
  }

  open(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  close(): void {
    this.modalRef.hide();
  }
}
