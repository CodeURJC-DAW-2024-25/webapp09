import { Component } from '@angular/core';
import { HousingDTO } from '../../../models/DTOS/housing-dto';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment.development';

@Component({
  selector: 'app-housing-panel',
  templateUrl: './housing-panel.component.html',
  styleUrl: './housing-panel.component.css',
  standalone: false
})
export class HousingPanelComponent {
  houses: HousingDTO[] = [];
  currentPage = 0;
  isLoading = false;
  size = 3;

  constructor(private http: HttpClient) {
    this.loadHouses();
  }

  loadHouses() {
    if (this.isLoading) return;
    
    this.isLoading = true;
    this.http.get<HousingDTO[]>(`${environment.baseUrlApi}/admin/houses?page=${this.currentPage}&size=${this.size}`)
      .subscribe({
        next: (data) => {
          this.houses = [...this.houses, ...data];
          this.currentPage++;
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error loading houses:', err);
          this.isLoading = false;
        }
      });
  }

  handleDecision(houseCode: number, decision: 'accept' | 'reject') {
    if (decision == 'accept'){  
      this.http.put(`${environment.baseUrlApi}/admin/houses/decision/${houseCode}`, {})
        .subscribe({
          next: () => {
            this.houses = this.houses.filter(h => h.code !== houseCode);
          },
          error: (err) => {
            console.error('Error processing decision:', err);
          }
        });
    }
    else {
      this.http.delete(`${environment.baseUrlApi}/admin/houses/decision/${houseCode}`, {})
        .subscribe({
          next: () => {
            this.houses = this.houses.filter(h => h.code !== houseCode);
          },
          error: (err) => {
            console.error('Error processing decision:', err);
          }
        });
    }

  }
}
