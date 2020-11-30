import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  productService: ProductService;
  products$: Observable<any>;

  constructor(productService: ProductService) {
    this.productService = productService;
  }

  ngOnInit(): void {
    this.products$ = this.productService.getProducts();
  }

}
