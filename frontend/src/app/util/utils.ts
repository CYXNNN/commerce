import {Address} from "../service/order.service";

export function toMoney(amount: number): string {
  return amount.toLocaleString('de-CH', {style: 'currency', currency: 'CHF'})
}

export function toAddressString(address: Address): string {
  return `${address.prename} ${address.name}, ${address.street} ${address.number}, ${address.zip} ${address.city}`;
}
