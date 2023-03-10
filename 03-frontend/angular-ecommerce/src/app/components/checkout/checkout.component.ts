import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Country } from 'src/app/common/country';
import { State } from 'src/app/common/state';
import { ShopFormService } from 'src/app/services/shop-form.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup: FormGroup;
  totalPrice: number = 0;
  totalQuantity: number = 0;

  creditCardYears: number[] = [];
  creditCardMonths: number[] = [];

  countries: Country[] = [];

  shippingAddressStates: State[] = [];
  billingAddressStates: State[] = [];
  
  constructor(private formBuilder: FormBuilder, private shopFormService: ShopFormService) { }
  

  ngOnInit(): void {       
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: [''],
        lastName: [''],
        email: ['']
      }),
      shippingAddress: this.formBuilder.group({
        street: [''],
        city: [''],
        state: [''],
        country: [''],
        zipCode: ['']
      }),
      billingAddress: this.formBuilder.group({
        street: [''],
        city: [''],
        state: [''],
        country: [''],
        zipCode: ['']
      }),
      creditCard: this.formBuilder.group({
        cardType: [''],
        nameOnCard: [''],
        cardNumber: [''],
        securityCode: [''],
        expirationMonth: [''],
        expirationYear: ['']
      })
    });

    
    const startMonth: number = new Date().getMonth() + 1;
    console.log("startMonth: " + startMonth);

    this.shopFormService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );

    // populate credit card years

    this.shopFormService.getCreditCardYears().subscribe(
      data => {
        console.log("Retrieved credit card years: " + JSON.stringify(data));
        this.creditCardYears = data;
      }
    );

    this.shopFormService.getCountries().subscribe(
      data => {
        console.log("Retrieved countries: " + JSON.stringify(data));
        this.countries = data;
      }
    );
  }

  copyShippingAddressToBillingAddress(event: any) {

      if (event.target.checked) {
        this.checkoutFormGroup.controls['billingAddress']
              .setValue(this.checkoutFormGroup.controls['shippingAddress'].value);

              this.billingAddressStates = this.shippingAddressStates;
      }
      else {
        this.checkoutFormGroup.controls['billingAddress'].reset();

         this.billingAddressStates = [];
      }
    
    
  }

  onSubmit() {
    
      console.log("Handling the submit button");

      const tagControl = this.checkoutFormGroup.get('customer');
      if(tagControl) {
        console.log(tagControl.value);
        console.log("The email address is " + tagControl.value.email);
      }
  }

  handleMonthsAndYears() {

    const creditCardFormGroup = this.checkoutFormGroup.get('creditCard');

    if(creditCardFormGroup !=null) {

      const currentYear: number = new Date().getFullYear();
      const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);
  
      // if the current year equals the selected year, then start with the current month
  
      let startMonth: number;
  
      if (currentYear === selectedYear) {
        startMonth = new Date().getMonth() + 1;
      }
      else {
        startMonth = 1;
      }
  
      this.shopFormService.getCreditCardMonths(startMonth).subscribe(
        data => {
          console.log("Retrieved credit card months: " + JSON.stringify(data));
          this.creditCardMonths = data;
        }
      );
    }
    }

    getStates(formGroupName: string) {

      const formGroup = this.checkoutFormGroup.get(formGroupName);
  if(formGroup !=null) {
    const countryCode = formGroup.value.country.code;
    const countryName = formGroup.value.country.name;

    console.log(`${formGroupName} country code: ${countryCode}`);
    console.log(`${formGroupName} country name: ${countryName}`);

    this.shopFormService.getStates(countryCode).subscribe(
      data => {

        if (formGroupName === 'shippingAddress') {
          this.shippingAddressStates = data; 
        }
        else {
          this.billingAddressStates = data;
        }
        // select first item by default
        const state = formGroup.get('state');
        if(state !=null){
          state.setValue(data[0]);
}        
      }
    );
  }
 
    }
}
