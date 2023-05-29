import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {CompteDetails} from "../model/compte.model";

@Injectable({
  providedIn: 'root'
})
export class ComptesService {

  constructor(private http:HttpClient) { }
  public  getComptes(id_compte : string,page:number,size:number):Observable<CompteDetails>{
          return this.http.get<CompteDetails>("http://localhost:1975/comptes/"+id_compte+"/pageOperations?page="+page+"&size="+size)
  }
  public debit(id_compte : string, montant : number, description:string){
    let data={id_compte : id_compte, montant : montant, description : description}
    return this.http.post("http://localhost:1975/comptes/debit",data);
  }
  public credit(id_compte : string, montant : number, description:string){
    let data={id_compte : id_compte, montant : montant, description : description}
    return this.http.post("http://localhost:1975/comptes/credit",data);
  }
  public transfer(CompteSource: string,CompteDestination: string, Montant : number, description:string){
    let data={CompteSource, CompteDestination, Montant, description }
    return this.http.post("http://localhost:1975/comptes/transfer",data);
  }
}
