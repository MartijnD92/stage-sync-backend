package nl.stagesync.stagesync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gig")
public class Gig {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    @NotNull
    private String venue;

    @Column
    private String room;

    @Column
    @NotNull
    private String location;

    @Column
    @NotNull
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
    private InvoiceStatus invoiceStatus;

    @ManyToOne
    @JsonIgnore
    private Artist artist;

}
