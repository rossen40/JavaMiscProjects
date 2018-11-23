package mainApp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Zone} Paradox zone. ID is always numeric (1-8 for Evo192) States
 * are taken from cached RAM memory map and parsed.
 *
 * @author Konstantin_Polihronov - Initial contribution
 */
public class Zone extends Entity {

	private static Logger logger = LoggerFactory.getLogger(Zone.class);

	private ZoneState zoneState;

	public Zone(int id, String label) {
		super(id, label);
	}

	public ZoneState getZoneState() {
		return zoneState;
	}

	public void setZoneState(ZoneState zoneState) {
		this.zoneState = zoneState;
		logger.debug("Zone {} state updated to:\tOpened: {}, Tampered: {}, LowBattery: {}",
				new Object[] { getLabel(), zoneState.isOpened(), zoneState.isTampered(), zoneState.hasLowBattery() });
	}

}