import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {Observable} from "rxjs";
import {CompteDeatails} from "../model/compte.model";

@Injectable({
  providedIn: 'root'
})
export class ComptesService {

  constructor(private http:HttpClient) { }
  public  getComptes(id_compte : string,page:number,size:number):Observable<CompteDeatails>{
          return this.http.get<CompteDeatails>(environment.url+"/comptes/"+id_compte+"/pageOperations?page="+page+"&size="+size)
  }
}
