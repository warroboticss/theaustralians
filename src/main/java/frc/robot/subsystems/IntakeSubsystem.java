package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private static CANSparkMax intakeMotor = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
    private static CANSparkMax cacheMotor = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless);
    private static RelativeEncoder encoder = cacheMotor.getEncoder();
    private final double intakeSpeed = 0.3;
    private final double cacheSpeed = 0.2;

    public IntakeSubsystem(){}

    public void runIntakeMotor(){
        intakeMotor.set(intakeSpeed);
    }
    public void stopIntakeMotor(){
        intakeMotor.set(0);
    }
    public void holdCache(){
        cacheMotor.set(0);
    }
    public void clearCache(){
        cacheMotor.set(cacheSpeed);
    }
    public void stop(){
        cacheMotor.set(0);
        intakeMotor.set(0);
    }
    // public double encoderVal(){
    //     return encoder.getPosition();
    // }
    // public boolean inCache(double prevVal){
    //     if(prevVal != encoder.getPosition()){
    //         return true;
    //     }
    //     return false;
    // }
    public void out(){
        intakeMotor.set(-0.5);
        cacheMotor.set(-0.5);
    }
    public Command cache() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
             clearCache();
            });
      }
    public Command stopCache() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              holdCache();
            });
      }
    public Command intake() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              runIntakeMotor();
            });
      }
    public Command stopIntake() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              stopIntakeMotor();
            });
      }
}
