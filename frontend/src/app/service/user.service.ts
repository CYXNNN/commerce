import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {CartService} from "./cart.service";
import {Address} from "../util/interfaces";

const BASE_URL = 'http://127.0.0.1:8080/commerce/user/v1'

@Injectable({
  providedIn: 'root',
})
export class UserService {

  constructor(private http: HttpClient, private cartService: CartService) {
  }

  getAddress(): Observable<Address> {
    return this.http.get<Address>(BASE_URL);
  }

  putAddress(address: Address): Observable<any> {
    return this.http.put<any>(BASE_URL, address);
  }
}
