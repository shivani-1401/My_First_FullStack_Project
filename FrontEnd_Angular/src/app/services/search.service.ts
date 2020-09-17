import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../movie';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  errMessage: string;
  public keyword;

  constructor(private httpclient: HttpClient) { }

  getSearchedMovies(keyword) {
    return fetch(`http://localhost:8765/catalogservice/movieTitle/${keyword}`)
      .then(response => response.json());
  }

}
