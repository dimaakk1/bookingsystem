package org.example.event.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Event extends PanacheEntity {
    public String name;
    public String location;
    public int seats;
}
