import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-weapon',
  templateUrl: './edit-weapon.component.html',
  styleUrls: ['./edit-weapon.component.scss']
})
export class EditWeaponComponent implements OnInit {
  weaponName: string="Bolt Pistol";

  constructor() { }

  ngOnInit(): void {
  }

}
