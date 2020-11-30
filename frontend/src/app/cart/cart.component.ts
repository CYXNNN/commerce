import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  items: Map<string, number> = new Map<string, number>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public put(id: string, amount: number): void {
    const current = this.items.get(id);
    if (current) {
      this.items.set(id, amount + current);
    }
    this.items.set(id, amount);
  }

  public remove(id: string): void {
    this.items.delete(id);
  }

}
