package mainApp.model;

import java.util.HashMap;
import java.util.Map;

public class ParadoxInformationConstants {
	public static Map<String, PanelType> panelTypes = new HashMap<String, PanelType>();
	static {
		panelTypes.put("0xA122", PanelType.EVO48);
		panelTypes.put("0xA133", PanelType.EVO48);
		panelTypes.put("0xA159", PanelType.EVO48);
		panelTypes.put("0xA123", PanelType.EVO192);
		panelTypes.put("0xA15A", PanelType.EVO192);
		panelTypes.put("0xA16D", PanelType.EVOHD);
		panelTypes.put("0xA41E", PanelType.SP5500);
		panelTypes.put("0xA450", PanelType.SP5500);
		panelTypes.put("0xA41F", PanelType.SP6000);
		panelTypes.put("0xA451", PanelType.SP6000);
		panelTypes.put("0xA420", PanelType.SP7000);
		panelTypes.put("0xA452", PanelType.SP7000);
		panelTypes.put("0xA524", PanelType.MG5000);
		panelTypes.put("0xA526", PanelType.MG5050);
		panelTypes.put("0xAE53", PanelType.SP4000);
		panelTypes.put("0xAE54", PanelType.SP65);
	};

}
