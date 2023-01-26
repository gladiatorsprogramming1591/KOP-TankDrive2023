// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  //Fast
  public final static boolean kFastSquaredInputs = true;
  public final static double kFastDriveScalar = 0.9;  // with squared inputs, sets max speed to 81%
  public final static double kFastTurnScalar = 0.7;
  
  //Slow
  public final static boolean kSlowSquaredInputs = true;
  public final static double kSlowDriveScalar = 0.5;  // with squared inputs, sets max speed to 25%
  public final static double kSlowTurnScalar = 0.6;
      
}
