import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FavouritesComponent } from './mainwork/favourites/favourites.component';
import { SearchComponent } from './mainwork/search/search.component';
import { LoginCompComponent } from './login/login-comp/login-comp.component';
import { HomepageComponent } from './mainwork/homepage/homepage.component';
import { AuthenticationGuard } from './authentication.guard';
import { RegisterComponent } from './login/register/register.component';

const routes: Routes = [{ path: '', redirectTo: '/homepage', pathMatch: 'full' },
{ path: 'search', component: SearchComponent },
{ path: 'favourites', component: FavouritesComponent, canActivate: [AuthenticationGuard] },
{ path: 'login', component: LoginCompComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'homepage', component: HomepageComponent }];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
