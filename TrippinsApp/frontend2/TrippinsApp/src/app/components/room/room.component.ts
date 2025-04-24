import { Component, OnInit } from '@angular/core';
import { HousingDTO } from '../../models/DTOS/housing-dto';
import { HousingServiceService } from '../../services/housing-service.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrl: './room.component.css',
  standalone:false
})
export class RoomComponent implements OnInit {
  houses: any[] = [];
  currentPage = -1;
  pageSize = 6;
  isLoading = false;
  hasMore = true;

  constructor(private houseService: HousingServiceService) {}

  ngOnInit(): void {
    this.loadHouses();
  }

  loadHouses(): void {
    if (this.isLoading || !this.hasMore) return;

    this.isLoading = true;
    this.currentPage++;

    this.houseService.getRooms(this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.houses = [...this.houses, ...response.content];
        this.hasMore = !response.last;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading houses:', err);
        this.isLoading = false;
      }
    });
  }


  searchHouses(searchParams: any): void {
    this.currentPage = 1;
    this.houses = [];
    this.houseService.searchHouses(searchParams).subscribe({
      next: (houses) => {
        this.houses = houses;
      }
    });
  }
}
