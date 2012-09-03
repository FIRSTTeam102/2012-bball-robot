/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Team102Lib;

/**
 *
 * @author Administrator
 */
public class TextMeter {

    char majorTick = '|';
    char minorTick = '\'';
    int majorTickIncrement = 10;
    int width;
    double lowerBound;
    double upperBound;
    double value;
    boolean showValue;
    String valueFormat = "%4.1f";

    public TextMeter(double lowerBound, double upperBound, int width) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.width = width;
        this.value = 0.0;
        this.showValue = false;
    }

    public TextMeter() {
        this(0.0, 100.0, 20);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setShowValue(boolean ssv) {
        this.showValue = ssv;
    }

    public void setMajorTickIncrement(int increment) {
        this.majorTickIncrement = increment;
    }

    protected String buildMeterText(int numTicks) {
        if (numTicks < 0) {
            return "-";
        } else if (numTicks == 0) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer(width);
            for (int i = 1; i <= Math.min(numTicks, width); i++) {
                if ((i == width) && (numTicks > width)) {
                    sb.append('+');
                } else if ((i % majorTickIncrement) == 0) {
                    sb.append(majorTick);
                } else {
                    sb.append(minorTick);
                }
            }
            return sb.toString();
        }
    }

    protected int scaleToMeter() {
        int scaledLowerBound = (int) (lowerBound * width / (upperBound - lowerBound));
        return (int) ((value * width / (upperBound - lowerBound)) - scaledLowerBound);
    }

    public String toString() {
        StringBuffer result = new StringBuffer(width);
        if (showValue) {
//            result += String.format(valueFormat, value);
            if (value == 0.0) {
                result.append('0');
            } else {
                if ((value >= 1.0) || (value <= -1.0)) {
                    result.append((int) value);
                }
                result.append('.');
                value = (value - (int) value) * 10;
                result.append((int) value);
            }
            result.append((int) (value - ((int) value) * 10));
            result.append(": ");
        }
        result.append(buildMeterText(scaleToMeter()));
        return result.toString();
    }
}
