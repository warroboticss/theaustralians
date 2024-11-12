package frc.robot.commands;

import java.util.function.Supplier;

//import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    private IntakeSubsystem intake;
    private Supplier<Double> intaking;
    private Supplier<Double> shooting;
    private boolean current_intaking;
    private double current_shooting;

    public IntakeCommand(IntakeSubsystem intake, Supplier<Double> intaking, Supplier<Double> shooting){
        this.intake = intake;
        this.intaking = intaking;
        this.shooting = shooting;

        addRequirements(intake);
    }
    
    @Override
    public void execute(){
        current_intaking = intaking.get() > 0;
        current_shooting = shooting.get();
        if(current_intaking){
            // intake.holdCache();
            intake.runIntakeMotor();
            // if(intake.inCache(prevVal)){
            //     table.getEntry("ledMode").setNumber(2);
            // }
        }
        if(current_shooting > 0){
            intake.clearCache();
        }
        if(!(current_intaking || current_shooting > 0)){
            //table.getEntry("ledMode").setNumber(1);
            intake.stop();
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        intake.stopIntakeMotor();
    }

    @Override
    public boolean isFinished() {
    return false;
  }
}
