import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {ComptesComponent} from "./comptes/comptes.component";
import {NewClientComponent} from "./new-client/new-client.component";

const routes: Routes = [
  { path :"clients",component:ClientsComponent},
  { path :"comptes",component:ComptesComponent},
  {path:"new-client",component:NewClientComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
