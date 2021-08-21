import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EditModelComponent} from "./edit-model/edit-model.component";
import {EditWeaponComponent} from "./edit-weapon/edit-weapon.component";
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  {path: 'model' , component: EditModelComponent},
  {path: 'weapon' , component: EditWeaponComponent},
  {path: 'login' , component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
