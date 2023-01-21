package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class DriveToAngleCommand extends CommandBase {
    private Drivetrain m_drivetrain;

    public DriveToAngleCommand(Drivetrain drivetrain){
        m_drivetrain = drivetrain;
        addRequirements(m_drivetrain);
    }

    @Override
    public void execute(){
        m_drivetrain.driveToAngle(12);
    }
}
