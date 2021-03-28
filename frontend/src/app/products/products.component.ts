import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Observable} from "rxjs";
import {CartService} from "../service/cart.service";
import {Product} from "../util/interfaces";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  @Input()
  products$!: Observable<Product[]>;

  constructor(private productService: ProductService,
              private cartService: CartService) {
  }

  ngOnInit(): void {
  }


  public addToCart(product: Product): void {
    this.cartService.put(product.id, product.name, 1, product.price);
  }

}
