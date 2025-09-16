import { Component, OnInit, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './shared/header/header.component';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'microservices-shop-frontend';
  
  private readonly oidcSecurityService = inject(OidcSecurityService);

  ngOnInit(): void {
    // Initialize the authentication service
    this.oidcSecurityService.checkAuth().subscribe({
      next: (authResult) => {
        console.log('App initialization - Auth result:', authResult);
      },
      error: (error) => {
        console.error('App initialization - Auth error:', error);
      }
    });
  }
}