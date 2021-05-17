import {Component, OnInit} from '@angular/core';
import {OrderService} from "../service/order.service";
import {Observable} from "rxjs";
import {toAddressString, toMoney} from "../util/utils"
import {Address, Order} from "../util/interfaces";
import {UserService} from "../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  orders$: Observable<Order[]>;
  address$: Observable<Address>;

  form: FormGroup;
  toMoney = toMoney;
  toAddressString = toAddressString;

  constructor(private orderService: OrderService,
              private userService: UserService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private fb: FormBuilder) {

    this.form = this.fb.group({
      name: ['', Validators.required],
      prename: ['', Validators.required],
      street: ['', Validators.required],
      number: ['', Validators.required],
      zip: ['', Validators.required],
      city: ['', Validators.required],
    });

  }

  ngOnInit(): void {

    if (!this.tokenStorageService.getToken()) {
      this.router.navigate(['home']);
      return;
    }

    this.orders$ = this.orderService.get();
    this.address$ = this.userService.getAddress();

    this.address$.subscribe(next => {
      this.form.patchValue(next);
    })

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
    this.userService.putAddress(this.form.value).subscribe();
  }
}
