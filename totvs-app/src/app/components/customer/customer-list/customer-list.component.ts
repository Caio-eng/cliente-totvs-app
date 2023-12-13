import { CustomerService } from './../../../services/customer.service';
import { Cliente } from '../../../models/cliente';
import { Component, HostListener, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';


@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent {

  ELEMENT_DATA: Cliente[] = [];

  displayedColumns: string[] = ['nome','cpf','endereco', 'bairro', 'telefones', 'actions'];
  dataSource = new MatTableDataSource<Cliente>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(
    private service: CustomerService
  ) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(response => {
      this.ELEMENT_DATA = response.map(customer => ({
        ...customer,
        telefones: customer.telefones || []
      }));
      this.dataSource = new MatTableDataSource<Cliente>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
