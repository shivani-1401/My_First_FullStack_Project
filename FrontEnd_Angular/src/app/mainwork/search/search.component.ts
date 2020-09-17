import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { FavouritesService } from 'src/app/services/favourites';
import { AuthserviceService } from 'src/app/services/authservice.service';
import { RouterService } from 'src/app/services/router.service';
import { Movie } from 'src/app/movie';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  errMessage: string;
  searchedMovies: Movie[] = [];
  movie1: Movie;

  constructor(private searchService: SearchService, private favouritesService: FavouritesService, private authService: AuthserviceService, private routerService: RouterService) { }

  ngOnInit() {
  }

  getMoviesByTitle(value) {
    this.searchService.getSearchedMovies(value)
      .then(response => this.searchedMovies = response);
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
