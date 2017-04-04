package com.studentmanik.smartsales.BusinessObject;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ritu on 12/10/2016.
 */
public class Outlet implements Parcelable {
    int id;
    int srId;
    String name;
    String address;
    String ownerName;
    String mobile;
    double lat;
    double lon;
    double distance;
    int verified;
    int visited;

    public Outlet() {
    }


    protected Outlet(Parcel in) {
        id = in.readInt();
        srId = in.readInt();
        name = in.readString();
        address = in.readString();
        ownerName = in.readString();
        mobile = in.readString();
        lat = in.readDouble();
        lon = in.readDouble();
        distance = in.readDouble();
        verified = in.readInt();
        visited = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(srId);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(ownerName);
        dest.writeString(mobile);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeDouble(distance);
        dest.writeInt(verified);
        dest.writeInt(visited);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Outlet> CREATOR = new Creator<Outlet>() {
        @Override
        public Outlet createFromParcel(Parcel in) {
            return new Outlet(in);
        }

        @Override
        public Outlet[] newArray(int size) {
            return new Outlet[size];
        }
    };

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public double genareteDistance(Location location) {
        Location outletLocation=new Location("");
        outletLocation.setLatitude(this.lat);
        outletLocation.setLongitude(this.lon);
       this.distance= outletLocation.distanceTo(location);
        return distance;
    }
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMobile() {
        return mobile;
    }

    public int getSrId() {
        return srId;
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
