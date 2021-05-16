package nl.stagesync.stagesync.model;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String genre;

    @Column
    private int price;

    @Column
    private boolean hasSoundEngineer;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "artist")
    private List<Gig> gigs;
}
