import {Component, OnInit, TemplateRef} from '@angular/core';
import {Observable} from "rxjs";
import {Order, Product, ProductCategory} from "../util/interfaces";
import {ProductService} from "../service/product.service";
import {OrderService} from "../service/order.service";
import {toAddressString, toMoney} from '../util/utils';
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import Swal from "sweetalert2";
import {faEdit, faPlus, faTimes} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  products$: Observable<Product[]>;
  orders$: Observable<Order[]>;
  users$: Observable<any>;

  categories = [
    {label: 'Food', value: ProductCategory.FOOD},
    {label: 'Drink', value: ProductCategory.DRINK}
  ]

  toMoney = toMoney;
  toAddressString = toAddressString;
  faCross = faTimes;
  faEdit = faEdit;
  faPlus = faPlus;
  modalRef: BsModalRef;
  productForm: FormGroup;

  constructor(private productService: ProductService,
              private orderService: OrderService,
              private modalService: BsModalService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.fetchProducts();
    this.orders$ = this.orderService.getOrders();

    this.modalService.onHidden.subscribe((reason: string | any) => {
      this.productForm.reset();
    })

    this.productForm = this.fb.group({
      id: undefined,
      name: [undefined,
        [Validators.required,
          Validators.minLength(3),
          Validators.maxLength(255)]],
      description: [undefined,
        [Validators.required,
          Validators.minLength(10),
          Validators.maxLength(2000)]],
      price: [undefined,
        [Validators.required,
          Validators.min(0)]],
      stock: [undefined,
        [Validators.required,
          Validators.min(0)]],
      // FIXME hardcoded
      category: [undefined, Validators.required]
    })
  }

  open(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  saveProduct(): void {
    if (!this.productForm.valid) {
      Swal.fire({
        position: 'center',
        icon: 'error',
        title: 'Form is not valid',
        showConfirmButton: true,
        confirmButtonText: 'Fuck'
      })
      return;
    }
    const product = this.productForm.value as Product;

    if (!product.id) {
      this.productService.post(product).subscribe(_ => this.fetchProducts());
    } else {
      this.productService.put(product).subscribe(_ => this.fetchProducts());
    }

    this.close();
  }

  editProduct(product: Product, template: TemplateRef<any>): void {
    this.productForm.patchValue(product);
    this.open(template)
  }

  removeProduct(id: string): void {
    this.productService.delete(id).subscribe(_ => this.fetchProducts());
  }

  close(): void {
    this.modalRef.hide();
  }

  newUser(): void {

  }

  fetchProducts(): void {
    this.products$ = this.productService.getProducts('CREATION_DATE_DESC');
  }

}
