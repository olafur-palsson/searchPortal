/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server.user
 */

package server.user;

import server.booking.Booking;

import java.util.HashMap;
import java.util.Map;

public class User extends UserEntity {
	private Map<Long, Booking> bookings = new HashMap<>();

	public User(Long id, String name, String email, Map<Integer, Long> bookings) {
		super(id, name, email, bookings);
	}

	public Map<Long, Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Map<Long, Booking> bookings) {
		this.bookings = bookings;
	}
}
