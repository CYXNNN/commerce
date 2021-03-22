import {Component, OnInit} from '@angular/core';
import {OrderService, PlacedOrder} from "../service/order.service";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CartService, Orderable} from "../service/cart.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  products: Orderable[];
  order: PlacedOrder;
  form: FormGroup;
  sub: any;

  constructor(private route: ActivatedRoute,
              private cartService: CartService,
              private orderService: OrderService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {

    this.products = this.cartService.get();

    //FIXME remove test values
    this.form = this.fb.group({
      products: this.fb.array([], Validators.required),
      senderAddress: this.fb.group({
        name: ['test1', Validators.required],
        prename: ['test1', Validators.required],
        street: ['test1', Validators.required],
        number: ['test1', Validators.required],
        zip: ['test1', Validators.required],
        city: ['test1', Validators.required],
      }),
      billingAddress: this.fb.group({
        name: ['test2', Validators.required],
        prename: ['test2', Validators.required],
        street: ['test2', Validators.required],
        number: ['test2', Validators.required],
        zip: ['test2', Validators.required],
        city: ['test2', Validators.required],
      }),
      // TODO implement payment methods
      payment: 'BILL', // nothing else implemented yet
    })

    this.initProducts(this.products);
  }

  getProducts(): FormArray {
    return this.form.get('products') as FormArray;
  }

  private initProducts(productArray: Orderable[]): void {
    let products = this.form.get('products') as FormArray;

    productArray.forEach(p => {
      products.push(this.fb.group({id: p.id, name: p.name, quantity: p.amount}));
    })
  }

  submit(): void {
    if (!this.form.valid) {
      alert('form has errors');
      return;
    }
    const order = this.form.value as PlacedOrder;
    this.orderService.post(order);
  }
}
