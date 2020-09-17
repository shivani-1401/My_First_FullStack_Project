import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TrendingService {

  urlTrending = "http://localhost:8765/trendingservice/trendingMovies";

  constructor(private httpClient: HttpClient) { }

  getAllTrendingMovies() {
    return fetch(this.urlTrending).then(response => response.json());
    // return this.httpClient.get<any>(this.urlTrending)
    // .pipe(map((response:Response)=>{
    //   return response.json();
    // }))
  }
}
