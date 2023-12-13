import { CustomerService } from './../../../services/customer.service';
import { Cliente } from 'src/app/models/cliente';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup } from '@angular/forms';
import { LoadingService } from 'src/app/services/loading.service';

@Component({
  selector: 'app-customer-delete',
  templateUrl: './customer-delete.component.html',
  styleUrls: ['./customer-delete.component.css']
})
export class CustomerDeleteComponent {

  customer: Cliente = { } as Cliente;
  form: FormGroup;

  constructor(
    private service: CustomerService,
    public loadingService: LoadingService,
    private router: Router,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute
  ) {
    this.form = new FormGroup({
      nome: new FormControl(),
      cpf: new FormControl(),
      endereco: new FormControl(),
      bairro: new FormControl(),
    })
   }

  ngOnInit(): void {
    this.customer.id = Number(this.route.snapshot.paramMap.get('id'));
    this.findById();
  }

  findById(): void {
    this.loadingService.setLoading(true);
    this.service.findById(this.customer.id).subscribe(response => {
      this.customer = response;
      this.form.patchValue({
        nome: this.customer.nome,
        cpf: this.customer.cpf,
        endereco: this.customer.endereco,
        bairro: this.customer.bairro
      });
      this.form.disable();
      this.loadingService.setLoading(false);
    })
  }

  delete() : void {
    this.loadingService.setLoading(true);
    this.service.delete(this.customer.id).subscribe(_response => {
      this.snackBar.open('Cliente deletado com sucesso', 'Fechar', {
        duration: 4000,
        panelClass: ['success-snackbar']
      });
      this.loadingService.setLoading(false);
      this.router.navigate(['customer']);
    }, ex => {
      if (ex.error.errors) {
        this.loadingService.setLoading(true);
        ex.error.errors.array.forEach((element: { message: string; }) => {
          this.snackBar.open(element.message, 'Fechar', {
            duration: 4000,
            panelClass: ['error-snackbar']
          });
        });
      } else {
        this.loadingService.setLoading(true);
        this.snackBar.open(ex.error.message, 'Fechar', {
          duration: 4000,
          panelClass: ['error-snackbar']
        });
      }
    });

  }

}
