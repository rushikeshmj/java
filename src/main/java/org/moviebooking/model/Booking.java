package org.moviebooking.model;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    //constructor
    public Booking() {
    }

    public Booking(Show show) {
        this.show = show;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    //toString
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", show=" + show +
                '}';
    }


}

