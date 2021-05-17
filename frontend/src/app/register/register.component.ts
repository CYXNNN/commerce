import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import Swal from "sweetalert2";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;

  constructor(private authService: AuthService,
              private router: Router,
              private fb: FormBuilder) {

    this.form = this.fb.group({
      username: [undefined, Validators.required],
      email: [undefined, [Validators.required, Validators.email]],
      password: [undefined, Validators.required],
    });
  }

  ngOnInit(): void {
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
    this.authService.register(this.form.value).subscribe(
      data => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'You are now registered',
          text: 'Press login from the navigation in order to sign in',
          showConfirmButton: false,
          timer: 2000
        })
        this.router.navigate(['home']);
      });
  }

}
