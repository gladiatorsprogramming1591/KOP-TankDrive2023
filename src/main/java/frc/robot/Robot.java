// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
  public class Robot extends TimedRobot {
    private final CANSparkMax m_frontLeftMotor = new CANSparkMax(0,MotorType.kBrushless);
    private final CANSparkMax m_rearLeftMotor = new CANSparkMax(1,MotorType.kBrushless);
    private final MotorControllerGroup leftGroup = new MotorControllerGroup(m_frontLeftMotor, m_rearLeftMotor);

    private final CANSparkMax m_frontRightMotor = new CANSparkMax(2,MotorType.kBrushless);
    private final CANSparkMax m_rearRightMotor = new CANSparkMax(3,MotorType.kBrushless);
    private final MotorControllerGroup rightGroup = new MotorControllerGroup(m_frontRightMotor, m_rearRightMotor);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftGroup, rightGroup);
  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_frontRightMotor.setInverted(true);
    m_rearRightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());
  }
}
