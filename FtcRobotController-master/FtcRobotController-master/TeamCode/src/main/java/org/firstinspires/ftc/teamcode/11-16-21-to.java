package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Stubby_TO_11_9_21")
public class Stubby_TO_11_9_21 extends LinearOpMode {

    private DcMotorEx Frontleft;
    private DcMotorEx Backleft;
    private DcMotorEx Frontright;
    private DcMotorEx Backright;
    private DcMotorEx Intakemotor;
    private DcMotorEx Ladder;
    private DcMotorEx Carousel;
    private Servo Basket;

    double power = 0.05;


    @Override
    public void runOpMode() {
        Frontleft = (DcMotorEx) hardwareMap.get(DcMotor.class, "frontLeft");
        Backleft = (DcMotorEx) hardwareMap.get(DcMotor.class, "backLeft");
        Frontright = (DcMotorEx) hardwareMap.get(DcMotor.class, "frontRight");
        Backright = (DcMotorEx) hardwareMap.get(DcMotor.class, "backRight");
        Frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        Backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        Intakemotor = (DcMotorEx)hardwareMap.get(DcMotor.class, "intakeMotor");
        Ladder = (DcMotorEx)hardwareMap.get(DcMotor.class, "ladder");
        Carousel = (DcMotorEx)hardwareMap.get(DcMotor.class, "carousel");
        Basket = hardwareMap.get(Servo.class,"basket");


        waitForStart();

        while (opModeIsActive()) {
            double powerStrafe = gamepad1.left_stick_x;
            if (Math.abs(powerStrafe) < power) powerStrafe = 0;
            double powerForward = -gamepad1.left_stick_y;
            if (Math.abs(powerForward) < power) powerForward = 0;
            double powerTurn = -gamepad1.right_stick_x;
            if (Math.abs(powerTurn) < power) powerTurn = 0;
            double p1 = -powerStrafe + powerForward - powerTurn;
            double p2 = powerStrafe + powerForward - powerTurn;
            double p3 = -powerStrafe + powerForward + powerTurn;
            double p4 = powerStrafe + powerForward + powerTurn;
            double max = Math.max(1.0, Math.abs(p1));
            max = Math.max(max, Math.abs(p2));
            max = Math.max(max, Math.abs(p3));
            max = Math.max(max, Math.abs(p4));
            p1 /= max;
            p2 /= max;
            p3 /= max;
            p4 /= max;
            Backleft.setPower(p1);
            Frontleft.setPower(p2);
            Frontright.setPower(p3);
            Backright.setPower(p4);
            telemetry.update();


            if (gamepad1.y){
                Backleft.setPower(p1 * 0.5);
                Frontleft.setPower(p2 * 0.5);
                Frontright.setPower(p3 * 0.5);
                Backright.setPower(p4 * 0.5);
                telemetry.update();
            }


            if (gamepad2.a) {
                //Ladder.setPIDFCoefficient();
                Intakemotor.setVelocity(-2000);
            }
            else {
                Intakemotor.setPower(0);
                Ladder.setPower(0);
            }


            if (gamepad2.b) {
                Ladder.setVelocity(-750);
                Intakemotor.setVelocity(2000);
            }
            else {
                Ladder.setPower(0);
                Intakemotor.setPower(0);
            }


            if (gamepad2.x) {
                ((DcMotorEx) Carousel).setVelocity(1600);
            }
            else {
                ((DcMotorEx) Carousel).setVelocity(0);
            }


            if (gamepad2.left_bumper) {
                Basket.setDirection(Servo.Direction.REVERSE);
                Basket.setPosition(1);
            }
            if (gamepad2.right_bumper) {
                Basket.setDirection(Servo.Direction.FORWARD);
                Basket.setPosition(0.5);
        }
        }
    }
}
/* Encoder resolution formula: ((((1+(46/17))) * (1+(46/11))) * 28)
Approximatley 538 ticks per revolution*/

