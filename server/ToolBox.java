/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server;

//ma alveg endurskyra ef hann faer a sig skyrt purpose
public class ToolBox {
	private static final int dayInMs = 86400000;

	public static long formatToMidnight(long date) {
		return (date / dayInMs) * dayInMs;
	}

}
