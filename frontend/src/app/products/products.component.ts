import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Observable} from "rxjs";
import {CartService} from "../service/cart.service";
import {Product} from "../util/interfaces";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  @Input()
  products$!: Observable<Product[]>;

  constructor(private productService: ProductService,
              private cartService: CartService,
              private domSanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
  }

  public renderImage(product: Product): any {
    return this.domSanitizer.bypassSecurityTrustResourceUrl('data:' + product.fileType + ';base64,' + product.picture);
  }

  addToCart(product: Product): void {
    this.cartService.put(product.id, product.name, 1, product.price);
  }

}
