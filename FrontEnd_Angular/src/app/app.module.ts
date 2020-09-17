import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MainworkModule } from './mainwork/mainwork.module';
import { LoginModule } from './login/login.module';
import { TrendingService } from './services/trending.service';
import { SearchService } from './services/search.service';
import { FavouritesService } from './services/favourites';
import { AuthserviceService } from './services/authservice.service';
import { RouterService } from './services/router.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MainworkModule,
    LoginModule
  ],
  providers: [SearchService, TrendingService, FavouritesService, AuthserviceService, RouterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
