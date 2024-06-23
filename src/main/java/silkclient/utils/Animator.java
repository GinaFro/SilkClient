package silkclient.utils;

public class Animator {

    private float f;
    private long l;

    public Animator(float f) {
        this.f = f;
    }

    public float getValue(float f, float f1, boolean flag) {
        float t = (float) (System.currentTimeMillis() - this.l) / this.f;

        t = t < 0.5F ? 4.0F * t * t * t : (t - 1.0F) * (2.0F * t - 2.0F) * (2.0F * t - 2.0F) + 1.0F;
        float value = flag ? f + t * (f1 - f) : f1 + t * (f - f1);

        if (flag && f1 < value) {
            value = f1;
        } else if (!flag && value < f) {
            value = f;
        }

        return value;
    }

    public int getValue(int f, int f1, boolean flag) {
        return (int) this.getValue((float) f, (float) f1, flag);
    }

    public void reset() {
        this.l = System.currentTimeMillis();
    }

}
