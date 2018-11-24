package mainApp.parser;

import mainApp.ParadoxUtil;
import mainApp.ZoneStateFlags;
import mainApp.model.PartitionState;
import mainApp.model.Version;
import mainApp.model.ZoneState;

public class EvoParser extends AbstractParser  {
    public PartitionState calculatePartitionState(byte[] partitionFlags) {
        byte firstByte = partitionFlags[0];
        PartitionState state = new PartitionState();
        state.setArmed(ParadoxUtil.isBitSet(firstByte, 0));
        state.setArmedInAway(ParadoxUtil.isBitSet(firstByte, 1));
        state.setArmedInStay(ParadoxUtil.isBitSet(firstByte, 2));
        state.setArmedInNoEntry(ParadoxUtil.isBitSet(firstByte, 3));

        state.setInAlarm(ParadoxUtil.isBitSet(firstByte, 4));
        state.setInSilentAlarm(ParadoxUtil.isBitSet(firstByte, 5));
        state.setInAudibleAlarm(ParadoxUtil.isBitSet(firstByte, 6));
        state.setInFireAlarm(ParadoxUtil.isBitSet(firstByte, 7));

        byte secondByte = partitionFlags[1];
        state.setReadyToArm(ParadoxUtil.isBitSet(secondByte, 0));
        state.setInExitDelay(ParadoxUtil.isBitSet(secondByte, 1));
        state.setInEntryDelay(ParadoxUtil.isBitSet(secondByte, 2));
        state.setInTrouble(ParadoxUtil.isBitSet(secondByte, 3));
        state.setHasAlarmInMemory(ParadoxUtil.isBitSet(secondByte, 4));
        state.setInZoneBypass(ParadoxUtil.isBitSet(secondByte, 5));

        byte thirdByte = partitionFlags[2];
        state.setHasZoneInTamperTrouble(ParadoxUtil.isBitSet(thirdByte, 4));
        state.setHasZoneInLowBatteryTrouble(ParadoxUtil.isBitSet(thirdByte, 5));
        state.setHasZoneInFireLoopTrouble(ParadoxUtil.isBitSet(thirdByte, 6));
        state.setHasZoneInSupervisionTrouble(ParadoxUtil.isBitSet(thirdByte, 7));

        byte sixthByte = partitionFlags[5];
        state.setStayInstantReady(ParadoxUtil.isBitSet(sixthByte, 0));
        state.setForceReady(ParadoxUtil.isBitSet(sixthByte, 1));
        state.setBypassReady(ParadoxUtil.isBitSet(sixthByte, 2));
        state.setInhibitReady(ParadoxUtil.isBitSet(sixthByte, 3));
        state.setAllZoneClosed(ParadoxUtil.isBitSet(sixthByte, 4));

        return state;
    }

    public ZoneState calculateZoneState(int id, ZoneStateFlags zoneStateFlags) {

        int index = id / 8;
        int bitNumber = id % 8 - 1;

        byte[] zonesOpened = zoneStateFlags.getZonesOpened();
        boolean isOpened = ParadoxUtil.isBitSet(zonesOpened[index], bitNumber);

        byte[] zonesTampered = zoneStateFlags.getZonesTampered();
        boolean isTampered = ParadoxUtil.isBitSet(zonesTampered[index], bitNumber);

        byte[] zonesLowBattery = zoneStateFlags.getZonesLowBattery();
        boolean hasLowBattery = ParadoxUtil.isBitSet(zonesLowBattery[index], bitNumber);

        return new ZoneState(isOpened, isTampered, hasLowBattery);
    }

}
