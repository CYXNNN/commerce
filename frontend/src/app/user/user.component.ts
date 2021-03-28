import {Component, OnInit} from '@angular/core';
import {OrderService} from "../service/order.service";
import {Observable} from "rxjs";
import {toAddressString, toMoney} from "../util/utils"
import {Order} from "../util/interfaces";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  orders$: Observable<Order[]>;
  toMoney = toMoney;
  toAddressString = toAddressString;

  constructor(private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.orders$ = this.orderService.get(null);
  }
}
