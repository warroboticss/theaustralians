package frc.robot.subsystems;
import java.io.File;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveControllerConfiguration;
import swervelib.parser.SwerveDriveConfiguration;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class DriveSubsystem extends SubsystemBase{
    private SwerveDrive swerveDrive;
    private static Pigeon2 gyro;
    //  SET NEW MAX SPEED
    public double maximumSpeed = Units.feetToMeters(4.5);

    

    public DriveSubsystem() {
        File directory = new File(Filesystem.getDeployDirectory(),"swerve");
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.NONE;
        try
        {
        SwerveDrive swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
        this.swerveDrive = swerveDrive;

        } catch (Exception e)
        {
        throw new RuntimeException(e);
        }

    
        gyro = new Pigeon2(5);
        setupPathPlanner();
    };


    public DriveSubsystem(SwerveDriveConfiguration driveCfg, SwerveControllerConfiguration controllerCfg)
    {
        swerveDrive = new SwerveDrive(driveCfg, controllerCfg, maximumSpeed);
    }

    public void driveCommand(Double xInput, Double yInput)
      {
        swerveDrive.driveFieldOriented(swerveDrive.swerveController.getTargetSpeeds(xInput, yInput,0,0,swerveDrive.getYaw().getRadians(),swerveDrive.getMaximumVelocity()));
      }


    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        swerveDrive.drive(translation.rotateBy(Rotation2d.fromDegrees(-90)), rotation, fieldRelative, isOpenLoop);
    }
    public ChassisSpeeds getTargetSpeeds(double x, double y, Rotation2d rotation) {
        return swerveDrive.swerveController.getTargetSpeeds(x, y, rotation.getRadians(),
                (getYaw()).getRadians() , maximumSpeed);

    }
    public Rotation2d getYaw() {
        return swerveDrive.getYaw();
    }


  public void driveFieldOriented(ChassisSpeeds velocity)
  {
    swerveDrive.driveFieldOriented(velocity);
  }
  public void zeroGyro()
  {
    swerveDrive.zeroGyro();
  }
  public void setupPathPlanner()
  {
    AutoBuilder.configureHolonomic(
        this::getPose, // Robot pose supplier
        this::resetOdometry, // Method to reset odometry (will be called if your auto has a starting pose)
        this::getRobotVelocity, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
        this::setChassisSpeeds, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
        new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
                                        new PIDConstants(0.01, 0, 0),
                                         // Translation PID constants
                                         new PIDConstants(0.01, 0, 0),
                                         // Rotation PID constants
                                         1,
                                         // Max module speed, in m/s
                                         swerveDrive.swerveDriveConfiguration.getDriveBaseRadiusMeters(),
                                         // Drive base radius in meters. Distance from robot center to furthest module.
                                         new ReplanningConfig()
                                         // Default path replanning config. See the API for the options here
        ),
        () -> {
          // Boolean supplier that controls when the path will be mirrored for the red alliance
          // This will flip the path being followed to the red side of the field.
          // THE ORIGIN WILL REMAIN ON THE BLUE SIDE
          var alliance = DriverStation.getAlliance();
          return alliance.isPresent() ? alliance.get() == DriverStation.Alliance.Red : false;
        },
        this // Reference to this subsystem to set requirements
                                  );
  }
  public SwerveDriveKinematics getKinematics()
  {
    return swerveDrive.kinematics;
  }
  public void resetOdometry(Pose2d initialHolonomicPose)
  {
    swerveDrive.resetOdometry(initialHolonomicPose);
  }
  public Pose2d getPose()
  {
    return swerveDrive.getPose();
  }
  public void setChassisSpeeds(ChassisSpeeds chassisSpeeds)
  {
    swerveDrive.setChassisSpeeds(chassisSpeeds);
  }
  public ChassisSpeeds getRobotVelocity()
  {
    return swerveDrive.getRobotVelocity();
  }
    public Command getAutonomousCommand(String pathName)
  {
    return new PathPlannerAuto(pathName);
  }
}
