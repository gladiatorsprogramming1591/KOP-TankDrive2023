/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class FastDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_driveTrain;
  private double m_AxisForward;
  private double m_AxisTurning;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public FastDrive(Drivetrain driveTrain) {
    m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Fast Drive", true);
    m_driveTrain.setCoastMode();
    m_driveTrain.setFastDrive();
  }

  @Override
  public void execute() {
    m_AxisForward = m_driveTrain.getAxisForward() * Constants.kFastDriveScalar;
    m_AxisTurning = m_driveTrain.getAxisTurning() * Constants.kFastTurnScalar;
    m_driveTrain.arcadeDrive(m_AxisForward, m_AxisTurning, Constants.kFastSquaredInputs);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}