import { Component, OnInit } from '@angular/core';
import { HousingDTO } from '../../models/DTOS/housing-dto';
import { HousingServiceService } from '../../services/housing-service.service';



interface SearchParams {
  tags?: string;
  stars?: number;
}


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

  searchParams: SearchParams = {
    tags: '',
    stars: 1
  };

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


  onSearchSubmit(): void {
    this.currentPage = 1;
    this.houses = [];
    
    // Call the search service with both parameters
    this.houseService.searchHouses(
      this.searchParams.tags || '', 
      this.searchParams.stars || 1
    ).subscribe({
      next: (houses) => {
        this.houses = houses;
      },
      error: (error) => {
        console.error('Search error:', error);
        // Handle errors as needed
      }
    });
  }
}
