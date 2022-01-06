package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.OI.controller;
import static frc.robot.Robot.drivetrain;

public class DriveCommand extends CommandBase {

    public DriveCommand() {
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.setDutyCycles(controller.getLeftY(), -controller.getRightY());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
