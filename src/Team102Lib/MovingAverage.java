/*
 * Class to implement a moving average calculator.
 */

package Team102Lib;

/**
 *
 * @author us1468
 */
public class MovingAverage {

    int numElements;
    double lastMovingAverage;
    public MovingAverage(double initMovingAverage)
    {
        init();
        lastMovingAverage = initMovingAverage;
    }
    public MovingAverage()
    {
        init();
    }

    public void init()
    {
        numElements = 0;
        lastMovingAverage = 0.0;
    }
    // NOTE: this will return the exponentially smoothed average.
    public double smoothValue(double val)
    {
        numElements++;
        double k = 2.0 / ((double) numElements + 1.0);
        lastMovingAverage = (val * k) + (lastMovingAverage * (1 - k));
        return lastMovingAverage;
    }
    public double currentAverage()
    {
        return lastMovingAverage;
    }
}
