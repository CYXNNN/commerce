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

  constructor(private http: HttpClient, private cartService: CartService) {
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
