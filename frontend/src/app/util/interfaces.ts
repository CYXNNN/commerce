export interface Persistence {
  id: string,
  modificationDate: Date,
  creationDate: Date
}


export interface Address extends Persistence {
  prename: string,
  name: string,
  street: string,
  number: string,
  zip: string,
  city: string,

}

export interface OrderItemCreation {
  id: string,
  quantity: number,
}

export interface OrderItem extends OrderItemCreation {
  description: string,
  name: string,
  price: number,
  unitPrice: number,
  productId: string,
}

export enum Shipment {
  WAITING = 'Waiting',
  ON_THE_WAY = 'On the way',
  DELIVERED = 'Delivered'
}

export enum Payment {
  CREDIT_CARD = 'Credit Card',
  PRE_PAYMENT = 'Pre payment',
  BILL = 'Bill'
}

export enum ProductCategory {
  FOOD = 'FOOD',
  DRINK = 'DRINK'
}

interface OrderBase extends Persistence {
  billingAddress: Address,
  senderAddress: Address,
}

export interface OrderCreation extends OrderBase {
  products: OrderItemCreation[],
}

export interface Order extends OrderBase {
  shipment: Shipment,
  payment: Payment,
  items: OrderItem[],
  modificationDate: Date,
  orderTotal: number,
}

export interface Product extends Persistence {
  name: string,
  description: string,
  price: number,
  stock: number,
  category: ProductCategory,
  deleted: boolean,
}
