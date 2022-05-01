import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public fb: FormBuilder) { }

  loginForm = this.fb.group({
    username: [null, [Validators.required]],
    password: [null, [Validators.required]]
  });

  ngOnInit(): void {
  }

  loginUser(){
    console.log("user Logged In"); 
  }

}
