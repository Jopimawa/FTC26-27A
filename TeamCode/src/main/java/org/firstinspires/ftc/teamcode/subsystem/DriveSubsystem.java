package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class DriveSubsystem extends SubsystemBase {
    Motor m_frontLeft;
    public static boolean frontLeftRev = false;
    Motor m_frontRight;
    public static boolean frontRightRev = false;
    Motor m_backLeft;
    public static boolean backLeftRev = false;
    Motor m_backRight;
    public static boolean backRightRev = false;
    public static double driveKp = 0;
    public static double driveKi = 0;
    public static double driveKd = 0;
    public static double driveKs = 0;
    public static double driveKv = 0;
    MecanumDrive mecanum;

    public DriveSubsystem(HardwareMap hardwareMap) {

        m_frontLeft = new Motor(hardwareMap,"frontLeft Drive");
        setupMotor(m_frontLeft,frontLeftRev);

        m_frontRight = new Motor(hardwareMap,"frontRight Drive");
        setupMotor(m_frontRight,frontRightRev);

        m_backLeft = new Motor(hardwareMap,"backLeft Drive");
        setupMotor(m_backLeft,backLeftRev);

        m_backRight = new Motor(hardwareMap,"backRight Drive");
        setupMotor(m_backRight,backRightRev);
        MecanumDrive mecanum = new MecanumDrive(m_frontLeft,m_frontRight
                ,m_backLeft,m_backRight);

    }
    public void setupMotor(Motor motor, boolean rev) {
        motor.setRunMode(Motor.RunMode.VelocityControl);
        motor.setVeloCoefficients(driveKp, driveKi, driveKd);
        motor.setFeedforwardCoefficients(driveKs, driveKv);
        motor.setInverted(rev);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }
    public void drive(double x, double y, double rx) {
        driveAuto(x,y,rx);
    }
    public void driveAuto(double x, double y, double rx, boolean isField) {
        mecanum.driveRobotCentric(y,x,rx);
    }
    public void driveAuto(double x, double y, double rx) {
        driveAuto(x,y,rx,false);
    }
    public void driveManual(double x, double y, double rx) {
       double theta = Math.atan2(y,x);
       double power = Math.hypot(y,x);

       double sin = Math.sin(theta - Math.PI/4);
       double cos = Math.cos(theta - Math.PI/4);
       double max = Math.max(Math.abs(sin), Math.abs(cos));

       double frontLeft  = power * cos/max + rx;
       double frontRight = power * sin/max - rx;
       double rearLeft   = power * sin/max + rx;
       double rearRight  = power * cos/max - rx;

       if ((power + Math.abs(rx)) > 1) {
           frontLeft  /= power + Math.abs(rx);
           frontRight /= power + Math.abs(rx);
           rearLeft   /= power + Math.abs(rx);
           rearRight  /= power + Math.abs(rx);
       }

       //TODO: Motor power

    }
    public void stop() {
       mecanum.stop();
    }
}
