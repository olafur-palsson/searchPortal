/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server;

import server.availability.AvailabilityRepository;
import server.booking.BookingRepository;
import server.hotel.Hotel;
import server.hotel.HotelEntity;
import server.hotel.HotelRepository;
import server.room.Room;
import server.room.RoomEntity;
import server.room.RoomRepository;
import server.user.User;
import server.user.UserEntity;
import server.user.UserRepository;

import java.util.*;

import static java.util.Arrays.*;

public class Converter {
	private static RoomRepository roomRepository;
	private static AvailabilityRepository availabilityRepository;
	private static UserRepository userRepository;
	private static BookingRepository bookingRepository;
	private static HotelRepository hotelRepository;

	public static Map<Integer, ?> arrayListToMap(ArrayList<?> arrayList) {
		Map<Integer, Object> map = new HashMap<>();
		for(int i = 0; i < arrayList.size(); i++)
			map.put(i, arrayList.get(i));
		return map;
	}

	//assume format yyyy-mm-dd as the input for simplcity of use
	public static Long yyyymmdd_toLong(String dateString) {
		String[] yearMonthDay = dateString.split("-");
		Calendar c = new GregorianCalendar();
		c.set(
			 Integer.parseInt(yearMonthDay[0]),
			 Integer.parseInt(yearMonthDay[1]) - 1,
			 Integer.parseInt(yearMonthDay[2])
		);
		return c.getTime().getTime();
	}

	public static Room  toRoom(RoomEntity entity) { return (Room)  entity; }
	public static Hotel toHotel(HotelEntity entity) { return (Hotel) entity; }
	public static User  toUser (UserEntity  entity) { return (User)  entity; }

	public static Iterable<Room> toRooms(Iterable<RoomEntity> entities) {
		List<Room> rooms = new ArrayList<>();
		for(RoomEntity re : entities)
			rooms.add(toRoom(re));
		return rooms;
	}

	public static Iterable<Hotel> toHotels(Iterable<HotelEntity> entities) {
		List<Hotel> hotels = new ArrayList<>();
		for(HotelEntity he : entities)
			hotels.add(toHotel(he));
		return hotels;
	}

	public static Iterable<User> convertToUsers(Iterable<UserEntity> entities) {
		List<User> users = new ArrayList<>();
		for(UserEntity ue : entities)
			users.add(toUser(ue));
		return users;
	}

	public static void setupRoomEntitiesForHotel(Hotel hotel) {
		Map<String, RoomEntity> roomEntities = new HashMap<>();
		Map<String, Long> roomIds = hotel.getRoomIds();
		for(Map.Entry<String, Long> entry : roomIds.entrySet())
			roomEntities.put(entry.getKey(), roomRepository.findOne(entry.getValue()));
		hotel.setRoomEntities(roomEntities);
	}

	public static void roomEntitiesToRoomsForHotel(Hotel hotel) {
		Map<String, Room> rooms = new HashMap<>();
		Map<String, RoomEntity> entityMap =  hotel.getRoomEntities();
		for(Map.Entry<String, RoomEntity> entry : entityMap.entrySet())
			rooms.put(entry.getKey(), (Room) entry.getValue());
		hotel.setRooms(rooms);
	}

	public static Hotel hotelEntityToFullHotel(HotelEntity entity) {
		Hotel hotel = toHotel(entity);
		setupRoomEntitiesForHotel(hotel);
		roomEntitiesToRoomsForHotel(hotel);
		return hotel;
	}

	public static Iterable<Hotel> hotelEntititiesToFullHotels(Iterable<HotelEntity> entities) {
		List<Hotel> hotels = new ArrayList<>();
		for(HotelEntity entity : entities)
			hotels.add(hotelEntityToFullHotel(entity));
		return hotels;
	}

	public static RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public static void setRoomRepository(RoomRepository roomRepository) {
		Converter.roomRepository = roomRepository;
	}

	public static AvailabilityRepository getAvailabilityRepository() {
		return availabilityRepository;
	}

	public static void setAvailabilityRepository(AvailabilityRepository availabilityRepository) {
		Converter.availabilityRepository = availabilityRepository;
	}

	public static UserRepository getUserRepository() {
		return userRepository;
	}

	public static void setUserRepository(UserRepository userRepository) {
		Converter.userRepository = userRepository;
	}

	public static BookingRepository getBookingRepository() {
		return bookingRepository;
	}

	public static void setBookingRepository(BookingRepository bookingRepository) {
		Converter.bookingRepository = bookingRepository;
	}

	public static HotelRepository getHotelRepository() {
		return hotelRepository;
	}

	public static void setHotelRepository(HotelRepository hotelRepository) {
		Converter.hotelRepository = hotelRepository;
	}
}
