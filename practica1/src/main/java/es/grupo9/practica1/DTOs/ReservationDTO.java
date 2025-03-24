package es.grupo9.practica1.DTOs;

import java.sql.Date;

import es.grupo9.practica1.entities.Reservation;

public class ReservationDTO {
    private Integer id;
    private Date checkIn;
    private Date checkOut;
    private boolean valorated;
    private String clientDni; // Represents the client's DNI (unique identifier)
    private int housingCode; // Represents the housing code
    private String housingName; // Represents the housing name

    // Constructors
    public ReservationDTO() {
    }

    public ReservationDTO(Integer id, Date checkIn, Date checkOut, boolean valorated, String clientDni,
        int housingCode, String housingName) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valorated = valorated;
        this.clientDni = clientDni;
        this.housingCode = housingCode;
        this.housingName = housingName;
    }

    public ReservationDTO(Reservation reservation){
        this.id = reservation.getId();
        this.checkIn = reservation.getCheck_in();
        this.checkOut = reservation.getCheck_out();
        this.valorated = reservation.isValorated();
        this.clientDni = reservation.getID_cliente().getDni();
        this.housingCode = reservation.getHousing().getCode();
        this.housingName = reservation.getHousing().getName();

    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isValorated() {
        return valorated;
    }

    public void setValorated(boolean valorated) {
        this.valorated = valorated;
    }

    public String getClientDni() {
        return clientDni;
    }

    public void setClientDni(String clientDni) {
        this.clientDni = clientDni;
    }

    public int getHousingCode() {
        return housingCode;
    }

    public void setHousingCode(int housingCode) {
        this.housingCode = housingCode;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }
}
