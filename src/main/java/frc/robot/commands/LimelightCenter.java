package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import swervelib.SwerveController;

public class LimelightCenter extends Command{
    private DriveSubsystem m_swerve;
    private ShooterSubsystem shooter;
    private Supplier<Double> x, y, tx, ta;

    public LimelightCenter(DriveSubsystem swerve, ShooterSubsystem shooter, Supplier<Double> x, Supplier<Double> y, Supplier<Double> tx, Supplier<Double> ta){
        this.m_swerve = swerve;
        this.shooter = shooter;
        this.x = x;
        this.y = y;
        this.tx = tx;
        this.ta = ta;

        addRequirements(swerve);
    }

    @Override
    public void execute(){
        double current_x = x.get();
        double current_y = y.get();
        double current_tx = tx.get();
        double current_ta = ta.get();
        if (current_ta > 0.0 && current_ta < 5.8){
            current_y = ((6 - current_ta) * 0.08);
        }
        //System.out.println(current_tx);
        ChassisSpeeds targetSpeeds = m_swerve.getTargetSpeeds(current_x, current_y * -1, new Rotation2d(-0.001 * current_tx));
        m_swerve.drive(SwerveController.getTranslation2d(targetSpeeds), current_tx * -0.025, false, false); 
        //shooter.speakerScore();                                                                                                                                                                                                                                     
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
    return false;
  }
}
