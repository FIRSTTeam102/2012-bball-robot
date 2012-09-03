/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class BallGate extends Subsystem {

    public static final double TIME_TO_RELEASE_GATE_UP = 0.0;
    public static final double TIME_TO_RELEASE_GATE_DOWN = 0.0;

    Solenoid gateSolenoidDown;
    Solenoid gateSolenoidUp;

    public BallGate()
    {
        gateSolenoidDown = new Solenoid(RobotMap.solenoidModule, RobotMap.gateSolenoidUpPort);
        gateSolenoidUp = new Solenoid(RobotMap.solenoidModule, RobotMap.gateSolenoidDownPort);

        gateUp();
    }
    public void initDefaultCommand() {
        // No default command.  Gate is opened and closed on command only.
    }
    public void gateUp() {
        gateSolenoidUp.set(true);
        gateSolenoidDown.set(false);
    }
    public void gateDown() {
        gateSolenoidUp.set(false);
        gateSolenoidDown.set(true);
    }
    public void updateStatus() {
        SmartDashboard.putBoolean("gateSolenoidDown: ", gateSolenoidUp.get());
        SmartDashboard.putBoolean("gateSolenoidUp: ", gateSolenoidDown.get());
    }
}