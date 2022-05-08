import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/Modal_Classes/user';
import { UserClass } from 'src/Modal_Classes/UserClass';
import { RegistrationService } from 'src/services/registration.service';

@Component({
  selector: 'app-consumer-register',
  templateUrl: './consumer-register.component.html',
  styleUrls: ['./consumer-register.component.css']
})
export class ConsumerRegisterComponent implements OnInit {
  name: any;
  username: any;
  password: any;

  constructor(public fb: FormBuilder,
    private registration : RegistrationService) { }

  consumerRegisterForm = this.fb.group({
  name: [null, [Validators.required, Validators.minLength(4)]],
  username: [null, [Validators.required, Validators.minLength(4)]],
  password: [null, [Validators.required, Validators.maxLength(8)]],
  });


  ngOnInit(): void {
  }


  registerConsumer(){
    console.log(this.consumerRegisterForm.value);
    this.name = this.consumerRegisterForm.get('name')?.value;
    this.username = this.consumerRegisterForm.get('username')?.value;
    this.password = this.consumerRegisterForm.get('password')?.value;
    let user = new User(this.name, this.username, this.password, UserClass.Consumer);
    const response = this.registration.addConsumer(user);
    response.subscribe((data) => console.log(data));
    this.consumerRegisterForm.reset();
  }

}
