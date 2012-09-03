
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.CompressorOff;
import edu.wpi.first.wpilibj.templates.commands.CompressorOn;
import edu.wpi.first.wpilibj.templates.commands.FindTarget;
import edu.wpi.first.wpilibj.templates.commands.GateDownThenUp;
import edu.wpi.first.wpilibj.templates.commands.LightsOff;
import edu.wpi.first.wpilibj.templates.commands.LightsOn;
import edu.wpi.first.wpilibj.templates.commands.RemoveSlackAndArm;
import edu.wpi.first.wpilibj.templates.commands.ShiftHigh;
import edu.wpi.first.wpilibj.templates.commands.Shift;
import edu.wpi.first.wpilibj.templates.commands.Shoot;
import edu.wpi.first.wpilibj.templates.commands.TilterArmDrive;
import edu.wpi.first.wpilibj.templates.commands.TurnToAngle;
import edu.wpi.first.wpilibj.templates.subsystems.TilterArm;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public static final int JOYSTICK_PORT = 1;
    public static final int JOYSTICK_PORT2 = 2;
    public static final int JOYSTICK_PORT3 = 3;
    private Joystick leftstick;
    private Joystick rightstick;
    private Joystick xBox;

    private JoystickButton trigger;
    private JoystickButton leftStickButton8;
    private JoystickButton leftStickButton3;
    private JoystickButton leftStickButton4;

    private JoystickButton rightStickButton2;
    private JoystickButton rightStickButton3;
    private JoystickButton rightStickButton4;

    private JoystickButton xBoxA;
    private JoystickButton xBoxB;
    private JoystickButton xBoxX;
    private JoystickButton xBoxY;
    private JoystickButton xBoxRightBumper;

    public OI() {
        leftstick = new Joystick(JOYSTICK_PORT);
        rightstick = new Joystick(JOYSTICK_PORT2);
        xBox = new Joystick(JOYSTICK_PORT3);

//        trigger = new JoystickButton(leftstick, Joystick.ButtonType.kTop.value);
//        trigger.whenPressed(new ArmAndShoot());

//        leftStickButton8 = new JoystickButton(leftstick, 8);
//        leftStickButton8.whenPressed(new CompressorOn());
//        leftStickButton8.whenReleased(new CompressorOff());

        rightStickButton2 = new JoystickButton(rightstick, 2);
        rightStickButton3 = new JoystickButton(rightstick, 3);
        rightStickButton4 = new JoystickButton(rightstick, 4);

        xBoxA = new JoystickButton(xBox, 1);
        xBoxB = new JoystickButton(xBox, 2);
        xBoxX = new JoystickButton(xBox, 3);
        xBoxY = new JoystickButton(xBox, 4);
        xBoxRightBumper = new JoystickButton(xBox, 6);

        xBoxY.whenPressed(new RemoveSlackAndArm());
        xBoxB.whenPressed(new GateDownThenUp());
        xBoxA.whenPressed(new Shoot());
        xBoxX.whenPressed(new FindTarget());
        xBoxRightBumper.whenPressed(new LightsOn());
        xBoxRightBumper.whenReleased(new LightsOff());

        rightStickButton2.whileHeld(new Shift());
//        rightStickButton2.whenReleased(new ShiftHigh());
        leftStickButton3.whileHeld(new TilterArmDrive(TilterArm.ARM_DOWN_DIRECTION * TilterArm.SPEED_TO_MOVE_ARM_DOWN));
//        leftStickButton4.whenReleased(new TilterArmUp());
        leftStickButton4.whileHeld(new TilterArmDrive(-TilterArm.ARM_DOWN_DIRECTION * TilterArm.SPEED_TO_MOVE_ARM_UP));
    }

    public Joystick getLeftJoystick() {
        return leftstick;
    }

    public Joystick getRightJoystick() {
        return rightstick;
    }
    public Joystick getXBox()
    {
        return xBox;
    }
}

