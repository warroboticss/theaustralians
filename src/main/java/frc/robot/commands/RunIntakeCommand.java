package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.ShooterSubsystem;

public class RunIntakeCommand extends Command{
     private IntakeSubsystem intake;
    //private Supplier<Double> speed;

    public RunIntakeCommand(IntakeSubsystem intake){
        this.intake = intake;
        //this.speed = speed;
        
        addRequirements(intake);
    }
    
    @Override
    public void execute(){
        intake.runIntakeMotor();
    }

    @Override
    public void end(boolean interrupted) {
        //intake.stopIntakeMotor();
    }

    @Override
    public boolean isFinished() {
    return false;
  }
}
