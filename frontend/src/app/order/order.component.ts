import {Component, OnInit} from '@angular/core';
import {OrderService} from "../service/order.service";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CartService, Orderable} from "../service/cart.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderCreation} from "../util/interfaces";
import Swal from "sweetalert2";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  products: Orderable[];
  order: OrderCreation;
  form: FormGroup;

  constructor(private route: ActivatedRoute,
              private cartService: CartService,
              private orderService: OrderService,
              private fb: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void {

    this.products = this.cartService.get();

    //FIXME set user default addresses for easier use
    this.form = this.fb.group({
      products: this.fb.array([
        this.fb.group({
          id: [undefined, Validators.required],
          name: [undefined, Validators.required],
          quantity: [undefined, [Validators.required, Validators.min(1)]]
        })
      ]),
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
      payment: ['CREDIT_CARD', Validators.required]
    })

    this.initProducts(this.products);
  }

  getProducts(): FormArray {
    return this.form.get('products') as FormArray;
  }

  private initProducts(productArray: Orderable[]): void {
    let products = this.form.get('products') as FormArray;
    products.clear();
    productArray.forEach(p => {
      products.push(this.fb.group({id: p.id, name: p.name, quantity: p.amount}));
    })
  }

  submit(): void {
    if (!this.form.valid) {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Form is not valid',
        showConfirmButton: true,
        confirmButtonText: 'Fuck'
      })
      return;
    }
    const order = this.form.value as OrderCreation;
    this.orderService.post(order).subscribe(_ => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Order has been placed',
        showConfirmButton: false,
        timer: 2000
      })
      this.router.navigate(['user']);
      this.cartService.reset();
    });
  }
}
