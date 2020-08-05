import {Component, OnInit} from '@angular/core';

enum UnitType {
  HQ, LORD_OF_WAR, FAST_ATTACK, TROOPS, HEAVY_SUPPORT, FORTIFICATION, FLYER, ELITES, DEDICATED_TRANSPORT
}

@Component({
  selector: 'app-edit-model',
  templateUrl: './edit-model.component.html',
  styleUrls: ['./edit-model.component.scss']
})
export class EditModelComponent implements OnInit {
  movement: number;
  modelName: string = 'Lord of Contagion';
  weaponSkill: number;
  ballisticsSkill: number;
  strength: number;
  toughness: number;
  wounds: number;
  attacks: number;
  leadership: number;
  saves: number;
  type: UnitType = UnitType.HQ;
  typeMap: Map<number, string>
  typeIcon: string;
  powerPoints: number = 10;

  constructor() {
  }

  ngOnInit(): void {
    this.typeMap = new Map<number, string>();
    this.typeMap.set(UnitType.HQ, 'assets/HQ.png');
    this.typeMap.set(UnitType.LORD_OF_WAR, 'assets/lord of war.png');
    this.typeMap.set(UnitType.FAST_ATTACK, 'assets/Fast Attack.png');
    this.typeMap.set(UnitType.TROOPS, 'assets/Troops.png');
    this.typeMap.set(UnitType.HEAVY_SUPPORT, 'assets/heavy support.png');
    this.typeMap.set(UnitType.FORTIFICATION, 'assets/fortification.png');
    this.typeMap.set(UnitType.FLYER, 'assets/flyer.png');
    this.typeMap.set(UnitType.ELITES, 'assets/Elites.png');
    this.typeMap.set(UnitType.DEDICATED_TRANSPORT, 'assets/dedicated transport.png');
    this.typeIcon = this.typeMap.get(this.type);
    console.log(this.type);
    console.log(this.typeMap);
    console.log(this.typeMap.get(this.type));
  };

}
