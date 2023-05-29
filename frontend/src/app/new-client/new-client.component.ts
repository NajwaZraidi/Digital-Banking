import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../services/client.service";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit{
  newClietFormGroup! : FormGroup;
  constructor(private fb : FormBuilder,private clientService : ClientService,private router:Router) {}

  ngOnInit(): void {
    this.newClietFormGroup=this.fb.group(
      {
        name : this.fb.control(null,[Validators.required,Validators.minLength(4)]),
        email : this.fb.control(null,[Validators.required,Validators.email])

      });
  }

  handleSaveClient() {
   let client=this.newClietFormGroup.value;
   this.clientService.saveClient(client).subscribe(
     {
       next : data =>{
         Swal.fire("L\'ajout",'Client est enregistrer','success');
         this.newClietFormGroup.reset();

         this.router.navigateByUrl("/clients")
       },
       error : err=>{
         console.log(err);
       }
     }
   );
  }
}
