package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {
    private ShooterSubsystem shooter;
    private Supplier<Boolean> speaker;
    private Supplier<Boolean> amp;
    // private boolean isRevS = false;
    // private boolean isRevA = false;

    public ShooterCommand(ShooterSubsystem shooter, Supplier<Boolean> speaker, Supplier<Boolean> amp){
        this.shooter = shooter;
        this.speaker = speaker;
        this.amp = amp;

        addRequirements(shooter);
    }
    
    @Override
    public void execute(){
        if(speaker.get()){
            // if(isRevS){
            //     shooter.stopShooter();
            //     isRevS = false;
            //     //isRevA = false;
            // }
            // else{
            //     shooter.speakerScore();
            //     isRevS = true;
            //     isRevA = false;
            // }
            shooter.speakerScore();;
            
        }
        else if(amp.get()){
            // if(isRevA){
            //     shooter.stopShooter();
            //     isRevA = false;
            //     //isRevS = false;
            // }
            // else{
            //     shooter.ampScore();
            //     isRevA = true;
            //     isRevS = false;
            // }
            shooter.ampScore();
        }
        else{
            shooter.stopShooter();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter();
    }

    @Override
    public boolean isFinished() {
    return false;
  }
}
