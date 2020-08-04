import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-model',
  templateUrl: './edit-model.component.html',
  styleUrls: ['./edit-model.component.scss']
})
export class EditModelComponent implements OnInit {
  movement: number;
  modelName: string="Lord of Contagion";
  weaponSkill: number;
  ballisticsSkill: number;
  strength: number;
  toughness: number;
  wounds: number;
  attacks: number;
  leadership: number;
  saves: number;

  constructor() { }

  ngOnInit(): void {
  }

}
