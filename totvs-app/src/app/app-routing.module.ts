import { CustomerDeleteComponent } from './components/customer/customer-delete/customer-delete.component';
import { CustomerCreateComponent } from './components/customer/customer-create/customer-create.component';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';

const routes: Routes = [
  {
    path: '', component: NavComponent,  children: [

      { path: 'customer', component: CustomerListComponent },
      { path: 'customer/create', component: CustomerCreateComponent },
      { path: 'customer/edit/:id', component: CustomerCreateComponent },
      { path: 'customer/delete/:id', component: CustomerDeleteComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
