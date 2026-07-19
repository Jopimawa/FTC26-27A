package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(6) // in kilos
            //.headingPIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0))
            //.forwardZeroPowerAcceleration(0)
            //.lateralZeroPowerAcceleration(0)
            //.translationalPIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0))
            //.drivePIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0))
            //.centripetalScaling(0.005)
            ;

    // TODO: TESTING Constants
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .leftFrontMotorName("frontRight Drive")
            .rightFrontMotorName("frontRight Drive")
            .leftRearMotorName("backLeft Drive")
            .rightRearMotorName("backRight Drive")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            //.xVelocity(0)
            //.yVelocity(0)
            ;

    public static TwoWheelConstants localizerConstants = new TwoWheelConstants()
            .forwardEncoder_HardwareMapName("Forward Odom")
            .strafeEncoder_HardwareMapName("Strafe Odom")
            .IMU_HardwareMapName("imu")
            .IMU_Orientation(
                    new RevHubOrientationOnRobot(
                            RevHubOrientationOnRobot.LogoFacingDirection.UP,
                            RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                    )
            )
            .forwardTicksToInches(1)
            .strafeTicksToInches(1);
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .twoWheelLocalizer(localizerConstants)
                .build();
    }
}
