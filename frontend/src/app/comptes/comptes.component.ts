import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit{
  compteFormGroup!:FormGroup;
 constructor(private fb:FormBuilder) {
 }
  ngOnInit(): void {
   this.compteFormGroup=this.fb.group(
     {
       idcompte :this.fb.control('')
     });
  }

  handleSearchAccount() {

  }
}
