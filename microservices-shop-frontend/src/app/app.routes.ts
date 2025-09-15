import { Routes } from '@angular/router';
import { HomePageComponent } from './features/homepage/homepage.component';
import { AddProductComponent } from './features/product/add-product/add-product.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'add-product', component: AddProductComponent },
];
