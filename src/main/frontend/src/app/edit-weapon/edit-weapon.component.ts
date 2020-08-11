import {Component, OnInit} from '@angular/core';

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

  constructor() {
  }

  ngOnInit(): void {
  }

}
