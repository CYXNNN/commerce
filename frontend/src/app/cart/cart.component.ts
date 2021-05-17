import {Component, OnInit} from '@angular/core';
import {CartService, Orderable} from "../service/cart.service";
import {Observable} from "rxjs";
import {TokenStorageService} from "../service/token-storage.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  items$: Observable<Orderable[]> | undefined;

  constructor(private cartService: CartService,
              private router: Router,
              private tokenService: TokenStorageService) {
  }

  ngOnInit(): void {
    // get observable of cart service
    // so the cart will update itself on action
    this.items$ = this.cartService.observable();
  }

  remove(id: string): void {
    this.items$ = this.cartService.remove(id);
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
