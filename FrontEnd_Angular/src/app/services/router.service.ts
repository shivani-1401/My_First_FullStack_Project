import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class RouterService {

  constructor(private router: Router) { }

  routeToHomePage() {
    this.router.navigate(['/homepage']);
  }

  routeToSearch() {
    this.router.navigate(['/search']);
  }

  routeToFavourites() {
    this.router.navigate(['/favourites']);
  }

  routeToLogin() {
    this.router.navigate(['/login']);
  }

  routeToRegister() {
    this.router.navigate(['/register']);
  }

}
