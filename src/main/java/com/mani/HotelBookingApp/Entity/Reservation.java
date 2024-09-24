package com.mani.HotelBookingApp.Entity;//NOSONAR

import com.mani.HotelBookingApp.DTO.ReservationDto;
import com.mani.HotelBookingApp.Enum.ReservationStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long price;
    private ReservationStatus reservationStatus;//NOSONAR

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="room-id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="user-id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ReservationStatus getReservation() {
        return reservationStatus;
    }

    public void setReservation(ReservationStatus reservation) {
        this.reservationStatus = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public ReservationDto getReservationDto(){
        ReservationDto reservationDto=new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setPrice(price);
        reservationDto.setCheckInDate(checkInDate);
        reservationDto.setCheckOutDate(checkOutDate);
        reservationDto.setReservationStatus(reservationStatus);

        reservationDto.setUserId(user.getId());
        reservationDto.setUsername(user.getUsername());

        reservationDto.setRoomId(room.getId());
        reservationDto.setRoomName(room.getName());
        reservationDto.setRoomType(room.getType());

        return reservationDto;

    }
}
