/**
 * Created by Benjamin Ehlers on 3/17/2019.
 */
public class Change {

    private int coinValue;

    private boolean used;

    public Change(int coinValue) {
        this.coinValue = coinValue;
        this.used = false;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
