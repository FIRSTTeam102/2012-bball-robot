/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveConveyor;

/**
 *
 * @author Administrator
 */
public class Conveyor extends Subsystem {

    static final double conveyorMotorScale = 1.0;
    Victor conveyorMotor;

    public Conveyor()
    {
        conveyorMotor = new Victor(RobotMap.conveyorMotorPort);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new DriveConveyor());
    }
    public void driveConveyor(Joystick rightXBoxStick) {

        conveyorMotor.set(rightXBoxStick.getY() * conveyorMotorScale);
    }
}