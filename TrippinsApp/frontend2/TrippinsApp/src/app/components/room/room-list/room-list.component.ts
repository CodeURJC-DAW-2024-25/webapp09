import { Component } from '@angular/core';
import { HousingDTO } from '../../../models/DTOS/housing-dto';
import { HousingServiceService } from '../../../services/housing-service.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrl: './room-list.component.css',
  standalone:false
})
export class RoomListComponent {
  constructor (private housingService: HousingServiceService){
  }

  rooms: HousingDTO[] = [];


  ngOnInit() {
    this.housingService.getRooms().subscribe(rooms => {
      this.rooms = rooms;
    });
  }

}
