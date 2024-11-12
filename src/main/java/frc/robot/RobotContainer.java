package frc.robot;

import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.LimelightCenter;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.RunCacheCommand;
import frc.robot.commands.RunIntakeCommand;
import frc.robot.commands.ShootSpeakerCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.StopCacheCommand;
import frc.robot.commands.StopShooterCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  //private final Joystick controller = new Joystick(0);
  private final CommandXboxController controller = new CommandXboxController(0);
  private final Joystick revControl = new Joystick(1);
  private final
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-a");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  public RobotContainer() {

    NamedCommands.registerCommand("shoot", shooterSubsystem.shoot());
    NamedCommands.registerCommand("cache", intakeSubsystem.cache());
    NamedCommands.registerCommand("intake", intakeSubsystem.intake());
    NamedCommands.registerCommand("stop cache", intakeSubsystem.stopCache());
    NamedCommands.registerCommand("stop shooter", shooterSubsystem.stopShoot());

    configureBindings();
    driveSubsystem.setDefaultCommand(new DefaultDriveCommand(driveSubsystem, () -> controller.getLeftX() , () -> controller.getLeftY(), () -> controller.getRightX()));
    shooterSubsystem.setDefaultCommand(new ShooterCommand(shooterSubsystem, () -> controller.leftBumper().getAsBoolean(), () -> controller.a().getAsBoolean()));
    intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem, () -> controller.getLeftTriggerAxis(), () -> controller.getRightTriggerAxis()));    
  }

 
  private void configureBindings() {
    controller.y().onTrue((new InstantCommand(driveSubsystem::zeroGyro)));
    controller.rightBumper().whileTrue((new LimelightCenter(driveSubsystem, shooterSubsystem, () -> controller.getLeftX(), () -> controller.getLeftY(), ()-> tx.getDouble(0.0), ()-> ta.getDouble(0.0))));
    controller.x().whileTrue(new OuttakeCommand(intakeSubsystem, shooterSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    return driveSubsystem.getAutonomousCommand("Just Shoot");
    //return new ShootSpeakerCommand(shooterSubsystem);
  }
}
