import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../service/product.service";
import {CartService} from "../service/cart.service";
import {Product} from "../util/interfaces";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit {
  product: Product;
  id: string;
  sub: any;

  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private cartService: CartService) {
    this.productService = productService;
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.productService.get(this.id).subscribe(res => this.product = res);
    });
  }

  public addToCart(): void {
    this.cartService.put(this.product.id, this.product.name, 1, this.product.price);
  }
}
