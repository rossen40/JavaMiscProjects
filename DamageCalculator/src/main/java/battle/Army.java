package main.java.battle;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.config.ArmyStats;
import main.java.config.ArmySubType;
import main.java.config.ArmyType;
import main.java.config.CalculationsHelper;
import main.java.config.ConfigManager;
import main.java.config.Configuration;

public class Army implements Comparable<Army> {

    private static Logger logger = LoggerFactory.getLogger(Army.class);

    protected Map<ArmyType, Double> damageVsOthers = new HashMap<>();
    protected Map<ArmyType, Double> damageReductionVsOthers = new HashMap<>();

    private ArmyType type;
    private ArmySubType subType;
    private int tier;
    private int number;
    private int totalLosses = 0;
    private ArmyStats armyStats;

    private int temporaryLosses = 0;

    public Army(ArmyType type, int tier, int number) {
        this.type = type;
        this.tier = tier;
        this.number = number;

        Configuration configuration = ConfigManager.getInstance().getConfiguration();
        this.subType = configuration.TYPE_TO_SUBTYPE_MAP.get(type)[tier];
    }

    public void addModifiedArmyStats(CalculationsHelper helper) {
        Configuration configuration = ConfigManager.getInstance().getConfiguration();
        ArmyStats baseArmyStats = configuration.BASE_UNIT_STATS_PER_ARMYTYPE.get(type).get(tier);

        double attack = calculateAttack(baseArmyStats, helper);
        double defense = calculateDefense(baseArmyStats, helper);
        double health = calculateHealth(baseArmyStats, helper);
        double damage = calculateDamage(baseArmyStats, helper);
        double damageReduction = calculateDamageReduction(baseArmyStats, helper);
        armyStats = new ArmyStats(attack, defense, health, damage, damageReduction);

        Map<ArmyType, Double> specificEfficienciesMap = helper.SPECIFIC_EFFICIENCY.get(getType());
        if (specificEfficienciesMap != null && !specificEfficienciesMap.isEmpty()) {
            if (ArmyType.INFANTRY == getType()) {
                damageReductionVsOthers.putAll(specificEfficienciesMap);
            } else {
                damageVsOthers.putAll(specificEfficienciesMap);
            }
        }

    }

    public void addDamageVsOthers(CalculationsHelper helper) {
    }

    @Override
    public int compareTo(Army object) {
        int result = this.getType().compareTo(object.getType());

        if (result == 0) {
            result = Integer.compare(this.getTier(), this.getTier());
        }

        return result;
    }

    public void updateLosses() {
        totalLosses += number - temporaryLosses >= 0 ? temporaryLosses : number;
        number = Math.max(0, number - temporaryLosses);
        temporaryLosses = 0;
    }

    private double calculateAttack(ArmyStats armyStats, CalculationsHelper helper) {
        double baseAttack = armyStats.getAttack();
        logger.trace(this + " base attack:\t\t" + baseAttack);

        double modifiedAttack = baseAttack * (1 + (helper.ATTACK_MODIFIERS.get(getType())) / 100);
        logger.trace(this + " modified attack:\t" + modifiedAttack);

        return modifiedAttack;
    }

    private double calculateDefense(ArmyStats armyStats, CalculationsHelper helper) {
        double baseDefense = armyStats.getDefense();
        logger.trace(this + " base defense:\t\t" + baseDefense);

        double modifiedDefense = baseDefense * (1 + (helper.DEFENSE_MODIFIERS.get(getType())) / 100);
        logger.trace(this + " modified defense:\t" + modifiedDefense);

        return modifiedDefense;
    }

    private double calculateHealth(ArmyStats armyStats, CalculationsHelper helper) {
        double baseHealth = armyStats.getHealth();
        logger.trace(this + " base defense:\t\t" + baseHealth);

        double modifiedHealth = baseHealth * (1 + (helper.HEALTH_MODIFIERS.get(getType())) / 100);
        logger.trace(this + " modified defense:\t" + modifiedHealth);

        return modifiedHealth;
    }

    private double calculateDamage(ArmyStats armyStats, CalculationsHelper helper) {
        double modifiedDamage = helper.DAMAGE_MODIFIERS.get(getType());
        logger.trace(this + " modified damage:\t" + modifiedDamage);

        return modifiedDamage;
    }

    private double calculateDamageReduction(ArmyStats armyStats, CalculationsHelper helper) {
        double modifiedDamageReduction = helper.DAMAGE_REDUCTION_MODIFIERS.get(getType());
        logger.trace(this + " modified DamageReduction:\t" + modifiedDamageReduction);

        return modifiedDamageReduction;
    }

    public ArmyType getType() {
        return type;
    }

    public void setType(ArmyType type) {
        this.type = type;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArmySubType getSubType() {
        return subType;
    }

    public void setSubType(ArmySubType subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "[type=" + type + "[" + (tier + 1) + "], subType=" + subType + ", number=" + number + ", losses=" + totalLosses + "]";
    }

    public ArmyStats getArmyStats() {
        return armyStats;
    }

    public void setDamageVsOthers(Map<ArmyType, Double> damageVsOthers) {
        this.damageVsOthers = damageVsOthers;
    }

    public void setDamageReductionVsOthers(Map<ArmyType, Double> damageReductionVsOthers) {
        this.damageReductionVsOthers = damageReductionVsOthers;
    }

    public void setArmyStats(ArmyStats armyStats) {
        this.armyStats = armyStats;
    }

    public Map<ArmyType, Double> getDamageVsOthers() {
        return damageVsOthers;
    }

    public Map<ArmyType, Double> getDamageReductionVsOthers() {
        return damageReductionVsOthers;
    }

    public void addLosses(int losses) {
        this.temporaryLosses += losses;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public int getTemporaryLosses() {
        return temporaryLosses;
    }


    public String getTypeForPrinting() {
        return type + "[" + (tier + 1) + "]=";
    }

    @Override
    public Army clone() {
        Army army = new Army(type, tier, number);
        army.setArmyStats(armyStats.clone());
        army.setDamageReductionVsOthers(damageReductionVsOthers);
        army.setDamageVsOthers(damageVsOthers);
        return army;
    }

}
