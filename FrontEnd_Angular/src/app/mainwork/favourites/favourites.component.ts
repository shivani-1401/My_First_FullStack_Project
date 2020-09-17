import { Component, OnInit } from '@angular/core';
import { AuthserviceService } from 'src/app/services/authservice.service';
import { FavouritesService } from 'src/app/services/favourites';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})

export class FavouritesComponent implements OnInit {
  favouriteList = [];
  errMessage: string;
  constructor(private favouriteService: FavouritesService, private authService: AuthserviceService) { }

  ngOnInit() {
    this.favouriteService.getFavouriteList(this.authService.getUserId())
      .subscribe(
        data => {
          this.favouriteList = data;
        },
        error => {
          this.errMessage = error.message;
        }
      );

  }

  deleteFromFavourites(userId, movieId) {
    this.favouriteService.deleteFromFavouriteList(userId, movieId);
    this.favouriteList = this.favouriteList.filter((elem) => {
      return elem.id !== movieId;
    });
  }
}



