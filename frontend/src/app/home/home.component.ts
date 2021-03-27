import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  newest$ = new Observable<any>();

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.newest$ = this.productService.getNewest(4);
  }

}
