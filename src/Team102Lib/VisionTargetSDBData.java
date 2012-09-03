/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Team102Lib;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboardData;

/**
 *
 * @author Administrator
 */
public class VisionTargetSDBData implements SmartDashboardData
{
    protected NetworkTable table;

    public double x;
    public double y;
    public double distance;
    public double width;
    public double height;
    public double aspectRatio;
    public double timeRecorded;

    public VisionTargetSDBData()
    {
        
    }
    public void showBackboardParticle(BackboardParticle bbp)
    {
        x = bbp.x;
        y = bbp.y;
        distance = bbp.distance;
        width = bbp.width;
        height = bbp.height;
        aspectRatio = bbp.aspectRatio;
        timeRecorded = Timer.getFPGATimestamp();
    }
    public String getType() {
        return "BackboardTarget";
    }

    public NetworkTable getTable() {

        // NOTE: May want to check if the data have already been sent based on time stamp.

        if (table == null) {
            table = new NetworkTable();

            // Things we want to send to the smartdash board.
            table.putDouble("Width", width);
            table.putDouble("Height", height);
            table.putDouble("Distance", distance);
            table.putDouble("Aspect Ratio", aspectRatio);
            table.putDouble("Position X", x);
            table.putDouble("Position Y", y);
            table.putDouble("Time Recorded", timeRecorded);

/*            table.addListenerToAll(new NetworkListener() {
                public void valueChanged(String key, Object value) {
                }
                public void valueConfirmed(String key, Object value) {
                }
            });
*/        }
        return table;
    }

}
