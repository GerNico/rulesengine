import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EditModelComponent} from "./edit-model/edit-model.component";
import {EditWeaponComponent} from "./edit-weapon/edit-weapon.component";


const routes: Routes = [
  {path: 'model' , component: EditModelComponent},
  {path: 'weapon' , component: EditWeaponComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
