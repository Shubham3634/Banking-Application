import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';
import { ActivatedRoute } from '@angular/router';
import { Account } from '../account';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  id:number=0;
  account:Account=new Account();
constructor(private accountService:AccountService, private route:ActivatedRoute){}
 

  ngOnInit(){
  this.id=this.route.snapshot.params['id'];
  this.accountService.getAccountById(this.id).subscribe(data=>{
this.account=data

console.log(data)

  })
}

}
