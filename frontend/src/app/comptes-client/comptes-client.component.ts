import {Component, OnInit} from '@angular/core';
import {Client} from "../model/client.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientService} from "../services/client.service";

@Component({
  selector: 'app-comptes-client',
  templateUrl: './comptes-client.component.html',
  styleUrls: ['./comptes-client.component.css']
})
export class ComptesClientComponent implements OnInit{
  id_client!: string;
  client!: Client;
  comptes: any = [];
  errorMessage: any;
  clientName: any;

  constructor(private route: ActivatedRoute, private router: Router,private clientService: ClientService ) {
    this.id_client = this.route.snapshot.params['id'];
    this.client = this.router.getCurrentNavigation()?.extras.state as Client;
    this.clientService.getComptesOfClient(parseInt(this.id_client)).subscribe({
      next: (data: any) => {
        console.log(data);
        this.comptes = data;
      },
      error: (err: any) => {
        console.log(err);
      }
    });

    this.clientService.getclient(parseInt(this.id_client)).subscribe({
      next: (data: any) => {
        this.clientName = data.name + '(' + data.email + ')';
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }

  ngOnInit(): void {

  }

}
