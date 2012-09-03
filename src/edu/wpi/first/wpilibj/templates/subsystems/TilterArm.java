/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class TilterArm extends Subsystem {

    public static final double TIME_TO_MOVE_ARM_DOWN = 1.0;
    public static final double SPEED_TO_MOVE_ARM_DOWN = 1.0;
    public static final double TIME_TO_MOVE_ARM_UP = 1.0;
    public static final double SPEED_TO_MOVE_ARM_UP = 1.0;
    static final double armDownDirection = -1.0;
    Victor armMotor;

    public TilterArm()
    {
        armMotor = new Victor(RobotMap.tilterArmMotorPort);
    }
    public void initDefaultCommand() {
    }
    public void armUp(double speed) {
        armMotor.set(-armDownDirection * speed);
    }
    public void armDown(double speed) {
        armMotor.set(armDownDirection * speed);
    }
    public void armStop()
    {
        armMotor.set(0.0);
    }
}