package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
    private static CANSparkMax climberMotor1 = new CANSparkMax(0, CANSparkLowLevel.MotorType.kBrushless);
    private static CANSparkMax climberMotor2 = new CANSparkMax(0, CANSparkLowLevel.MotorType.kBrushless);
    private final double climberSpeed = 0.2;

    public ClimberSubsystem(){}

    // Inversion will probably need to be changed
    public void extendClimbers(){
        climberMotor1.set(climberSpeed);
        climberMotor2.set(climberSpeed);
    }
    public void retractClimbers(){
        climberMotor1.set(climberSpeed * -1);
        climberMotor2.set(climberSpeed * -1);
    }
    public void stopClimbers(){
        climberMotor1.set(0.0);
        climberMotor2.set(0.0);
    }
}
