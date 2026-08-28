package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class DriveSubsystem extends SubsystemBase {
    //Strips the Motor object
    DcMotorEx m_frontLeft;
    public static boolean frontLeftRev = true;
    DcMotorEx m_frontRight;
    public static boolean frontRightRev = false;
    DcMotorEx m_backLeft;
    public static boolean backLeftRev = true;
    DcMotorEx m_backRight;
    public static boolean backRightRev = false;
    MecanumDrive mecanum;

    public DriveSubsystem(HardwareMap hardwareMap) {
        m_frontLeft = (DcMotorEx) hardwareMap.get(DcMotor.class, "frontRight");
        setupMotor(m_frontLeft,frontLeftRev);

        m_frontRight = (DcMotorEx) hardwareMap.get(DcMotor.class, "backLeft");
        setupMotor(m_frontRight,frontRightRev);

        m_backLeft = (DcMotorEx) hardwareMap.get(DcMotor.class, "frontLeft");
        setupMotor(m_backLeft,backLeftRev);

        m_backRight = (DcMotorEx) hardwareMap.get(DcMotor.class, "backRight");
        setupMotor(m_backRight,backRightRev);

    }
    public void setupMotor(DcMotorEx motor, boolean rev) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotorSimple.Direction invert = (rev) ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE;
        motor.setDirection(invert);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void drive(double x, double y, double rx) {
        driveLegacy(x,y,rx);
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

        m_frontLeft.setPower(frontLeft);
        m_frontRight.setPower(frontRight);
        m_backLeft.setPower(backLeft);
        m_backRight.setPower(backRight);
    }

    public void driveLegacy(double x, double y, double rx) {
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

        m_frontLeft.setPower(fl);
        m_frontRight.setPower(fr);
        m_backLeft.setPower(bl);
        m_backRight.setPower(br);
    }
    public void stop() {
       mecanum.stop();
    }
}
