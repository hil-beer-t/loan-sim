package com.hilbert.loansimapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "loan_sim")
public class LoanSim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String cod;

    private LoanStatus status;

    private Month firstMonth;

    private Integer numMonths;

    private Instant moment;

    private Integer bestDay;

    private Long value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public LoanSim() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Month getFirstMonth() {
        return firstMonth;
    }

    public void setFirstMonth(Month firstMonth) {
        this.firstMonth = firstMonth;
    }

    public Integer getNumMonths() {
        return numMonths;
    }

    public void setNumMonths(Integer numMonths) {
        this.numMonths = numMonths;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Integer getBestDay() {
        return bestDay;
    }

    public void setBestDay(Integer bestDay) {
        this.bestDay = bestDay;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}