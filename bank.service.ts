import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  private apiUrl = 'http://localhost:8080/api/bank';

  constructor(private http: HttpClient) { }

  transferFunds(fromAccountId: number, toAccountId: number, amount: number): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/transfer`, null, {
      params: {
        fromAccountId: fromAccountId.toString(),
        toAccountId: toAccountId.toString(),
        amount: amount.toString()
      }
    });
  }
}
