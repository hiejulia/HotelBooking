package com.project.hb.restaurant.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

public class Room extends BaseEntity<BigInteger> {

    private int numOfPeople;


    public Room(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id, @JsonProperty("numOfPeople") int numOfPeople) {
        super(id, name);
        this.numOfPeople = numOfPeople;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    @Override
    public String toString() {
        return "Room{" +
                "numOfPeople=" + numOfPeople +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
