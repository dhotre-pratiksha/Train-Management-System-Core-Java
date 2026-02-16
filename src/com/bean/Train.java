package com.bean;

public class Train {
    private int trainId;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double fare;

    public Train(int trainId, String trainName, String source, String destination, int totalSeats, double fare){
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fare = fare;
    }

    public int getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "\nTrain Id : "+trainId+
                "\nTrain Name : "+trainName+
                "\nSource : "+source+
                "\nDestination : "+destination+
                "\nTotal Seats "+totalSeats+
                "\nAvailable Seats : "+availableSeats+
                "\nFare : "+fare;
    }

    public boolean bookSeats(int count){
        if(count <= availableSeats){
            availableSeats -= count;
            return true;
        }
        return false;
    }

    public void cancelSeats(int count){
        availableSeats += count;
    }
}
