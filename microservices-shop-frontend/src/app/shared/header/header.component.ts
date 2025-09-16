import {Component, inject, OnInit} from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  private readonly oidcSecurityService = inject(OidcSecurityService);
  isAuthenticated = false;
  username = "";

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(
      ({isAuthenticated}) => {
        this.isAuthenticated = isAuthenticated;
        console.log('Authentication status:', isAuthenticated);
      }
    );

    this.oidcSecurityService.userData$.subscribe(
      (userData) => {
        console.log('User data received:', userData);
        if (userData) {
          // Use type assertion to access custom claims
          const userClaims = userData as any;
          this.username = userClaims.preferred_username || 
                         userClaims.name || 
                         userClaims.sub || 
                         'Unknown';
        } else {
          console.log('User data is null');
          this.username = '';
        }
      }
    );

    this.oidcSecurityService.checkAuth().subscribe({
      next: (authResult) => {
        console.log('Auth check result:', authResult);
      },
      error: (error) => {
        console.error('Auth check error:', error);
      }
    });
  }

  login(): void {
    this.oidcSecurityService.authorize();
  }

  logout(): void {
    this.oidcSecurityService
      .logoff()
      .subscribe((result) => console.log(result));
  }
}