package nl.stagesync.stagesync.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String name;

    @Column
    long actor_id;

    @Column
    long venue_id;

    @Column
    LocalDateTime date;

    @Column
    float duration;

    @Column
    boolean isConfirmed;

    @Column
    boolean hasPassed;

//    @Column
//    enum InvoiceStatus {
//        NOT_SENT, SENT, PAID
//    }

}
