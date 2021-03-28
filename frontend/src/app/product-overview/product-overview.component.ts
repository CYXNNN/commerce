import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ProductService} from "../service/product.service";
import {SortOption} from "../util/interfaces";

@Component({
  selector: 'app-product-overview',
  templateUrl: './product-overview.component.html',
  styleUrls: ['./product-overview.component.scss']
})
export class ProductOverviewComponent implements OnInit {

  products$ = new Observable<any>();
  options = [{
    label: 'Keine Sortierung', value: 'NONE'
  }, {
    label: 'Teuerste zuerst', value: 'PRICE_DESC'
  }, {
    label: 'Billigste zuerst', value: 'PRICE_ASC'
  }, {
    label: 'Neueste zuerst', value: 'CREATION_DATE_DESC'
  }, {
    label: 'Älteste zuerst', value: 'CREATION_DATE_ASC'
  }];

  selected: SortOption = 'NONE';

  constructor(private productService: ProductService) {

  }

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.products$ = this.productService.getProducts(this.selected);
  }

}
