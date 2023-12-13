import { CustomerDeleteComponent } from './components/customer/customer-delete/customer-delete.component';
import { CustomerCreateComponent } from './components/customer/customer-create/customer-create.component';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';

const routes: Routes = [
  {
    path: '', component: NavComponent,  children: [

      { path: 'cliente', component: CustomerListComponent },
      { path: 'cliente/create', component: CustomerCreateComponent },
      { path: 'cliente/edit/:id', component: CustomerCreateComponent },
      { path: 'cliente/delete/:id', component: CustomerDeleteComponent },

      { path: '**', redirectTo: 'cliente' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
