import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

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

export interface OrderProduct {
  id: string,
  quantity: number,
}

export interface PlacedOrder {
  id: string,
  creationDate: Date,
  products: OrderProduct[],
  billingAddress: Address,
  senderAddress: Address,
}

@Injectable({
  providedIn: 'root',
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  getOrders(): Observable<PlacedOrder[]> {
    // now returns an Observable of Config
    return this.http.get<PlacedOrder[]>(BASE_URL);
  }

  get(userId: string): Observable<PlacedOrder> {
    // now returns an Observable of Config
    return this.http.get<PlacedOrder>(BASE_URL + '/' + userId);
  }

  post(order: PlacedOrder): void {
    // now returns an Observable of Config
    this.http.post<any>(BASE_URL, order).subscribe();
  }
}
