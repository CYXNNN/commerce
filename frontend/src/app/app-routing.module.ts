import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductsComponent} from "./products/products.component";
import {ProductDetailComponent} from "./product-detail/product-detail.component";
import {CartComponent} from "./cart/cart.component";
import {OrderComponent} from "./order/order.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ProfileComponent} from "./profile/profile.component";
import {HomeComponent} from "./home/home.component";


const routes: Routes = [
  {path: 'products', component: ProductsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'products/:id', component: ProductDetailComponent},
  {path: 'checkout', component: OrderComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'home', component: HomeComponent},
  {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
