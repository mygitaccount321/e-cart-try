class Customer {

     firstName : string;
     lastName : String;
    constructor(theFirst: string, theLast: string) {
        this.firstName = theFirst;
        this.lastName = theLast;
    }
}
let myCustomer = new Customer("aa", "bb");

console.log(myCustomer.firstName);
console.log(myCustomer.lastName);