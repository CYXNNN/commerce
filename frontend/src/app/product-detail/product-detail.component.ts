import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../service/product.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit {
  product: any;
  sub: any;

  constructor(private route: ActivatedRoute, private productService: ProductService) {
    this.productService = productService;
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.productService.get(params['id']).subscribe(res => this.product = res
      );
    });
  }

}
