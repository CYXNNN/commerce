import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import Swal from "sweetalert2";
import {CartService} from "./cart.service";

const BASE_URL = 'http://127.0.0.1:8080/commerce/order/v1'

export interface Address {
  id: string,
  prename: string,
  name: string,
  street: string,
  number: string,
  zip: string,
  city: string,

}

export interface OrderItemCreation {
  id: string,
  quantity: number,
}

export interface OrderItem extends OrderItemCreation {
  description: string,
  name: string,
  price: number,
  unitPrice: number,
  productId: string,
}

export enum Shipment {
  WAITING = 'Waiting',
  ON_THE_WAY = 'On the way',
  DELIVERED = 'Delivered'
}

export enum Payment {
  CREDIT_CARD = 'Credit Card',
  PRE_PAYMENT = 'Pre payment',
  BILL = 'Bill'
}

export interface OrderBase {
  id: string,
  creationDate: Date,
  billingAddress: Address,
  senderAddress: Address,
}

export interface OrderCreation extends OrderBase {
  products: OrderItemCreation[],
}

export interface Order extends OrderBase {
  shipment: Shipment,
  payment: Payment,
  items: OrderItem[],
  modificationDate: Date,
  orderTotal: number,
}

@Injectable({
  providedIn: 'root',
})
export class OrderService {

  constructor(private http: HttpClient, private cartService: CartService) {
  }

  getOrders(): Observable<OrderCreation[]> {
    // now returns an Observable of Config
    return this.http.get<OrderCreation[]>(BASE_URL);
  }

  get(userId: string): Observable<Order[]> {
    // FIXME remove user id
    userId = '4028b881785bcc1e01785bcc54210000';
    // now returns an Observable of Config
    return this.http.get<Order[]>(BASE_URL + '/' + userId);
  }

  post(order: OrderCreation): void {
    // now returns an Observable of Config
    this.http.post<any>(BASE_URL, order).subscribe(_ => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Order has been placed',
        showConfirmButton: false,
        timer: 2000
      })
      this.cartService.reset();
    });
  }
}
