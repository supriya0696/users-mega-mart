import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatRippleModule } from '@angular/material/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, MatIconModule, MatRippleModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  features = [
    { icon: 'inventory_2',   title: 'Smart Inventory',    desc: 'Real-time stock tracking across every category — never miss a restock.' },
    { icon: 'verified',      title: 'Quality Assured',    desc: 'Every listed product passes our in-house quality review process.' },
    { icon: 'local_shipping', title: 'Fast Fulfilment',   desc: 'From warehouse to customer in under 48 hours — nationwide.' },
    { icon: 'support_agent', title: '24 / 7 Support',     desc: 'Dedicated support team available around the clock for all queries.' },
    { icon: 'bar_chart',     title: 'Live Analytics',     desc: 'Dashboards and reports that give you actionable business insights.' },
    { icon: 'lock',          title: 'Secure Platform',    desc: 'JWT-secured API, role-based access control, and encrypted storage.' },
  ];

  stats = [
    { value: '10 K+', label: 'Products Listed' },
    { value: '500+',  label: 'Suppliers Onboarded' },
    { value: '2 M+',  label: 'Orders Fulfilled' },
    { value: '99.9%', label: 'Platform Uptime' },
  ];

  year = new Date().getFullYear();
}
