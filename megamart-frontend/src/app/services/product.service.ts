import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

const API_URL = '/api/product';

@Injectable({ providedIn: 'root' })
export class ProductService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(API_URL);
  }

  getById(id: number): Observable<Product> {
    return this.http.get<Product>(`${API_URL}/${id}`);
  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(API_URL, product);
  }

  update(product: Product): Observable<Product> {
    return this.http.put<Product>(API_URL, product);
  }

  delete(id: number): Observable<string> {
    return this.http.delete<string>(`${API_URL}/${id}`, { responseType: 'text' as 'json' });
  }
}
