package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private static CANSparkMax shooterMotor1 = new CANSparkMax(9, CANSparkLowLevel.MotorType.kBrushless);
    private static CANSparkMax shooterMotor2 = new CANSparkMax(12, CANSparkLowLevel.MotorType.kBrushless);
    private static double ampSpeed = 0.247;
    private static double speakerSpeed = 0.9;

    public ShooterSubsystem(){}

    public void ampScore(){
        shooterMotor1.set(ampSpeed);
        shooterMotor2.set(0.312);
    }
    public static void speakerScore(){
        shooterMotor1.set(speakerSpeed);
        shooterMotor2.set(speakerSpeed);
    }
    public void stopShooter(){
        shooterMotor1.set(0);
        shooterMotor2.set(0);
    }
    public void out(){
        shooterMotor1.set(-0.2);
        shooterMotor2.set(-0.2);
    }
    // public Command shoot(){
    //     return 
    // }
    public Command shoot() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              speakerScore();
            });
      }
    public Command stopShoot() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              stopShooter();
            });
      }
}
