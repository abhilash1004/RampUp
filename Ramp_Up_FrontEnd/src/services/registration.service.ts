import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { User } from 'src/Modal_Classes/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http : HttpClient) { }

  public getUsers(){
    return this.http.get("http://localhost:8080/getUsers");
  }

  public addUser(user: User){
    const headers = { 'content-type': 'application/json'}  
    return this.http.post<User>("http://localhost:8080/addUser", user, {'headers':headers});
  }
}
