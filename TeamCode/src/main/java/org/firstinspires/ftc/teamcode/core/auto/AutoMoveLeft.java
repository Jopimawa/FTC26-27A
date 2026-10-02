package org.firstinspires.ftc.teamcode.core.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.command.DriveMove;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

@Autonomous(name="MoveLeft", group="Basic")
public class AutoMoveLeft extends CommandOpMode {

    // IF YOU ARE TESTING, CHANGE THESE VARIABLES, DO NOT TOUCH ANYTHING ELSE
    // IF YOU ARE TESTING, CHANGE THESE VARIABLES, DO NOT TOUCH ANYTHING ELSE

    public double speed = -1;
    public double time = 100;

    // IF YOU ARE TESTING, CHANGE THESE VARIABLES, DO NOT TOUCH ANYTHING ELSE
    // IF YOU ARE TESTING, CHANGE THESE VARIABLES, DO NOT TOUCH ANYTHING ELSE
    // good job if you got this far lol

    private DriveSubsystem drive;
    @Override
    public void initialize() {
        telemetry.addData("Move Left: Speed of ", String.valueOf(speed), " for ", String.valueOf(time), " milliseconds");
        telemetry.update();
        drive = new DriveSubsystem(hardwareMap);
        schedule(new DriveMove(drive, speed, 0, 0).withTimeout((long) time));
    }

}
