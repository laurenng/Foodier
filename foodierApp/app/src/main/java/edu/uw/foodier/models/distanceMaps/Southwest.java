
package edu.uw.foodier.models.distanceMaps;
// Created by Lauren for the googleMapsAPI results
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;

public class Southwest implements Parcelable
{

    @com.squareup.moshi.Json(name = "lat")
    private Double lat;
    @com.squareup.moshi.Json(name = "lng")
    private Double lng;
    public final static Creator<Southwest> CREATOR = new Creator<Southwest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Southwest createFromParcel(android.os.Parcel in) {
            return new Southwest(in);
        }

        public Southwest[] newArray(int size) {
            return (new Southwest[size]);
        }

    }
    ;

    protected Southwest(android.os.Parcel in) {
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lng = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Southwest() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Southwest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lat");
        sb.append('=');
        sb.append(((this.lat == null)?"<null>":this.lat));
        sb.append(',');
        sb.append("lng");
        sb.append('=');
        sb.append(((this.lng == null)?"<null>":this.lng));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lng);
    }

    public int describeContents() {
        return  0;
    }

}
