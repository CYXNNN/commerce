import {Component, OnInit} from '@angular/core';
import {CartService, Orderable} from "../service/cart.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  items$: Observable<Orderable[]> | undefined;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    // get observable of cart service
    // so the cart will update itself on action
    this.items$ = this.cartService.observable();
  }

  remove(id: string): void {
    this.items$ = this.cartService.remove(id);
  }

}
