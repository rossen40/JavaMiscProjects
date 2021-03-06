package mainApp.model;
/**
 * The {@link PanelType} Enum of all panel types
 *
 * @author Konstantin_Polihronov - Initial contribution
 */
public enum PanelType {
	EVO48, EVO192, EVOHD, SP5500, SP6000, SP7000, MG5000, MG5050, SP4000, SP65, UNKNOWN;

	public String toString() {
		return this.name();
	}
}
