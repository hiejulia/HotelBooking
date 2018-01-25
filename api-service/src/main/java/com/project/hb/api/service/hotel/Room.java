package com.project.hb.api.service.hotel;




class Room {

    private int numOfPeople;

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    @Override
    public String toString() {
        return "Table{" +
                "numOfPeople=" + numOfPeople +
                '}';
    }

    public Room(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }
}
