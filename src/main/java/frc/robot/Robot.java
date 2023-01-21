// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
// import frc.robot.commands.DriveToAngleCommand;
import frc.subsystems.Drivetrain;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
  public class Robot extends TimedRobot {    
    
  private final XboxController m_stick = new XboxController(0);

  private Drivetrain m_drivetrain;

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_drivetrain = new Drivetrain();
  }

  @Override
  public void teleopPeriodic() {
    // Arcade drive: Y axis drives forward and backward, and the X turns left and right.
    if(m_stick.getLeftBumper()){
      m_drivetrain.driveToLevel();
    }
    else if(m_stick.getYButton()){
      // DriveToAngleCommand command = new DriveToAngleCommand(m_drivetrain);
      m_drivetrain.driveToAngle(-12);
    } else {
      m_drivetrain.arcadeDrive(-m_stick.getLeftY(), -m_stick.getRightX(),true);
    }

  }
}
