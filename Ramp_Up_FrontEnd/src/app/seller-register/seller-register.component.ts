import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-seller-register',
  templateUrl: './seller-register.component.html',
  styleUrls: ['./seller-register.component.css']
})
export class SellerRegisterComponent implements OnInit {

  constructor(public fb: FormBuilder) { }
  sellerRegisterForm = this.fb.group({
    organization: [null, [Validators.required]],
    organizationDescription: [null, [Validators.required]],
    organizationEmail: [null, [Validators.required]]
  });

  ngOnInit(): void {
  }

}
