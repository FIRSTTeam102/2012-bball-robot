/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class BallGate extends Subsystem {

    public static final double TIME_TO_RELEASE_GATE_UP = 0.0;
    public static final double TIME_TO_RELEASE_GATE_DOWN = 0.0;

    Solenoid gateSolenoidUp;
    Solenoid gateSolenoidDown;

    public BallGate()
    {
        gateSolenoidUp = new Solenoid(RobotMap.solenoidModule, RobotMap.gateSolenoidUpPort);
        gateSolenoidDown = new Solenoid(RobotMap.solenoidModule, RobotMap.gateSolenoidDownPort);

    }
    public void initDefaultCommand() {
        // No default command.  Gate is opened and closed on command only.
    }
    public void gateUp() {
        gateSolenoidDown.set(false);
        gateSolenoidUp.set(true);
    }
    public void gateDown() {
        gateSolenoidDown.set(true);
        gateSolenoidUp.set(false);
    }
}