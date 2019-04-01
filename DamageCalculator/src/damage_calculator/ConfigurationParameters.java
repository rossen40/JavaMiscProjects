package damage_calculator;

import java.util.Map;

public class ConfigurationParameters {

    private int ABSOLUTE_MAX_TIER = 12;
    public Map<Integer, Integer> CASTLE_BASE_MARCH_CAPACITY;
    public Map<ArmyType, ArmySubType[]> TYPE_TO_SUBTYPE_MAP;
    public ArmySubType[] distanceSubtypes = new ArmySubType[ABSOLUTE_MAX_TIER];
    public ArmySubType[] cavalrySubtypes = new ArmySubType[ABSOLUTE_MAX_TIER];
    public Map<ArmyType, int[]> BASE_ATTACK_FACTORS;

    public ConfigurationParameters() {
        // {
        // Array index is tier number -1, i.e. tier 5 has index 4, value is subtype
        // Map<ArmyType, ArmySubType[]> tempMap = new HashMap<>();
        // distanceSubtypes[0] = ArmySubType.RIFLEMEN;
        // distanceSubtypes[1] = ArmySubType.GRENADIERS;
        // distanceSubtypes[2] = ArmySubType.GRENADIERS;
        // distanceSubtypes[3] = ArmySubType.RIFLEMEN;
        // distanceSubtypes[4] = ArmySubType.GRENADIERS;
        // distanceSubtypes[5] = ArmySubType.GRENADIERS;
        // distanceSubtypes[6] = ArmySubType.RIFLEMEN;
        // distanceSubtypes[7] = ArmySubType.RIFLEMEN;
        // distanceSubtypes[8] = ArmySubType.GRENADIERS;
        // distanceSubtypes[9] = ArmySubType.RIFLEMEN;
        // distanceSubtypes[10] = ArmySubType.GRENADIERS;
        // distanceSubtypes[11] = ArmySubType.RIFLEMEN;
        // tempMap.put(ArmyType.DISTANCE, distanceSubtypes);
        //
        // cavalrySubtypes[0] = ArmySubType.HEAVY_CAVALRY;
        // cavalrySubtypes[1] = ArmySubType.LIGHT_CAVALRY;
        // cavalrySubtypes[2] = ArmySubType.HEAVY_CAVALRY;
        // cavalrySubtypes[3] = ArmySubType.HEAVY_CAVALRY;
        // cavalrySubtypes[4] = ArmySubType.LIGHT_CAVALRY;
        // cavalrySubtypes[5] = ArmySubType.HEAVY_CAVALRY;
        // cavalrySubtypes[6] = ArmySubType.LIGHT_CAVALRY;
        // cavalrySubtypes[7] = ArmySubType.HEAVY_CAVALRY;
        // cavalrySubtypes[8] = ArmySubType.LIGHT_CAVALRY;
        // cavalrySubtypes[9] = ArmySubType.LIGHT_CAVALRY;
        // cavalrySubtypes[10] = ArmySubType.HEAVY_CAVALRY;
        // cavalrySubtypes[11] = ArmySubType.LIGHT_CAVALRY;
        // tempMap.put(ArmyType.CAVALRY, cavalrySubtypes);
        //
        // ArmySubType[] infantrySubtypes = new ArmySubType[ABSOLUTE_MAX_TIER];
        // for (int i = 0; i < ABSOLUTE_MAX_TIER; i++) {
        // if (i % 2 == 0) {
        // infantrySubtypes[i] = ArmySubType.MUSKETEERS;
        // } else {
        // infantrySubtypes[i] = ArmySubType.RIFLEMEN;
        // }
        // }
        // tempMap.put(ArmyType.INFANTRY, infantrySubtypes);
        //
        // ArmySubType[] artillerySubtypes = new ArmySubType[ABSOLUTE_MAX_TIER];
        // artillerySubtypes[0] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[1] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[2] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[3] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[4] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[5] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[6] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[7] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[8] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[9] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[10] = ArmySubType.SUB_ARTILLERY1;
        // artillerySubtypes[11] = ArmySubType.SUB_ARTILLERY1;
        // tempMap.put(ArmyType.ARTILLERY, artillerySubtypes);
        //
        // TYPE_TO_SUBTYPE_MAP = Collections.unmodifiableMap(tempMap);
        // }
        //
        // {
        // Map<ArmyType, int[]> tempMap = new HashMap<>();
        // tempMap.put(ArmyType.DISTANCE, new int[] { 106, 143, 165, 161, 218, 251, 245, 282, 382, 373, 505, 493 });
        // tempMap.put(ArmyType.CAVALRY, new int[] { 113, 111, 150, 173, 169, 228, 223, 302, 295, 339, 459, 449 });
        // tempMap.put(ArmyType.INFANTRY, new int[] { 34, 39, 45, 52, 60, 68, 79, 91, 104, 120, 138, 158 });
        // tempMap.put(ArmyType.ARTILLERY, new int[] { 51, 91, 68, 121, 89, 160, 184, 136, 243, 180, 321, 237 });
        // BASE_ATTACK_FACTORS = Collections.unmodifiableMap(tempMap);
        // }
        //
        // Map<Integer, Integer> tempMap = new HashMap<>();
        // tempMap.put(30, 97500);
        // tempMap.put(32, 106500);
        //
        // CASTLE_BASE_MARCH_CAPACITY = Collections.unmodifiableMap(tempMap);
    }

}