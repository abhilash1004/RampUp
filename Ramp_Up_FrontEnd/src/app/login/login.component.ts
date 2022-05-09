import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public fb: FormBuilder,
              private login: LoginService) { }

  loginForm = this.fb.group({
    username: [null, [Validators.required]],
    password: [null, [Validators.required]]
  });

  ngOnInit(): void {
  }

  loginUser(){
    console.log(this.loginForm.value);
    const uname = this.loginForm.get('username')?.value;
    const password = this.loginForm.get('password')?.value;
    const response = this.login.login(uname, password);
    response.subscribe((data) => console.log(data));
    this.loginForm.reset();
  }

}
