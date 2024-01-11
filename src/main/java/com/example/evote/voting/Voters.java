package com.example.evote.voting;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Voters {
    @Id
    private String voterUsername;

    @ManyToOne
    Candidates candidate;

}
