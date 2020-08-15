import {Component, OnInit} from '@angular/core';
import {ProvisioningService} from "../services/provisioning.service";
import {AppSettings} from "../app.settings";
import {environment} from "../../environments/environment";

enum WeaponType {
  Pistol = "Pistol",
  RapidFire = "Rapid Fire",
  Heavy = "Heavy",
  Assault = "Assault",
  Melee = "Melee",
  Grenade = "Grenade"
}

@Component({
  selector: 'app-edit-weapon',
  templateUrl: './edit-weapon.component.html',
  styleUrls: ['./edit-weapon.component.scss']
})
export class EditWeaponComponent implements OnInit {
  weaponName: string = "Bolt Pistol";
  typeIcon: string;
  range: number;
  weaponSkill: number;
  type: WeaponType;
  types = [WeaponType.Assault, WeaponType.Grenade, WeaponType.Heavy, WeaponType.Melee, WeaponType.RapidFire, WeaponType.Pistol];
  shouts: number;
  strength: number;
  armorPiercing: number;
  damage: number;
  selectedRule: string = "no rule";
  rules = [];

  constructor(private provisioning: ProvisioningService) {
  }

  ngOnInit(): void {
    this.provisioning.get(environment.url + AppSettings.WEAPON_RULES).subscribe(value => {
      console.log('value', value);
      this.rules = value
    }, error => {

    })
  }

}
