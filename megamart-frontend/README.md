# MegaMart Angular Frontend

Angular 21 + Angular Material frontend for the MegaMart Spring Boot backend.

## Prerequisites
- Node.js 18+
- Angular CLI 21 (`npm install -g @angular/cli`)
- Spring Boot backend running on **port 8081**

## Running in development

```bash
cd megamart-frontend
npm install
ng serve
```

The dev server starts on **http://localhost:4200** and proxies all `/api/**` requests to `http://localhost:8081` via `proxy.conf.json`.
For the sample accounts seeded by `sql-scripts/05-setup-spring-security-demo-database-bcrypt.sql`, use password `test123`:
`supriya` (USER), `amit` (USER/OPERATOR), or `anand` (USER/OPERATOR/ADMIN).

## Building for production

```bash
ng build
```

Output is placed in `dist/megamart-frontend/browser/`. You can serve these static files from any web server or copy them into the Spring Boot `src/main/resources/static/` folder (rename `index.html` carefully to avoid Thymeleaf conflicts).

## Features & Role-based UI

| Feature | USER | OPERATOR | ADMIN |
|---|---|---|---|
| View product list | ✅ | ✅ | ✅ |
| Edit a product | — | ✅ | ✅ |
| Add / delete product | — | — | ✅ |

Roles are read from the JWT token returned by `/api/auth/login`.

## Architecture

```
src/app/
├── components/
│   ├── login/           # Login page
│   ├── product-list/    # Product table with CRUD actions
│   └── product-form/    # Add / edit product form
├── guards/
│   └── auth.guard.ts    # Redirects unauthenticated users to /login
├── interceptors/
│   └── jwt.interceptor.ts  # Attaches Bearer token to every request
├── models/
│   └── product.model.ts
└── services/
    ├── auth.service.ts      # Login, logout, token, role helpers
    └── product.service.ts   # CRUD calls to /api/product
```
