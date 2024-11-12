package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.ShooterSubsystem;

public class StopCacheCommand extends Command{
     private IntakeSubsystem intake;
    //private Supplier<Double> speed;

    public StopCacheCommand(IntakeSubsystem intake){
        this.intake = intake;
        //this.speed = speed;
        
        addRequirements(intake);
    }
    
    @Override
    public void execute(){
        intake.holdCache();
    }

    @Override
    public void end(boolean interrupted) {
        //intake.holdCache();
    }

    @Override
    public boolean isFinished() {
    return false;
  }
}
