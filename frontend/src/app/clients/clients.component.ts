import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ClientService} from "../services/client.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Client} from "../model/client.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import Swal from 'sweetalert2';
import {Router} from "@angular/router";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent  implements  OnInit{
  clients!:Observable<Array<Client>>;
  //clients:any;
  errorMessage!:string ;
  rechercheformGroup!: FormGroup;
  constructor(private clientService:ClientService,private  fb:FormBuilder,private router :Router) {
  }
  ngOnInit(): void {
    this.rechercheformGroup=this.fb.group(
      {
        motCle : this.fb.control("")
      }
    )
 // this.clientService.getClients().subscribe(
 //   {
 //     next:(data)=>{
 //       this.clients=data;
 //     },
 //     error:(err)=>{
 //       this.errorMessage=err.message;
 //     }
 //   }
 // );
    this.handleRechercheClients()
    // this.clients=this.clientService.getClients().pipe(
    //   catchError(
    //     err => {
    //       this.errorMessage=err.message;
    //       return throwError(err);
    //     }
    //   )
    // );
  }

  handleRechercheClients() {
   let mc=this.rechercheformGroup?.value.motCle;
    this.clients=this.clientService.rechercheClients(mc).pipe(
      catchError(
        err => {
          this.errorMessage=err.message;
          return throwError(err);
        }
      )
    );
  }

  handleDeleteClients(c:Client) {
    Swal.fire({
      title: 'Êtes-vous sûr de vouloir supprimer ?' ,
      text: 'ce n\'est pas possible de recevoir vous données ' ,
      icon: 'warning' ,
      showCancelButton: true,
      confirmButtonText: 'Supprimer',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.value) {
        this.clientService.delectClient(c.id).subscribe(
          {
            next :(resp)=>{
              this.clients=this.clients.pipe(
                map(data=>{
                  let index =data.indexOf(c)
                  data.slice(index,1)
                  Swal.fire("Suppression","L' absence a supprimer :( ","success")
                  return data;
                })
              );

            },
            error:err=>{
              Swal.fire('Error', "", 'error')
            }
          }
        )
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Annulation', "Le client n' a pas supprimer :)", 'error');
      }
    });

  }

  handleCustomerAccounts(c: Client) {
      this.router.navigateByUrl("/comptes-client/"+c.id,{state :c});

  }
}
