/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server.booking;


import server.Converter;
import server.ToolBox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Booking {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private Long hotelId;
	private Long userId;
	private String roomType;
	private Boolean isPaid;
	private Long dateFrom;
	private Long dateTo;

	public Booking(Long hotelId, String roomType, Long from, Long to) {
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.dateFrom = from;
		this.dateTo = to;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean paid) {
		isPaid = paid;
	}

	public Long getFrom() {
		return dateFrom;
	}

	public void setFrom(Date from) {
		assert from.getTime() / 86400000 == 0;
		this.dateFrom = from.getTime();
	}

	public Long getTo() {
		return dateTo;
	}

	public void setTo(Date to) {
		assert to.getTime() / 86400000 == 0;
		this.dateTo = to.getTime();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

