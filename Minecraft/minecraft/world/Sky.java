package minecraft.world;

public class Sky {
    // 0000 to 1440
    // 1 second real time --> 1 minute minecraft time
    private int time = 360 - 1;

    public void elapseTime(int time) {
        this.time += time;

        if (this.time >= 1440) {
            this.time = this.time % 1440;
        }

        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public int getTime() {
        return time;
    }

    public boolean isNight() {
        // 19:00 to 3:00
        return time > 1140 || time < 300;
    }

    @Override
    public String toString() {
        return String.format("%02d", (time / 60)) + ":" + String.format("%02d", (time % 60));
    }
}
