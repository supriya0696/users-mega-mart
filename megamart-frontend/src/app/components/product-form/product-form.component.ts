import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatIconModule,
    MatSnackBarModule,
  ],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent implements OnInit {
  product: Product = { id: 0, productName: '', category: '', price: '', status: 'Active' };
  isEdit = false;
  loading = false;
  saving = false;

  nameFocus = false;
  priceFocus = false;

  categories = ['Electronics', 'Clothing', 'Groceries', 'Home & Garden', 'Toys', 'Sports', 'Other'];
  statuses = ['Active', 'Inactive'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.loading = true;
      this.productService.getById(+id).subscribe({
        next: p => {
          this.product = p;
          this.loading = false;
        },
        error: () => {
          this.snackBar.open('Failed to load product', 'Close', { duration: 3000 });
          this.router.navigate(['/products']);
        },
      });
    }
  }

  onSave(): void {
    this.saving = true;
    const operation = this.isEdit
      ? this.productService.update(this.product)
      : this.productService.create(this.product);

    operation.subscribe({
      next: () => {
        this.snackBar.open(`Product ${this.isEdit ? 'updated' : 'created'} successfully`, 'Close', {
          duration: 2000,
        });
        this.router.navigate(['/products']);
      },
      error: () => {
        this.snackBar.open('Save failed', 'Close', { duration: 3000 });
        this.saving = false;
      },
    });
  }
}
