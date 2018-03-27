package server.user;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity // This tells Hibernate to make a table out of this class
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	@ElementCollection
	private Map<Integer, Long> bookings = new HashMap<>();

	public UserEntity(Long id, String name, String email, Map<Integer, Long> bookings) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.bookings = bookings;
	}

	public UserEntity(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public UserEntity extractEntity() {
		return new UserEntity(id, name, email, bookings);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void addBooking(Long bookingId) {
		int bookingNumber = bookings.size();
		this.bookings.put(bookingNumber, bookingId);
	}
}
