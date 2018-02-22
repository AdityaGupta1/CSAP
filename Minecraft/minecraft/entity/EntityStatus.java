package minecraft.entity;

public class EntityStatus implements Cloneable {
    private boolean dead = false;

    private final int maxHealth;
    private int health;

    public EntityStatus(int maxHealth, int health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }

    public EntityStatus(int maxHealth) {
        this(maxHealth, maxHealth);
    }

    public void damage(int damage) {
        if (dead) {
            return;
        }

        this.health -= damage;

        if (this.health <= 0) {
            this.health = 0;
            this.dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public EntityStatus clone() {
        return new EntityStatus(this.maxHealth, this.health);
    }

    @Override
    public String toString() {
        return dead ? "dead" : "health: " + health + "/" + maxHealth;
    }
}
