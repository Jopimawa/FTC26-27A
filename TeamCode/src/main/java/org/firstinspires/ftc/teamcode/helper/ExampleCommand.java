package org.firstinspires.ftc.teamcode.helper;

import com.seattlesolvers.solverslib.command.CommandBase;

public class ExampleCommand extends CommandBase {
    /*
    ### FORMATTING COMMAND NAME ###
    The command is named after the first word of the subsystem it mainly uses:
    uses DriveSubsystem: DriveX (example: DriveMove, DriveRawMove)
    (A command may use multiple subsystems but that is disadvised for organization purposes,
     we may add a odometry subsystem later in the season which would be a use case for multi-subsystems)
    debug commands are specifically made for testing, and should not be used during the game

    Variables should be understandable what they represent, dont be afraid to right a few words, just remember KISS:
    Keep
    It
    Simple,
    Stupid

    The subsystem name should be the first word but lowercase
    (
    A general formatting in java is that you start a variable in lowercase if it is a non object variable
    like double or string, as well as if it is an instance of an object.
    If you are creating an object the variable should be capitalized at the start, and class names are also
    capitalized at the start.
    )

    end ur comments with a name that others would understand
     -jr
    */
    private final double fooPower;
    private final double barPosition;
    private final double buzzPower;
    ExampleSubsystem example;
    public ExampleCommand(ExampleSubsystem example, double fooPower, double barPosition, double buzzPower) {
        this.example = example;
        this.fooPower  = fooPower;
        this.barPosition = barPosition;
        this.buzzPower = buzzPower;
        /*
        if you want to lock the subsystem when this command is running, so no other commands can use the subsystem,
        only really useful for drive - jr
        */
        addRequirements(example);

    }

    @Override
    public void initialize() {
        // this method runs once -jr
        example.setBar(barPosition);
    }

    @Override
    public void execute() {
        // runs every cycle (repeatedly) -jr
        example.setFoo(fooPower);
        example.setBuzz(buzzPower);
    }

    @Override
    public void end(boolean interrupted) {
        // runs at the end of command (when isFinished is true OR when interrupted, like if the button isn't pressed) -jr
        example.stopFoo();
        example.stopBuzz();
    }

    @Override
    public boolean isFinished() {
        // runs every cycle, if returns true, ends command -jr

        // If the position of the bar servo is more than 50%, the command ends -jr
        if (example.getBar() >= 0.5) {
            return true;
        }
        return false;
    }
}
