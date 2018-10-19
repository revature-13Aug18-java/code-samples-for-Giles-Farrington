import { Component, OnInit } from '@angular/core';
import {ContextService} from '../../services/context.service';
import { DomSanitizer } from '@angular/platform-browser';
import {DataService} from '../../services/data.service';

import {User} from '../../models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public firstname;
  public lastname;
  public password;
  public validatePassword;
  public bio;
  public phone;
  public email;


  constructor(private sanitizer:DomSanitizer,
    private data: DataService,
    private context: ContextService,
    private router: Router) { }

  ngOnInit() {
    this.initializeUser();
    console.log(this.context.getUser());
  }

  currentUser: User;
  numOfEvents: Number;

  initializeUser(){
    this.currentUser = this.context.getUser();
    this.numOfEvents = this.context.getUser().events.length;
    console.log(this.currentUser, ' & ', this.numOfEvents);
  }

  updateUser(){
    console.log(this.firstname, this.lastname, this.bio, this.phone, this.email, this.password, this.validatePassword);
    if(this.firstname != undefined)
      this.currentUser.firstname = this.firstname;
    if(this.lastname != undefined)
      this.currentUser.lastname = this.lastname;
    if(this.bio != undefined)
      this.currentUser.bio = this.bio;
    if(this.phone != undefined)
      this.currentUser.phone = this.phone;
    if(this.email != undefined)
      this.currentUser.email = this.email;
    if(this.password != undefined && this.password === this.validatePassword)
      this.currentUser.password = this.password;
    this.context.setUser(this.currentUser);
    console.log("CURRENT USER TO BE UPDATED: ", this.currentUser);
    this.data.updateUser(this.currentUser).subscribe();
  }
}
