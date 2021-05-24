package nl.stagesync.stagesync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "artist_name")
    private String artistName;

    @Column
    private int fee;

    @Column
    private float duration;

    @Column(name = "tickets_total")
    private int ticketsTotal;

    @Column(name = "tickets_sold")
    private int ticketsSold;

    @Enumerated(EnumType.STRING)
    @Column(name="confirmation_status")
    private EConfirmationStatus confirmationStatus;

    @Column(name="has_passed")
    private boolean passed;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status")
    private EInvoiceStatus invoiceStatus;

    @ManyToOne
    @JoinTable (name = "gig_artist",
            joinColumns = @JoinColumn(name = "gig_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @JsonIgnore
    private Artist artist;

    public Gig() {
    }

    public Gig(String name, String venue, String room, String location, LocalDateTime date, int fee, float duration, int ticketsTotal, EConfirmationStatus confirmationStatus, EInvoiceStatus invoiceStatus, Artist artist) {
        this.name = name;
        this.venue = venue;
        this.room = room;
        this.location = location;
        this.date = date;
        this.fee = fee;
        this.duration = duration;
        this.confirmationStatus = confirmationStatus;
        this.invoiceStatus = invoiceStatus;
        this.artist = artist;
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

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public EConfirmationStatus getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setconfirmationStatus(EConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public boolean hasPassed() {
        return passed;
    }

    public void setHasPassed(boolean passed) {
        this.passed = passed;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
