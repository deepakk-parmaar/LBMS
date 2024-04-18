package com.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userid;

    private double fine;

    private Fines(Builder builder) {
        this.userid = builder.userid;
        this.fine = builder.fine;
    }

    // getter
    public Long getUserid() {
        return userid;
    }

    public double getFine() {
        return fine;
    }

    public static class Builder {
        private Long userid;
        private double fine;

        public Builder userid(Long userid) {
            this.userid = userid;
            return this;
        }

        public Builder fine(double fine) {
            this.fine = fine;
            return this;
        }

        public Fines build() {
            return new Fines(this);
        }
    }
}
