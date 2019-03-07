package com.assignment.alt_shift_cs991;

import android.os.Parcel;
import android.os.Parcelable;

public class ShiftModel implements Parcelable {

    private String name;
    private String description;
    private String workingDate;

    public ShiftModel(String name, String description, String workingDate) {
        this.name = name;
        this.description = description;
        this.workingDate = workingDate;
    }

    protected ShiftModel(Parcel in) {
        name = in.readString();
        description = in.readString();
        workingDate = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getWorkingDate(){
        return workingDate;
    }
    public void setWorkingDate(String workingDate){
        this.workingDate = workingDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeSerializable(workingDate);
    }

    public static final Creator<ShiftModel> CREATOR = new Creator<ShiftModel>() {
        @Override
        public ShiftModel createFromParcel(Parcel in) {
            return new ShiftModel(in);
        }

        @Override
        public ShiftModel[] newArray(int size) {
            return new ShiftModel[size];
        }
    };

}

