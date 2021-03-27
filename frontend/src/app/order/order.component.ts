import {Component, OnInit} from '@angular/core';
import {Order, OrderService} from "../service/order.service";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  orders$: Observable<Order[]>;

  constructor(private route: ActivatedRoute,
              private orderService: OrderService) {
  }

  ngOnInit(): void {

    this.orders$ = this.orderService.get(null);

  }

}
