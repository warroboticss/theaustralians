package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class OuttakeCommand extends Command {
    private IntakeSubsystem intake;
    private ShooterSubsystem shooter;

    public OuttakeCommand(IntakeSubsystem intake, ShooterSubsystem shooter){
        this.intake = intake;
        this.shooter = shooter;

        addRequirements(intake);
        addRequirements(shooter);
    }

    @Override
    public void execute(){
        intake.out();
        shooter.out();
    }
    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter();
        intake.stop();
    }

    @Override
    public boolean isFinished() {
    return false;
    }
}
