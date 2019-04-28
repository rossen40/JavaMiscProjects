package main.java.battle;

import java.util.List;

public class BattleWithBestChance extends Battle {

    public BattleWithBestChance(List<Army> attacker, List<Army> defender) {
        super(attacker, defender);
        type = BattleType.FULL_CHANCE;
    }

    @Override
    protected boolean isHighChance() {
        return true;
    }
}
