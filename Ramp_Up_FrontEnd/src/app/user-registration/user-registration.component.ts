import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/Modal_Classes/user';
import { RegistrationService } from 'src/services/registration.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  UserTypeData : any = ['Consumer', 'Seller', 'Admin'];
  userType: any;
  userTypeSelected: any;
  buttonName: any;
  name: any;
  username: any;
  password: any;
  userData: any;
  constructor(public fb: FormBuilder,
              private registration : RegistrationService) { }

  registerForm = this.fb.group({
    userType: ['', [Validators.required]],
    name: [null, [Validators.required, Validators.minLength(4)]],
    username: [null, [Validators.required, Validators.minLength(4)]],
    password: [null, [Validators.required, Validators.maxLength(8)]],
    organization: [null, [Validators.required]],
    organizationDescription: [null, [Validators.required]]
  });

  ngOnInit(): void {
  }

  changeUser(event: any){
    this.userType?.setValue(event.target.value,{
      onlySelf: true,
    });
    this.userTypeSelected = event.target.value;
    if(this.userTypeSelected === '2: Seller'){
      this.buttonName = 'Submit';
    }
    else{
      this.buttonName = 'Register';
    }
  }

  registerUser(){
    console.log(this.registerForm.value);
    this.name = this.registerForm.get('name')?.value;
    this.username = this.registerForm.get('username')?.value;
    this.password = this.registerForm.get('password')?.value;
    let user = new User(this.name, this.username, this.password);
    const response = this.registration.addUser(user);
    response.subscribe((data) => console.log(data));
    this.userTypeSelected = 'Consumer';
    this.registerForm.reset();
  }
} 
