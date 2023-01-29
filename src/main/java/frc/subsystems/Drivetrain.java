package frc.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.FastDrive;

public class Drivetrain extends SubsystemBase {
    private final CANSparkMax m_frontLeftMotor = new CANSparkMax(3,MotorType.kBrushless);
    private final CANSparkMax m_rearLeftMotor = new CANSparkMax(4,MotorType.kBrushless);
    private final CANSparkMax m_frontRightMotor = new CANSparkMax(2,MotorType.kBrushless);
    private final CANSparkMax m_rearRightMotor = new CANSparkMax(1,MotorType.kBrushless);
    private final AHRS navx = new AHRS(SPI.Port.kMXP);
    private final PIDController driveController = new PIDController(0.022, 0.005, 0.0025); // 1/21 ki:0.0055 kd: 0.0027 was worse
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);
    private int count = 0;
    private XboxController m_driverJoystick;

    public Drivetrain(XboxController stick){
        m_driverJoystick = stick;

        m_frontLeftMotor.restoreFactoryDefaults();
        m_rearLeftMotor.restoreFactoryDefaults();
        m_rearLeftMotor.follow(m_frontLeftMotor);
        m_frontRightMotor.restoreFactoryDefaults();
        m_rearRightMotor.restoreFactoryDefaults();
        m_rearRightMotor.follow(m_frontRightMotor);
        
        m_frontRightMotor.setInverted(true);

        setDefaultCommand(new FastDrive(this));
    
        // TODO - Add to SmartDashboard whether the navx is still calibrating, see Thunderclap code for example
        navx.calibrate();    
    }

    public void driveToLevel(){
        double pidOut = MathUtil.clamp(driveController.calculate(navx.getRoll(), 0), -0.35, 0.35);
        m_robotDrive.arcadeDrive(pidOut , 0);
        if (++count %10 == 0) {
            System.out.println("Roll is :" + navx.getRoll());
            System.out.println("PID Output is: " + pidOut);
        }
    }

    public void setCoastMode() {
        m_frontLeftMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        m_frontRightMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        m_rearLeftMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        m_rearRightMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    public void setBrakeMode() {
        m_frontLeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_frontRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_rearLeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_rearRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void setSlowDrive() {
        // TODO Fill in correctly
        // m_AxisForward = getAxisForward() * Constants.kSlowDriveScalar;
        // m_AxisTurning = getAxisTurning() * Constants.kSlowDriveScalar;
    }
      
    public void setFastDrive() {
        // TODO Fill in correctly
        // m_AxisForward = getAxisForward() * Constants.kFastDriveScalar;
        // m_AxisTurning = getAxisTurning() * Constants.kFastDriveScalar; 
    }
     
    public double getAxisForward() {
        return -m_driverJoystick.getLeftY();
    }
  
    public double getAxisTurning() {
        return m_driverJoystick.getRightX();
    }
  
    // TODO - make arcadeDrive the default command for this subsystem
    public void arcadeDrive(double y, double x, boolean isSquared){
        m_robotDrive.arcadeDrive(y, x,isSquared);
    }

    public boolean driveToAngle (double angle){
        boolean atAngle = true;
        double currentAngle = navx.getRoll();
        // Angle could be positive or negative, need to handle both (TODO)
        // Should be positive if driving forward, negative if driving backward onto the power station ramp
        if ( currentAngle >= angle) {
            m_robotDrive.arcadeDrive(.35, 0);
            atAngle = false;
            System.out.println("Angle is:" + currentAngle);
        }
        else {
            m_robotDrive.arcadeDrive(0, 0);  
        }
        return atAngle;
    }

    @Override   //?
    public void periodic(){
        SmartDashboard.putBoolean("NavX Callibrating", navx.isCalibrating());
  }
}
