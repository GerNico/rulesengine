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
import {ProvisioningService} from "./services/provisioning.service";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RecoverComponent } from './recover/recover.component';

@NgModule({
  declarations: [
    AppComponent,
    EditModelComponent,
    NavbarComponent,
    EditWeaponComponent,
    LoginComponent,
    RegisterComponent,
    RecoverComponent,
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
