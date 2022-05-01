import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { User } from 'src/Modal_Classes/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http : HttpClient) { }

  public getUsers(){
    return this.http.get("https://www.google.com/");
  }

  // public save(user: User){
  //   return this.http.post<User>("localhost:8080/addUser", user);
  // }
}
