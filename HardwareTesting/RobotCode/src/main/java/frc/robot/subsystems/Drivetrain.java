package frc.robot.subsystems;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Hardware.*;

public class Drivetrain extends SubsystemBase {

    public static Talon mLeftWheelsMaster = new Talon(kLeftMaster);
    public static Talon mRightWheelsMaster = new Talon(kRightMaster);
    private final Field2d field = new Field2d();

    public void setDutyCycles(double leftDutyCycle, double rightDutyCycle) {
        mLeftWheelsMaster.set(leftDutyCycle);
        mRightWheelsMaster.set(rightDutyCycle);
    }

    @Override
    public void periodic() {
        SmartDashboard.putData("Field", field);
        field.setRobotPose(new Pose2d(new Translation2d(2, 2), Rotation2d.fromDegrees(0)));
    }

    public Field2d getField() {
        return field;
    }

}
