import { Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./components/home/home.component').then(m => m.HomeComponent),
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./components/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'products',
    canActivate: [AuthGuard],
    loadComponent: () =>
      import('./components/product-list/product-list.component').then(
        m => m.ProductListComponent
      ),
  },
  {
    path: 'products/new',
    canActivate: [AuthGuard],
    loadComponent: () =>
      import('./components/product-form/product-form.component').then(
        m => m.ProductFormComponent
      ),
  },
  {
    path: 'products/edit/:id',
    canActivate: [AuthGuard],
    loadComponent: () =>
      import('./components/product-form/product-form.component').then(
        m => m.ProductFormComponent
      ),
  },
  { path: '**', redirectTo: '' },
];
