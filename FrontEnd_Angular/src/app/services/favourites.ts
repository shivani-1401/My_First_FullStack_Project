import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {

  constructor(private httpClient: HttpClient) { }

  getFavouriteList(id: string): Observable<any> {
    return this.httpClient.get<any>(`http://localhost:8765/userservice/getAllFavourites/userId/${id}`);
  }

  addToFavouriteList(movie): Observable<any> {
    return this.httpClient.post<any>("http://localhost:8765/userservice/addFavourite", movie);
  }

  deleteFromFavouriteList(userId, movieId) {
    return this.httpClient.delete<any>(`http://localhost:8765/userservice/deleteFavourite/userId/${userId}/movieId/${movieId}`)
      .subscribe(data => {
        console.log(data);
      },
        error => {
          console.log(error);
        });
  }
}
