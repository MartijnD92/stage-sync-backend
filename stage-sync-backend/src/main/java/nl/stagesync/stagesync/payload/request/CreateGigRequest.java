package nl.stagesync.stagesync.payload.request;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.EInvoiceStatus;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CreateGigRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 50)
    private String venue;

    @Size(min = 1, max = 50)
    private String room;

    @Size(min = 1, max = 50)
    private String location;

    @NotBlank
    private LocalDateTime date;

    private int fee;

    private float duration;

    private boolean isConfirmed;

    private boolean hasPassed;

    private EInvoiceStatus invoiceStatus;

    @NotBlank
    private Artist artist;

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

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
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
