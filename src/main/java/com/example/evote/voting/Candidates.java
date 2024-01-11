package com.example.evote.voting;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidates {
    @Id
    String username;
    @Lob
    byte[] candidateImage;
    int age;
    String experience;
    String level;
    String gp;
    @ManyToOne
   VotingFacilitators facilitators;

}
