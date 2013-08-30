package com.izzoh.walenje;

import android.os.Parcel;
import android.os.Parcelable;

public class Supermarket implements Parcelable {
	private String name;
	private int points;
	private int icon;

	public Supermarket(String name, int points, int icon) {
		this.points = points;
		this.icon = icon;
		this.name = name;
	}

	public Supermarket() {
		this("", 0, -1);
	}
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(points);
		dest.writeString(name);
		dest.writeInt(icon);
	}

	private Supermarket(Parcel in) {
		points = in.readInt();
		name = in.readString();
		icon = in.readInt();
	}

	public static final Parcelable.Creator<Supermarket> CREATOR = new Parcelable.Creator<Supermarket>() {

		@Override
		public Supermarket createFromParcel(Parcel in) {
			return new Supermarket(in);
		}

		@Override
		public Supermarket[] newArray(int size) {
			return new Supermarket[size];
		}

	};

	public int getPoints() {
		return points;
	}

	public int getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}
}
