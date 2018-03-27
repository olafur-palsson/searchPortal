/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server.hotel
 */

package server.hotel;

import server.room.Room;
import server.room.RoomEntity;
import server.room.RoomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel extends HotelEntity {

	private Map<String, Room> rooms = new HashMap<>();
	private Map<String, RoomEntity> roomEntities = new HashMap<>();

	public Hotel(Integer numRooms, String name, String email, Double longtitude, double latitude, Map<Integer, String> amenities) {
		super(numRooms, name, email, longtitude, latitude, amenities);
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}

	public Map<String, RoomEntity> getRoomEntities() {
		return roomEntities;
	}

	public void setRoomEntities(Map<String, RoomEntity> roomEntities) {
		this.roomEntities = roomEntities;
	}
}
