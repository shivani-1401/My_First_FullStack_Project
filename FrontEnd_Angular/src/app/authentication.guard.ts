import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthserviceService } from './services/authservice.service';
import { RouterService } from './services/router.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private authService: AuthserviceService, private routerService: RouterService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): any {
    if (this.authService.isUserAuthenticated()) {
      return true;
    } else {
      this.routerService.routeToLogin();
      return false;
    }
  }
}
