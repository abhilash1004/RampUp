import { UserClass } from "./UserClass";

export class User {
    name: string;
    username: string;
    password: string;
    userType: UserClass

    constructor(name: string, username: string, password:string, userType: UserClass){
        this.name=name;
        this.username=username;
        this.password=password;
        this.userType=userType;
    }
}
