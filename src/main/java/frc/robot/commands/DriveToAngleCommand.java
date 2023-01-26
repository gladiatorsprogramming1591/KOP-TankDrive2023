package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.Drivetrain;

public class DriveToAngleCommand extends CommandBase {
    private Drivetrain m_drivetrain;
    private boolean atAngle = false;

    public DriveToAngleCommand(Drivetrain drivetrain){
        m_drivetrain = drivetrain;
        addRequirements(m_drivetrain);
    }

    public void initialize(){
        atAngle = false;
    }

    @Override
    public void execute(){
        // TODO - read angle from the SmartDashboard
        atAngle = m_drivetrain.driveToAngle(12);
    }

    @Override
    public boolean isFinished(){
        return atAngle;
    }
}
