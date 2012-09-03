/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Team102Lib;

/**
 *
 * @author Administrator
 */
// Class to implement a deadband.
// Assumes a centered deadband around 0.0
// rangeRadius - the radius of the area of expected inputs.  Values outside this range will be truncated to the range.
// deadRadius - the radius of the 0.0 part of the deadband
// expFactor - the exponent that will determine the shape of the rest of the curve.
// rescalePercent - rescale the return value AFTER the deadband is applied.
public class Deadband {

    double rangeRadius;
    double deadRadius;
    double expFactor;
    double rescalePercent;

    public Deadband() {
        rangeRadius = 1.0;
        deadRadius = 0.02;
        expFactor = 2.0;
        rescalePercent = 1.0;
    }

    public Deadband(double rr, double dr, double ef, double rp) {
        this.rangeRadius = rr;
        this.deadRadius = dr;
        this.expFactor = ef;
        this.rescalePercent = rp;
    }

    public void SetExpFactor(double ef) {
        this.expFactor = ef;
    }

    public void SetRescalePercent(double rp) {
        this.rescalePercent = rp;
    }

    public double Deaden(double theValue) {
        // Truncate the input
        if (theValue > rangeRadius) {
            theValue = rangeRadius;
        } else if (theValue < -rangeRadius) {
            theValue = -rangeRadius;
        }

        if ((theValue >= -deadRadius) && (theValue <= deadRadius)) {
            return 0;
        } else {
            // NOTE: this will amplify, not deaden if abs(theValue) > 1
            double retVal = 0.0;
            if (theValue > 0.0) {
                retVal = MathLib.pow(theValue, expFactor);
            } else {
                retVal = -MathLib.pow(Math.abs(theValue), expFactor);
            }

            return retVal * rescalePercent;
        }
    }

    public String toString() {
        return "rangeRadius, deadRadius, expFactor, rescalePercent = "
                + rangeRadius + ", " + deadRadius + ", " + expFactor + ", " + rescalePercent;
    }
};
