
package server.hotel;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class HotelEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Integer numRooms;
	private Double  latitude;
	private Double longitude;
	private String  name;
	private String  email;
	//roomType, ID
	@ElementCollection
	private Map<String, Long> roomIds;
	@ElementCollection
	private Map<Integer, String> amenities;
	//private Date[] closedDates;

	public HotelEntity() { }

	public HotelEntity(Integer numRooms, String name, String email, Double longitude, double latitude, Map<Integer, String> amenities) {
		this.setNumRooms(numRooms);
		this.setName(name);
		this.setEmail(email);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		this.setAmenities(amenities);
	}

	public HotelEntity(long id, int numRooms, double latitude, double longitude, String name, String email, Map<Integer, String> amenities) {
		HotelEntity hotel = new HotelEntity(numRooms, name, email, longitude, latitude, amenities);
		hotel.setId(id);
	}

	public HotelEntity extractEntity() {
		return new HotelEntity(id, numRooms, latitude, longitude, name, email, amenities);
	}

	public Long getId()           { return id;     }
	public void    setId(Long id) { this.id = id;  }
  
	public Integer getNumRooms()                 { return numRooms;          } 
	public void    setNumRooms(Integer numRooms) { this.numRooms = numRooms; }

	public double getLatitude()                 { return latitude;         } 
	public void   setLatitude(double latitude) { this.latitude = latitude; }

	public Double getLongitude()                   { return longitude;            }
	public void setLongitude(Double longitude)  { this.longitude = longitude; }

	public String getName()             { return name;     } 
	public void   setName(String name) { this.name = name; }

	public String getEmail()             { return email;       } 
	public void   setEmail(String email) { this.email = email; }

	public void setAmenities(Map<Integer, String> amenities) {
		this.amenities = amenities;
	}

	public Map<String, Long> getRoomIds() { return roomIds; }

	public void setRoomIds(HashMap<String, Long> rooms) { this.roomIds = rooms; }

	public void addRoomId(String roomType, Long roomEntityId) {
		roomIds.put(roomType, roomEntityId);
	}

	public void setRoomIds(Map<String, Long> roomEntityList) {
		Map<String, Long> newRooms = new HashMap<>();
		for(Map.Entry<String, Long> e : roomEntityList.entrySet())
			newRooms.put(e.getKey(), e.getValue());
		this.roomIds = newRooms;
	}
}

