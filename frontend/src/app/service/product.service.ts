import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";

const BASE_URL = 'http://127.0.0.1:8080/commerce/product/v1'
const options = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
  })
};

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<any> {
    // now returns an Observable of Config
    return this.http.get<any>(BASE_URL);
  }

  get(id: string): Observable<any> {
    // now returns an Observable of Config
    return this.http.get<any>(BASE_URL + '/' + id);
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
