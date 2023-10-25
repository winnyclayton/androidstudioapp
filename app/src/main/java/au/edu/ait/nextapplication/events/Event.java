package au.edu.ait.nextapplication.events;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "EVENT")
public class Event implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;
    @ColumnInfo(name = "NAME")
    private String name;
    @ColumnInfo(name = "START_DATE")
    private String startDate;
    @ColumnInfo(name = "START_TIME")
    private String startTime;
    @ColumnInfo(name = "END_DATE")
    private String endDate;
    @ColumnInfo(name = "END_TIME")
    private String endTime;
    @ColumnInfo(name = "ADDRESS")
    private String address;
    @ColumnInfo(name = "SUBURB")
    private String suburb;
    @ColumnInfo(name = "STATE")
    private String state;
    @ColumnInfo(name = "POSTCODE")
    private String postcode;
    @ColumnInfo(name = "EVENT_TYPE")
    private String type;
    @ColumnInfo(name = "POSTER")
    private String poster;



    //we don't use but we need it here because of java rules
    public Event() {
    }

    public Event( String name, String startDate, String startTime, String endDate, String endTime, String address, String suburb, String state, String postcode, String type, String poster) {

        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.address = address;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.type = type;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", endTime='" + endTime + '\'' +
                ", address='" + address + '\'' +
                ", suburb='" + suburb + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
