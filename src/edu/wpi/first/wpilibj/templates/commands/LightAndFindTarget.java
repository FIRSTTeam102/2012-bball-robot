/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Administrator
 */
public class LightAndFindTarget extends CommandGroup {

    public LightAndFindTarget() {
        addSequential(new LightsOn());
        addSequential(new FindTarget());
        addSequential(new LightsOff());
    }
}