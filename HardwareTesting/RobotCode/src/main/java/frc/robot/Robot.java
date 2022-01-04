// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimesliceRobot {
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
