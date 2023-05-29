import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../model/client.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
    constructor(private  http:HttpClient) { }

  public getClients():Observable<Array<Client>>{
    return this.http.get<Array<Client>>("http://localhost:1975/clients")
  }

  public rechercheClients(motCle : string):Observable<Array<Client>>{
    return this.http.get<Array<Client>>("http://localhost:1975/clients/recherche?motCle="+motCle)
  }

  public saveClient(client : Client):Observable<Client>{
    return this.http.post<Client>("http://localhost:1975/clients",client)
  }
  public delectClient(id : number){
    return this.http.delete("http://localhost:1975/clients/"+id)
  }
}

