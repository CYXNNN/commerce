import {Component, OnInit} from '@angular/core';
import {CartService} from "../service/cart.service";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";
import {toMoney} from "../util/utils"
import Swal from "sweetalert2";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {

  items$: any;
  itemCount: number = 0;
  orderTotal: number = 0;
  toMoney = toMoney;

  constructor(private cartService: CartService,
              private router: Router,
              private tokenService: TokenStorageService) {
    // get observable of cart service
    // so the cart will update itself on action
    this.items$ = this.cartService.observable()
    this.items$.subscribe(next => {

      if (next && next.length > 0) {

        this.itemCount = next.length;
        this.orderTotal = 0;

        next.forEach(item => {
          this.orderTotal += item.price * item.amount;
        });
      } else {
        this.itemCount = 0;
        this.orderTotal = 0;
      }
    });
  }

  ngOnInit(): void {

  }

  remove(id: string): void {
    this.cartService.remove(id);
  }

  checkout(): void {

    if (this.tokenService.getToken()) {
      this.router.navigate(['checkout'])
    } else {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Please sign in to continue',
        showConfirmButton: true,
        confirmButtonText: 'Fuck'
      })
    }

  }

}
