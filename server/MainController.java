package server;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import server.availability.Availability;
import server.availability.AvailabilityRepository;
import server.booking.Booking;
import server.booking.BookingRepository;
import server.hotel.HotelEntity;
import server.hotel.HotelRepository;
import server.room.RoomEntity;
import server.room.RoomRepository;
import server.user.UserEntity;
import server.user.UserRepository;
import sun.misc.Request;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/database") // This means URL's start with /demo (after Application path)
public class MainController {

	@Autowired private UserRepository         userRepository;
	@Autowired private HotelRepository        hotelRepository;
	@Autowired private RoomRepository         roomRepository;
	@Autowired private AvailabilityRepository availabilityRepository;
	@Autowired private BookingRepository      bookingRepository;

	/*********************************
	 *   USER METHODS
	 ********************************/
	@GetMapping(path="/addUser") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (
		 @RequestParam String name ,
		 @RequestParam String email
	) {
		UserEntity n = new UserEntity(name, email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/allUsers")
	public @ResponseBody Iterable<UserEntity> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path="addBookingToUser")
	public @ResponseBody String addBookingToUser(
		 @RequestParam Long userId,
		 @RequestParam Long bookingId
	) {
		UserEntity user = userRepository.findOne(userId);
		user.addBooking(bookingId);
		return "Done";
	}

	/*********************************
	 *   HOTEL METHODS
	 ********************************/
	@GetMapping(path="/addHotel")
	public @ResponseBody String addNewHotel (
		 @RequestParam String name,
		 @RequestParam String email,
		 @RequestParam double latitude,
		 @RequestParam Double longitude,
		 @RequestParam ArrayList<String> amenities,
		 @RequestParam Integer numRooms
	) {
		Map<Integer, String> amenityMap = (Map<Integer, String>) Converter.arrayListToMap(amenities);
		HotelEntity h = new HotelEntity(numRooms, name, email, longitude, latitude, amenityMap);
		hotelRepository.save(h);
		return "Saved the hotel";
	}

	@GetMapping(path="/allHotels")
	public @ResponseBody Iterable<HotelEntity> getAllHotels() {
		// This returns a JSON or XML with the users
		return hotelRepository.findAll();
	}

	/*********************************
	 *   ROOM METHODS
	 ********************************/
	@GetMapping(path="/addRoom")
	public @ResponseBody String addNewRoom (
		 @RequestParam String roomType,
		 @RequestParam Integer numberOfBeds,
		 @RequestParam Boolean extraBed
	) {
		RoomEntity re = new RoomEntity(roomType, numberOfBeds, extraBed);
		Availability av = new Availability();
		av.setAvailabilityToZero();
		av = availabilityRepository.save(av);
		re.setAvailabilityId(av.getId());
		return "Saved a new room";
	}

	/*********************************
	 *   BOOKING METHODS
	 ********************************/
	//all dateString: yyyy-mm-dd
	private Booking makeBasicBooking(Long hotelId, String roomType, String dateFrom, String dateTo) {
		Long from = Converter.yyyymmdd_toLong(dateFrom);
		Long to   = Converter.yyyymmdd_toLong(dateTo);
		return new Booking(hotelId, roomType, from, to);
	}

	//return bookingID
	@GetMapping(path="/addBooking")
	public @ResponseBody String addNewBooking(
		 @RequestParam Long hotelId,
		 @RequestParam String roomType,
		 @RequestParam String dateFrom, //yyyy-mm-dd
		 @RequestParam String dateTo    //yyyy-mm-dd
	) {
		Booking booking = makeBasicBooking(hotelId, roomType, dateFrom, dateTo);
		booking = bookingRepository.save(booking);
		return booking.getId().toString();
	}

	@GetMapping(path="/addBookingWithUser")
	public @ResponseBody String addNewBooking(
		 @RequestParam Long hotelId,
		 @RequestParam Long userId,
		 @RequestParam String roomType,
		 @RequestParam String dateFrom, //yyyy-mm-dd
		 @RequestParam String dateTo    //yyyy-mm-dd
	) {
		Booking booking = makeBasicBooking(hotelId, roomType, dateFrom, dateTo);
		booking.setUserId(userId);
		booking = bookingRepository.save(booking);
		return booking.getId().toString();
	}

}
