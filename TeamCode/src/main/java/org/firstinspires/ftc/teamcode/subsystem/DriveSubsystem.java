package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class DriveSubsystem extends SubsystemBase {
    Motor m_frontLeft;
    public static boolean frontLeftRev = true;
    Motor m_frontRight;
    public static boolean frontRightRev = false;
    Motor m_backLeft;
    public static boolean backLeftRev = true;
    Motor m_backRight;
    public static boolean backRightRev = false;
    public static double driveKp = 0;
    public static double driveKi = 0;
    public static double driveKd = 0;
    public static double driveKs = 0;
    public static double driveKv = 0;
    MecanumDrive mecanum;

    public DriveSubsystem(HardwareMap hardwareMap) {

        //m_frontLeft = new Motor(hardwareMap,"frontLeft");
        setupMotor(m_frontLeft,frontLeftRev);

        //m_frontRight = new Motor(hardwareMap,"frontRight");
        setupMotor(m_frontRight,frontRightRev);

        //m_backLeft = new Motor(hardwareMap,"backLeft");
        setupMotor(m_backLeft,backLeftRev);

        //m_backRight = new Motor(hardwareMap,"backRight");
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
        driveManual(x,y,rx);
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
       double backLeft   = power * sin/max + rx;
       double backRight  = power * cos/max - rx;

       if ((power + Math.abs(rx)) > 1) {
           frontLeft  /= power + Math.abs(rx);
           frontRight /= power + Math.abs(rx);
           backLeft   /= power + Math.abs(rx);
           backRight  /= power + Math.abs(rx);
       }


        m_frontLeft.set(frontLeft);
        m_frontRight.set(frontRight);
        m_backLeft.set(backLeft);
        m_backRight.set(backRight);
    }

    public void driveLegacy(double y, double x, double rx) {
        // Drive used in 2025-2026 season, clobbered by aaron jimenez but wasnt replaced the whole season
        x =-x;
        y =-y;

        double fl = y + x - rx;
        double fr = y - x + rx;
        double bl = y - x - rx;
        double br = y + x + rx;


        // Normalize so no value exceeds 1
        double max = Math.max(1.0, Math.max(Math.abs(fl),
                Math.max(Math.abs(bl), Math.max(Math.abs(fr), Math.abs(br)))));

        fl /= max;
        bl /= max;
        fr /= max;
        br /= max;

        m_frontLeft.set(fl);
        m_backLeft.set(bl);
        m_frontRight.set(fr);
        m_backRight.set(br);
    }
    public void stop() {
       mecanum.stop();
    }
}
