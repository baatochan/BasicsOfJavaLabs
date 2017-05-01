/**
 * Program: RoomCollectionComparisonApp - Porównaj kolekcje
 * Program ukazujący podobieństwa i różnice pomiędzy różnymi typami kolekcji zawierającymi niestandatowy typ danych.
 *
 * Klasa: Room
 * Niestandardowy moduł danych.
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 11 grudnia 2016r.
 *
 * Created by barto on 11.12.16.
 */

public class Room implements Comparable<Room> {
	private String buildingName;
	private int roomNumber;
	private String description;
	
	public String getBuildingName() {
		return buildingName;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public String getDescription() {
		return description;
	}
	
	public Room(String buildingName, int roomNumber, String description) {
		this.buildingName = buildingName;
		this.roomNumber = roomNumber;
		this.description = description;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(buildingName);
		sb.append("/");
		sb.append(roomNumber);
		sb.append(" : ");
		sb.append(description);
		return sb.toString();
	}
	
	@Override
	public int hashCode(){
		return (buildingName+"/"+roomNumber).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean sameBuilding = buildingName.equals(((Room)obj).getBuildingName());
		boolean sameRoomNumber = roomNumber == ((Room)obj).getRoomNumber();
		return sameBuilding && sameRoomNumber;
	}
	
	@Override
	public int compareTo(Room o) {
		if (buildingName.equals(o.getBuildingName())) return roomNumber - o.roomNumber;
		return buildingName.compareTo(o.getBuildingName());
	}
}
