package minecraft.entity.monster;

import minecraft.entity.Entity;
import minecraft.game.Game;
import minecraft.game.event.EventGenerator;

public abstract class Monster extends Entity {
    protected abstract int getMinDamage();
    protected abstract int getMaxDamage();
    protected int getAttackDamage() {
        return Game.random.nextInt(getMaxDamage() - getMinDamage() + 1) + getMinDamage();
    }

    protected abstract double getFightChance();

    protected abstract String getHitMessage();
    protected abstract String getMissMessage();

    public void fight() {
        if (EventGenerator.random(getFightChance())) {
            System.out.println(getHitMessage());
            Game.player.damage(getAttackDamage());
        } else {
            System.out.println(getMissMessage());
        }
    }
}
