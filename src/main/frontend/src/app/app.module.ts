import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EditModelComponent } from './edit-model/edit-model.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from "@angular/forms";
import { EditWeaponComponent } from './edit-weapon/edit-weapon.component';
import { HttpClientModule} from "@angular/common/http";
import {ProvisioningService} from "./shared/services/provisioning.service";

@NgModule({
  declarations: [
    AppComponent,
    EditModelComponent,
    NavbarComponent,
    EditWeaponComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ProvisioningService],
  bootstrap: [AppComponent]
})
export class AppModule { }
