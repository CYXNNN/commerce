import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";

const BASE_URL = 'http://127.0.0.1:8080/commerce/product/v1'
const options = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
  })
};

export interface Product {
  id: string,
  creationDate: Date,
  name: string,
  description: string,
  price: number,
  stock: number,
}

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  filter = '';

  constructor(private http: HttpClient) {
  }

  getProducts(sortOption: string): Observable<Product[]> {
    // now returns an Observable of Config
    return this.http.get<Product[]>(BASE_URL + "/all/" + sortOption);
  }

  getNewest(limit: number): Observable<Product[]> {
    // now returns an Observable of Config
    return this.http.get<Product[]>(BASE_URL + '/newest/' + limit);
  }

  get(id: string): Observable<Product> {
    // now returns an Observable of Config
    return this.http.get<Product>(BASE_URL + '/' + id);
  }

  post(product: any): Observable<any> {
    // now returns an Observable of Config
    return this.http.post<any>(BASE_URL, product, options);
  }

  put(product: any): Observable<any> {
    // now returns an Observable of Config
    return this.http.put<any>(BASE_URL, product, options);
  }
}
