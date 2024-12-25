package com.nickz.flatfinder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "apartment")
@Getter
@Setter
@ToString
public class Apartment {

    @Id
    private String id;

    private String url;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Apartment apartment = (Apartment) o;
        return Objects.equals(id, apartment.id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }
}
