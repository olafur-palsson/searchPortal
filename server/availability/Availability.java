/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server.availability;




	 import server.ToolBox;

	 import javax.persistence.*;
	 import java.util.Date;
	 import java.util.HashMap;
	 import java.util.Map;

@Entity // This tells Hibernate to make a table out of this class
public class Availability {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ElementCollection
	//long == date og integer == fjoldi herbergja
	private Map<Long, Integer> days = new HashMap<>();
	private static Map<Long, Integer> zeroAvailabilityMap() {
		Map<Long, Integer> zeroByDays = new HashMap<>();
		Long today = new Date().getTime();
		today = ToolBox.formatToMidnight(today);
		return zeroByDays;
	}

	public void setAvailabilityToZero() {
		setDays(zeroAvailabilityMap());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setAvailabilityForDate(Long date, int numberOfRooms) {
		long d = ToolBox.formatToMidnight(date);
		days.put(date, numberOfRooms);
	}

	public int getAvailabilityForDate(long date, int numberOfRooms) {
		return days.get(date);
	}
	
	public Map<Long, Integer> getDays() {
		return days;
	}

	public void setDays(Map<Long, Integer> days) {
		this.days = days;
	}
}
