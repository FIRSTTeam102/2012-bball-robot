/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class TilterArm extends Subsystem {

    public static final double TIME_TO_MOVE_ARM_DOWN = 0.5;
    public static final double TIME_TO_MOVE_ARM_UP = 0.5;
    public static final double SPEED_TO_MOVE_ARM_DOWN = 0.75;
    public static final double SPEED_TO_MOVE_ARM_UP = 0.75;
    public static final double ARM_DOWN_DIRECTION = 1.0;
    Victor armMotor;

    public TilterArm()
    {
        armMotor = new Victor(RobotMap.tilterArmMotorPort);
    }
    public void initDefaultCommand() {
    }
    public void armUp(double speed) {
        armMotor.set(-ARM_DOWN_DIRECTION * speed);
    }
    public void armDown(double speed) {
        armMotor.set(ARM_DOWN_DIRECTION * speed);
    }
    public void armStop()
    {
        armMotor.set(0.0);
    }
    public void move(double speed)
    {
        armMotor.set(speed);
    }
    public void updateStatus() {
        SmartDashboard.putDouble("Tilter Arm Motor: ", armMotor.get());
    }
}