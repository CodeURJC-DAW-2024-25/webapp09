import { DatePipe } from "@angular/common";

export interface ReservationDTO {

    id: number;
    checkIn: Date;
    checkOut: Date;
    valorated: boolean;
    clientDni: string;
    housingCode: number;
    housingName: string;

}
