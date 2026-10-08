import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { finalize, timeout } from 'rxjs';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatTooltipModule,
  ],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filtered: Product[] = [];
  displayedColumns = ['id', 'productName', 'category', 'price', 'status', 'actions'];
  loading = true;
  loadError = '';
  searchTerm = '';

  get activeCount(): number {
    return this.products.filter(p => p.status === 'Active').length;
  }

  get categoryCount(): number {
    return new Set(this.products.map(p => p.category)).size;
  }

  constructor(
    private productService: ProductService,
    public auth: AuthService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading = true;
    this.loadError = '';
    this.productService
      .getAll()
      .pipe(
        timeout(15000),
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe({
        next: data => {
          this.products = data;
          this.filtered = data;
        },
        error: error => {
          this.loadError =
            error.name === 'TimeoutError'
              ? 'The product request timed out. Check that the backend is running and try again.'
              : error.status === 401 || error.status === 403
                ? 'Your session has expired. Sign in again to view products.'
                : 'Could not load products. Check your connection and try again.';
        },
      });
  }

  applyFilter(): void {
    const term = this.searchTerm.toLowerCase().trim();
    this.filtered = term
      ? this.products.filter(
          p =>
            p.productName.toLowerCase().includes(term) ||
            p.category.toLowerCase().includes(term) ||
            p.price.toLowerCase().includes(term) ||
            p.status.toLowerCase().includes(term)
        )
      : this.products;
  }

  deleteProduct(id: number): void {
    if (!confirm('Delete this product?')) return;
    this.productService.delete(id).subscribe({
      next: () => {
        this.snackBar.open('Product deleted', 'Close', { duration: 2000 });
        this.loadProducts();
      },
      error: () => this.snackBar.open('Delete failed', 'Close', { duration: 3000 }),
    });
  }

  isAdmin(): boolean {
    return this.auth.hasRole('ROLE_ADMIN');
  }

  isOperator(): boolean {
    return this.auth.hasRole('ROLE_OPERATOR') || this.isAdmin();
  }

  logout(): void {
    this.auth.logout();
  }
}
