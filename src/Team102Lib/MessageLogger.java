/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Team102Lib;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Administrator
 */
public class MessageLogger {

    public static boolean disableLog = false;
    public static boolean disableErrLog = false;
    public static void LogError(String errMsg)
    {
        if(disableErrLog)
            return;
        LogDirect(errMsg);
    }
    public static void LogMessage(String msg)
    {
        if(disableLog)
            return;
        LogDirect(msg);
    }
    public static void LogDirect(String msg)
    {
        System.out.print(Timer.getFPGATimestamp() + "\t");
        System.out.println(msg);
    }
}
