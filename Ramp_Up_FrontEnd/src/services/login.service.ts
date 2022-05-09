import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public login(username: string, password: string){
    const queryString = `?username=${username}&password=${password}`;
    console.log(queryString);
    
    return this.http.get("http://localhost:8080/login"+queryString);
  }
}
