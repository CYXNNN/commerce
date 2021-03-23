import {Observable, of} from "rxjs";
import {Injectable} from "@angular/core";


export interface Orderable {
  id: string,
  name: string,
  amount: number,
  price: number,
}

@Injectable({
  providedIn: 'root',
})
export class CartService {

  // acting as store so the cart is basically in a global state.
  items: Orderable[] = [];

  public find(id: string): Orderable {
    return this.items.find(i => i.id === id);
  }

  public put(id: string, name: string, amount: number, price: number): void {
    const current = this.find(id) as Orderable;
    let presentAmount = 0 as number;
    if (current) {
      this.remove(id);
      presentAmount = current.amount;
    }

    // create orderable out of params passed to put
    let item: Orderable = {id, name, 'amount': (presentAmount + amount), price};

    // add item to store
    this.items.push(item);
  }

  public remove(id: string): Observable<Orderable[]> {
    this.items = this.items.filter(obj => obj.id !== id);
    return this.observable();
  }

  public reset(): void {
    this.items = [];
  }

  public observable(): Observable<Orderable[]> {
    return of(this.items);
  }

  public get(): Orderable[] {
    return this.items;
  }
}
