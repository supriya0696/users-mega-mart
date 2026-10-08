import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username = '';
  password = '';
  hidePassword = true;
  errorMessage = '';
  loading = false;

  constructor(private auth: AuthService, private router: Router) {}

  onLogin(): void {
    this.errorMessage = '';
    this.loading = true;
    this.auth.login(this.username, this.password).subscribe({
      next: () => this.router.navigate(['/products']),
      error: (error: HttpErrorResponse) => {
        this.errorMessage =
          error.status === 401
            ? 'Invalid username or password.'
            : 'Unable to sign in right now. Please check the server and try again.';
        this.loading = false;
      },
    });
  }
}
