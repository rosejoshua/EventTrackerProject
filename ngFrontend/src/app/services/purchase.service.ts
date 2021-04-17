import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Purchase } from '../models/purchase';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  private baseUrl = 'http://localhost:8084/';
  private url = this.baseUrl + 'api/purchases';
  private url2 = this.baseUrl + 'api/purchase';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Purchase[]> {

    return this.http.get<Purchase[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in index()' + err);
      })
    );
  }

}
