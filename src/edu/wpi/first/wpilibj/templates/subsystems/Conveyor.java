/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveConveyor;

/**
 *
 * @author Administrator
 */
public class Conveyor extends Subsystem {

    public static final double CONVEYOR_MOTOR_SCALE = -1.0;
    Victor conveyorMotor;
    double joyY;

    public Conveyor()
    {
        conveyorMotor = new Victor(RobotMap.conveyorMotorPort);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new DriveConveyor());
    }
    public void driveConveyor(Joystick xBox) {

        joyY = RobotMap.conveyorDeadBand.Deaden(xBox.getY());
        conveyorMotor.set(joyY * CONVEYOR_MOTOR_SCALE);
    }
    public void driveConveyor(double yValue) {

        conveyorMotor.set(yValue * CONVEYOR_MOTOR_SCALE);
    }
    public void updateStatus() {
        SmartDashboard.putDouble("ConveyorMotor: ", conveyorMotor.get());
        SmartDashboard.putDouble("ConveyorJoy: ", joyY);
    }
}