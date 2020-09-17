import { Component, OnInit } from '@angular/core';
import { TrendingService } from 'src/app/services/trending.service';
import { AuthserviceService } from 'src/app/services/authservice.service';
import { RouterService } from 'src/app/services/router.service';
import { FavouritesService } from 'src/app/services/favourites';
import { Movie } from 'src/app/movie';



@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  errMessage: string;
  trendingMovies: Movie[] = [];
  movie1: Movie;

  constructor(private trendingService: TrendingService,
    private authService: AuthserviceService,
    private routerService: RouterService,
    private favouritesService: FavouritesService) { }

  ngOnInit() {
    this.trendingService.getAllTrendingMovies()
      .then(response => this.trendingMovies = response);
    // this.trendingService.getAllTrendingMovies()
    // .subscribe(
    // data => {
    // this.trendingMovies = data;
    // },
    // error => {
    // this.errMessage = error.message;
    // });
  }

  add(movie) {
    if (this.authService.isUserAuthenticated()) {
      this.movie1 = movie;
      this.movie1.movieAddedBy = this.authService.getUserId();
      this.favouritesService.addToFavouriteList(movie).subscribe(data => {
        console.log(data);
      },
        error => {
          this.errMessage = error;
          movie.invalid = true;
        });
    }
    else {
      this.routerService.routeToLogin();
    }
  }
}


