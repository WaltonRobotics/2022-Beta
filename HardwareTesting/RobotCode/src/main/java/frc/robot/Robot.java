// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.teleop.DriveCommand;
import frc.robot.subsystems.Drivetrain;

import java.util.List;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimesliceRobot {

  public static Drivetrain drivetrain = new Drivetrain();;

  private final Joystick m_stick = new Joystick(2);

  // Solenoid corresponds to a single solenoid.
  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

  private static final int kSolenoidButton = 1;

  public Robot() {
    super(0.002, 0.005);
  }

  public void toggleSolenoid(){
    m_solenoid.toggle();
  }

  @Override
  public void robotInit() {
    CommandScheduler.getInstance().setDefaultCommand(drivetrain, new DriveCommand());
    Trajectory trajectory =
            TrajectoryGenerator.generateTrajectory(
                    new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
                    List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                    new Pose2d(3, 0, Rotation2d.fromDegrees(0)),
                    new TrajectoryConfig(Units.feetToMeters(3.0), Units.feetToMeters(3.0)));
    drivetrain.getField();
    SmartDashboard.putData(drivetrain.getField());
    drivetrain.getField().getObject("traj").setTrajectory(trajectory);
    drivetrain.getField().getRobotPose().getTranslation();
    drivetrain.getField().getRobotPose().getRotation();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    /*
     * The output of GetRawButton is true/false depending on whether
     * the button is pressed; Set takes a boolean for whether
     * to use the default (false) channel or the other (true).
     */
    m_solenoid.set(m_stick.getRawButton(kSolenoidButton));

    /*
     * In order to set the double solenoid, if just one button
     * is pressed, set the solenoid to correspond to that button.
     * If both are pressed, set the solenoid will be set to Forwards.
     */
  }
}
