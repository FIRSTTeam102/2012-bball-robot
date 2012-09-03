/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import Team102Lib.*;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.GateUp;
import edu.wpi.first.wpilibj.templates.commands.ShootTwoSimple;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command autonomousCommand;
    Command upGate;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() { // called on robot start (powered on)
        try
        {
            CommandBase.init(); // initialize commands and the OI (created by Netbeans)
            SmartDashboard.putData("Scheduler", Scheduler.getInstance());
            autonomousCommand = new ShootTwoSimple();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In RobotInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    private void setUpJoysticks()
    {
        DriverStation ds = DriverStation.getInstance();

        // ATTENTION: getAnalogIn does not work in robotInit()!!  (except in debug mode :()
        double stickDeadening = ds.getAnalogIn(1) + 1;
        double conveyorDeadening = ds.getAnalogIn(2) + 1;
        double speedScale = ds.getAnalogIn(3) / 5.0;
        RobotMap.stickDeadBand = new Deadband(RobotMap.joystickRange, RobotMap.flatDeadband, stickDeadening, speedScale);
        RobotMap.conveyorDeadBand = new Deadband(RobotMap.joystickRange, RobotMap.flatDeadband, conveyorDeadening, speedScale);

    }
    public void autonomousInit() {

        try
        {
            // schedule the autonomous command (example)
            CommandBase.driveTrain.gyro.reset();

            setUpJoysticks();
            
            autonomousCommand.start();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In autonomousInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        try
        {
            Scheduler.getInstance().run();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In autonomousPeriodic.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

  public void startCompetiton() {
      
  }
    public void teleopInit() {
        try
        {
            if(autonomousCommand != null)
                autonomousCommand.cancel();     // Cancel the autonomous command.

            setUpJoysticks();

            upGate = new GateUp();
            upGate.start();

            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In teleopInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        try
        {
            Scheduler.getInstance().run();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In teleopPeriodic.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
    public void updateStatus()
    {
       
//        CommandBase.ballGate.updateStatus();
        CommandBase.cannon.updateStatus();
//        CommandBase.conveyor.updateStatus();
        CommandBase.driveTrain.updateStatus();
//        CommandBase.pnuematics.updateStatus();
//        CommandBase.shifters.updateStatus();
//        CommandBase.tilterArm.updateStatus();
        CommandBase.vision.updateStatus();

        DriverStationLCD.getInstance().updateLCD();
         
    }
    public void teleopDisable()
    {
        try
        {
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In teleopDisable.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
    public void disabledInit()
    {
        try
        {
            Scheduler.getInstance().run();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
}
