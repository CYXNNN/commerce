import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Observable} from "rxjs";
import {CartService, Orderable} from "../service/cart.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  products$: Observable<any>;

  constructor(private productService: ProductService,
              private cartService: CartService) {
  }

  ngOnInit(): void {
    this.products$ = this.productService.getProducts();
  }

  public addToCart(product: Orderable): void {
    this.cartService.put(product.id, product.name, 1, product.price);
  }

}
