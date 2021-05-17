import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Product, SortOption} from "../util/interfaces";

const BASE_URL = 'http://127.0.0.1:8080/commerce/product/v1'


@Injectable({
  providedIn: 'root',
})
export class ProductService {

  constructor(private http: HttpClient) {
  }


  getProducts(sortOption: SortOption): Observable<Product[]> {
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

  post(product: Product): Observable<Product> {
    // now returns an Observable of Config
    return this.http.post<Product>(BASE_URL, product);
  }

  put(product: Product): Observable<Product> {
    // now returns an Observable of Config
    return this.http.put<Product>(BASE_URL, product);
  }

  delete(id: string): Observable<any> {
    return this.http.delete(BASE_URL + '/' + id);
  }

  upload(file: FormData, id: string): Observable<any> {
    return this.http.post(BASE_URL + '/image/' + id, file);
  }

  getImage(id: string): Observable<any> {
    return this.http.get(BASE_URL + '/image/' + id, {responseType: 'blob'});
  }
}
