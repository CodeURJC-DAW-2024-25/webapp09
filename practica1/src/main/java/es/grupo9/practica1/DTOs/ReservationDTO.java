package es.grupo9.practica1.DTOs;

import java.sql.Date;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Reservation;
import es.grupo9.practica1.entities.User;

public class ReservationDTO {
    private Integer id;
    private Date checkIn;
    private Date checkOut;
    private boolean valorated;
    private String clientDni;
    private String clientName;
    private String housingCode;
    private String housingName;

    public ReservationDTO() {
    }

    public ReservationDTO(Integer id, Date checkIn, Date checkOut, boolean valorated, 
                         String clientDni, String clientName, String housingCode, String housingName) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valorated = valorated;
        this.clientDni = clientDni;
        this.clientName = clientName;
        this.housingCode = housingCode;
        this.housingName = housingName;
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.checkIn = reservation.getCheck_in();
        this.checkOut = reservation.getCheck_out();
        this.valorated = reservation.isValorated();
        this.clientDni = reservation.getID_cliente().getDni();
        this.clientName = reservation.getID_cliente().getName();
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getHousingCode() {
        return housingCode;
    }

    public void setHousingCode(String housingCode) {
        this.housingCode = housingCode;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", valorated=" + valorated +
                ", clientDni='" + clientDni + '\'' +
                ", clientName='" + clientName + '\'' +
                ", housingCode='" + housingCode + '\'' +
                ", housingName='" + housingName + '\'' +
                '}';
    }
}