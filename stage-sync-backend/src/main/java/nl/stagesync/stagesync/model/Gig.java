package nl.stagesync.stagesync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gig")
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String venue;

    @Column
    private String room;

    @Column
    private String location;

    @Column
    private LocalDateTime date;

    @Column
    private int fee;

    @Column
    private float duration;

    @Column(name="is_confirmed")
    private boolean isConfirmed;

    @Column(name="has_passed")
    private boolean hasPassed;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status")
    private EInvoiceStatus invoiceStatus;

    @ManyToOne
    @JsonIgnore
    private Artist artist;

    public Gig() {
    }

    public Gig(String name, String venue, String room, String location, LocalDateTime date, int fee, float duration, boolean isConfirmed, EInvoiceStatus invoiceStatus) {
        this.name = name;
        this.venue = venue;
        this.room = room;
        this.location = location;
        this.date = date;
        this.fee = fee;
        this.duration = duration;
        this.isConfirmed = isConfirmed;
        this.invoiceStatus = invoiceStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isHasPassed() {
        return hasPassed;
    }

    public void setHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }

    public EInvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(EInvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
