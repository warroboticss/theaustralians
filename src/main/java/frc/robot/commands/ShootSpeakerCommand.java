package frc.robot.commands;

//import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootSpeakerCommand extends Command {
    private ShooterSubsystem shooter;
    //private Supplier<Double> speed;

    public ShootSpeakerCommand(ShooterSubsystem shooter){
        this.shooter = shooter;
        //this.speed = speed;
        
        addRequirements(shooter);
    }
    
    @Override
    public void execute(){
        shooter.speakerScore();
    }

    @Override
    public void end(boolean interrupted) {
        //shooter.stopShooter();
    }

    @Override
    public boolean isFinished() {
    return false;
  }
}
