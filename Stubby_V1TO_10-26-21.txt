package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test15 (Blocks to Java)")
public class Test15 extends LinearOpMode {

  private DcMotor Frontleft;
  private DcMotor Backleft;
  private DcMotor Frontright;
  private DcMotor Backright;
  private DcMotorEx Intakewheels;
  private DcMotorEx Transfer;
  private DcMotorEx Shooterwheel;
  private DcMotor Arm;
  private Servo Kicker;
  private Servo Clamp;

  
  @Override
  public void runOpMode() {
    Frontleft = hardwareMap.get(DcMotor.class, "Front left");
    Backleft = hardwareMap.get(DcMotor.class, "Back left");
    Frontright = hardwareMap.get(DcMotor.class, "Front right");
    Backright = hardwareMap.get(DcMotor.class, "Back right");
    Frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
    Backleft.setDirection(DcMotorSimple.Direction.REVERSE);
    Intakewheels = (DcMotorEx)hardwareMap.get(DcMotor.class, "Intake wheels");
    Transfer = (DcMotorEx)hardwareMap.get(DcMotor.class, "Transfer ");
    Shooterwheel = (DcMotorEx)hardwareMap.get(DcMotor.class, "Shooter wheel");
    Arm = hardwareMap.get(DcMotor.class, "Arm");
    Kicker = hardwareMap.get(Servo.class,"Kicker");
    Clamp = hardwareMap.get(Servo.class, "Clamp");

    // Put initialization blocks here.
    waitForStart();
   
      while (opModeIsActive()) {
    
                 double powerStrafe = gamepad1.left_stick_x;
            if (Math.abs(powerStrafe) < 0.05) powerStrafe = 0;
            double powerForward = -gamepad1.left_stick_y;
            if (Math.abs(powerForward) < 0.05) powerForward = 0;
            double powerTurn = -gamepad1.right_stick_x;
            if (Math.abs(powerTurn) < 0.05) powerTurn = 0;
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
          Backleft.setPower(p1 * 0.05);
            Frontleft.setPower(p2 * 0.05);
            Frontright.setPower(p3 * 0.05);
            Backright.setPower(p4 * 0.05);
              telemetry.update();
        }
                
       if (gamepad2.a) {
         Transfer.setVelocity(750);
          Intakewheels.setVelocity(-2000);
          }
          else {
          Intakewheels.setPower(0);
          Transfer.setPower(0);
          }
       
        if (gamepad2.b) {
          Transfer.setVelocity(-750);
          Intakewheels.setVelocity(2000);
        } else {
          Transfer.setPower(0);
          Intakewheels.setPower(0);
        }
        if (gamepad2.x) {
          ((DcMotorEx) Shooterwheel).setVelocity(1600);
        } else {
          ((DcMotorEx) Shooterwheel).setVelocity(0);
        }   
        
        if (gamepad2.dpad_down) {
          Arm.setPower(-1);
        }
        if (gamepad2.dpad_up) {
          Arm.setPower(0.4);
        } else {
          Arm.setPower(0);
        }
         if (gamepad2.dpad_right) {
          Clamp.setDirection(Servo.Direction.REVERSE);
          Clamp.setPosition(1);
        } else if (gamepad2.dpad_left) {
          Clamp.setDirection(Servo.Direction.FORWARD);
          Clamp.setPosition(0.5);
        }
        
         if (gamepad2.left_bumper) {
          Kicker.setDirection(Servo.Direction.REVERSE);
          Kicker.setPosition(1);
        } else if (gamepad2.right_bumper) {
          Kicker.setDirection(Servo.Direction.FORWARD);
          Kicker.setPosition(0.5);
         }
        
      }
    }
}
    /*waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        telemetry.update();
        if (gamepad2.x) {
          ((DcMotorEx) Shooterwheel).setVelocity(1500);
        } 
        else {((DcMotorEx) Shooterwheel).setVelocity(0);
        }
      }
    }
  }
}*/