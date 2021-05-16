import {Component, OnInit} from '@angular/core';
import {ProvisioningService} from "../shared/services/provisioning.service";
import {KeyValuePipe} from "@angular/common";

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
  current?: string;
  rules: Map<string, string>;
  selectedRules: Map<string, string>;

  constructor(private provisioning: ProvisioningService) {
  }

  addRule(key: string): void {
    let value = this.rules[key];
    this.selectedRules.set(key, value)
  }

  removeRule(key: string): void {
    this.selectedRules.delete(key)
  }

  ngOnInit(): void {
    this.selectedRules = new Map<string, string>()
    this.provisioning.getWeaponRules().subscribe(value => {
      this.rules = value;
    }, error => {
    })
  }
}
