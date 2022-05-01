import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/Modal_Classes/user';
import { RegistrationService } from 'src/services/registration.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  users: any;

  constructor(private http : HttpClient) { 
  }

  ngOnInit(): void {
    // this.registration.getUsers().subscribe((data) => this.users=data);
    // this.registration.getUsers().subscribe((data) => console.log(data));
    let response = this.http.get("localhost:8080/getUsers");
    response.subscribe(data =>{
      this.users = data;
      console.log(data);
    });
  }

  // onSubmit(){
  //   this.registration.save(this.user)
  // }

}
