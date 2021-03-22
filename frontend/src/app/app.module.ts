import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ProductsComponent} from './products/products.component';
import {HttpClientModule} from "@angular/common/http";
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {CartComponent} from './cart/cart.component';
import {OrderComponent} from './order/order.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ProfileComponent} from './profile/profile.component';
import {HomeComponent} from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    ProductDetailComponent,
    CartComponent,
    OrderComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
