import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

import {catchError, Observable, throwError} from "rxjs";
import {CompteDetails} from "../model/compte.model";
import {ComptesService} from "../services/comptes.service";


@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {
  CompteFormGroup! : FormGroup;
  currentPage : number =0;
  pageSize : number =5;
  CompteObservable! : Observable<CompteDetails>
  operationFromGroup! : FormGroup;
  errorMessage! :string ;

  constructor(private fb : FormBuilder, private compteService : ComptesService) { }

  ngOnInit(): void {
    this.CompteFormGroup=this.fb.group({
      id_compte : this.fb.control('')
    });
    this.operationFromGroup=this.fb.group({
      TypeOperation : this.fb.control(null),
      Montant : this.fb.control(0),
      description : this.fb.control(null),
      comptesDestination : this.fb.control(null)
    })}

  handleSearchAccount() {
    let id_compte : string =this.CompteFormGroup.value.id_compte;
    this.CompteObservable=this.compteService.getComptes(id_compte,this.currentPage, this.pageSize).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }

  gotoPage(page: number) {
    this.currentPage=page;
    this.handleSearchAccount();
  }

  handleAccountOperation() {
    let id_compte :string = this.CompteFormGroup.value.id_compte;
    let TypeOperation=this.operationFromGroup.value.TypeOperation;
    let Montant :number =this.operationFromGroup.value.Montant;
    let description :string =this.operationFromGroup.value.description;
    let compteDestination :string =this.operationFromGroup.value.compteDestination;
    if(TypeOperation=='DEBIT'){
      this.compteService.debit(id_compte, Montant,description).subscribe({
        next : (data)=>{
          alert("Success Credit");
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error : (err)=>{
          console.log(err);
        }
      });
    } else if(TypeOperation=='CREDIT'){
      this.compteService.credit(id_compte, Montant,description).subscribe({
        next : (data)=>{
          alert("Success Debit");
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error : (err)=>{
          console.log(err);
        }
      });
    }
    else if(TypeOperation=='TRANSFER'){
      this.compteService.transfer(id_compte,compteDestination, Montant,description).subscribe({
        next : (data)=>{
          alert("Success Transfer");
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error : (err)=>{
          console.log(err);
        }
      });

    }
  }
}
