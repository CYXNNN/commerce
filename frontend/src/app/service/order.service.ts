import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {CartService} from "./cart.service";
import {Order, OrderCreation} from "../util/interfaces";

const BASE_URL = 'http://127.0.0.1:8080/commerce/order/v1'

@Injectable({
  providedIn: 'root',
})
export class OrderService {

  constructor(private http: HttpClient, private cartService: CartService) {
  }

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(BASE_URL);
  }

  get(userId: string): Observable<Order[]> {
    // FIXME remove user id
    userId = '4028b881785bcc1e01785bcc54210000';
    return this.http.get<Order[]>(BASE_URL + '/' + userId);
  }

  post(order: OrderCreation): Observable<any> {
    return this.http.post<any>(BASE_URL, order);
  }
}
