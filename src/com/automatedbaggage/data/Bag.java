package com.automatedbaggage.data;

import com.automatedbaggage.resources.Util;

public class Bag {
	private String id;
	private Util entryPoint;
	private String flightId;

	public Bag() {

	}

	public Bag(String bagId, Util entryPoint, String flightId) {
		this.id = bagId;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Util getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(Util entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	@Override
	public String toString() {
		return "Bag{" + "id='" + id + '\'' + ", entryPoint=" + entryPoint + ", flightId='" + flightId + '\'' + '}';
	}
}
