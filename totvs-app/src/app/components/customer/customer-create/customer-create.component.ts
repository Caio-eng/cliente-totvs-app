import { CustomerService } from './../../../services/customer.service';
import { Cliente } from 'src/app/models/cliente';
import { Component } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoadingService } from 'src/app/services/loading.service';


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css'],

})
export class CustomerCreateComponent {

  customer: Cliente = {} as Cliente;
  form: FormGroup;


  constructor(
    private service: CustomerService,
    public loadingService: LoadingService,
    private router: Router,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute

  ) {
    this.form = new FormGroup({
      nome: new FormControl(null, Validators.minLength(3)),
      cpf: new FormControl(null, Validators.required),
      endereco: new FormControl(null),
      bairro: new FormControl(null),
      telefones: new FormArray([]),
    });

  }

  get telefonesFormArray() {
    return this.form.get('telefones') as FormArray;
  }

  addTelefone(): void {
    this.telefonesFormArray.push(new FormControl(''));
  }

  removeTelefone(index: number): void {
    this.telefonesFormArray.removeAt(index);
  }


  ngOnInit(): void {

    if (this.route.snapshot.paramMap.get('id')) {
      this.loadingService.setLoading(true);
      this.customer.id = Number(this.route.snapshot.paramMap.get('id'));
      this.findById();
      this.loadingService.setLoading(false);
    }
  }

  findById(): void {
    this.loadingService.setLoading(true);
    this.service.findById(this.customer.id).subscribe(response => {
      this.customer = response;
      this.form.patchValue({
        nome: this.customer.nome,
        cpf: this.customer.cpf,
        endereco: this.customer.endereco,
        bairro: this.customer.bairro,
        telefones: this.customer.telefones
      });
      this.loadingService.setLoading(false);
    });
  }

  onSubmit(): void {
    if (this.form.valid) {
      const formValues = this.form.value;
      if (formValues.cpf && formValues.cpf !== '') {
        this.customer.cpf = formValues.cpf.replace(/[^\d]/g, '');
      }
      this.customer.cpf = undefined;
      this.customer.nome = formValues.nome;
      this.customer.cpf = formValues.cpf;
      this.customer.endereco = formValues.endereco;
      this.customer.bairro = formValues.bairro;
      this.customer.telefones = formValues.telefones.map((telefone: string) => {
        // Adicione a lógica para remover não numéricos e manter apenas os dígitos
        const numericDigits = telefone.replace(/[^\d]/g, '');
        // Adicione a lógica para adicionar a máscara desejada (000000-0000)
        return numericDigits.replace(/(\d{6})(\d{4})/, '$1-$2');
      });
    }
    if (this.customer.id) {
      this.loadingService.setLoading(true);
      this.service.update(this.customer).subscribe(response => {
        this.snackBar.open('Cliente atualizado com sucesso', 'Fechar', {
          duration: 4000,
          panelClass: ['success-snackbar']
        });
        this.loadingService.setLoading(false);
        this.router.navigate(['customer']);
      }, ex => {
        if (ex.error.errors) {
          this.loadingService.setLoading(false);
          ex.error.errors.array.forEach((element: { message: string; }) => {
            this.snackBar.open(element.message, 'Fechar', {
              duration: 4000,
              panelClass: ['error-snackbar']
            });
          });
        } else {
          this.loadingService.setLoading(false);
          this.snackBar.open(ex.error.message, 'Fechar', {
            duration: 4000,
            panelClass: ['error-snackbar']
          });
        }
      });

    } else {
      this.loadingService.setLoading(true);
      this.service.create(this.customer).subscribe(() => {
        this.snackBar.open('Cliente cadastrado com sucesso', 'Fechar', {
          duration: 4000,
          panelClass: ['success-snackbar']
        });
        this.loadingService.setLoading(false);
        this.router.navigate(['customer']);
      }, ex => {
        if (ex.error.errors) {
          this.loadingService.setLoading(false);
          ex.error.errors.array.forEach((element: { message: string; }) => {
            this.snackBar.open(element.message, 'Fechar', {
              duration: 4000,
              panelClass: ['error-snackbar']
            });
          });
        } else {
          this.loadingService.setLoading(false);
          this.snackBar.open(ex.error.message, 'Fechar', {
            duration: 4000,
            panelClass: ['error-snackbar']
          });
        }
      });

    }
  }

}
