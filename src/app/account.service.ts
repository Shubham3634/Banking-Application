import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from './account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient:HttpClient) { }


private baseUrl="http://localhost:8080/api/accounts"

getAllAccounts():Observable<Account[]>{

return this.httpClient.get<Account[]>(`${this.baseUrl}`);
}

createAccount(account:Account):Observable<Account>{
return this.httpClient.post<Account>(`${this.baseUrl}`, account)

}

getAccountById(id:number):Observable<Account>{


  return this.httpClient.get<Account>(`${this.baseUrl}/${id}`)
}

deposit(id:number, amount:number):Observable<Account>{

  const request = {amount}

return this.httpClient.put<Account>(`${this.baseUrl}/${id}/deposit`, request);
}

WithdrawComponent(id:number, amount:number):Observable<Account>{

  const request = {amount}

  return this.httpClient.put<Account>(`${this.baseUrl}/${id}/withdraw`, request)
}

delete(id:number):Observable<Account>{

return this.httpClient.delete<Account>(`${this.baseUrl}/${id}`)

}


}
