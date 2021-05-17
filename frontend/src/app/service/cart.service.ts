import {BehaviorSubject, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Orderable} from "../util/interfaces";

@Injectable({
  providedIn: 'root',
})
export class CartService {

  // acting as store so the cart is basically in a global state.
  items: Orderable[] = [];
  items$ = new BehaviorSubject<Orderable[]>([]);

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
    this.items$.next(this.items);
  }

  public remove(id: string): void {
    this.items = this.items.filter(obj => obj.id !== id);
    this.items$.next(this.items);
  }

  public reset(): void {
    this.items = [];
    this.items$.next(this.items);
  }

  public observable(): Observable<Orderable[]> {
    debugger;
    this.items$.next(this.items);
    return this.items$;
  }

  public get(): Orderable[] {
    return this.items;
  }
}
