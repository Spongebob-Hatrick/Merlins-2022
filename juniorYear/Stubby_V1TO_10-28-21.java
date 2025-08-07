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
  private DcMotorEx Intakemotor;
  private DcMotorEx Ladder;
  private DcMotorEx Carousel;
  private Servo Basket;

  
  @Override
  public void runOpMode() {
    Frontleft = (DCmotorEx)hardwareMap.get(DcMotor.class, "Front left");
    Backleft = (DCmotorEx)hardwareMap.get(DcMotor.class, "Back left");
    Frontright = (DCmotorEx)hardwareMap.get(DcMotor.class, "Front right");
    Backright = (DCmotorEx)hardwareMap.get(DcMotor.class, "Back right");
    Frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
    Backleft.setDirection(DcMotorSimple.Direction.REVERSE);
    Intakemotor = (DcMotorEx)hardwareMap.get(DcMotor.class, "Intake motor");
    Ladder = (DcMotorEx)hardwareMap.get(DcMotor.class, "Ladder");
    Carousel = (DcMotorEx)hardwareMap.get(DcMotor.class, "Carousel");
    Basket = hardwareMap.get(Servo.class,"Basket");
    

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
         Ladder.setVelocity(750);
          Intakemotor.setVelocity(-2000);
          }
          else {
          Intakemotor.setPower(0);
          Ladder.setPower(0);
          }
       
        if (gamepad2.b) {
          Ladder.setVelocity(-750);
          Intakemotor.setVelocity(2000);
        } else {
          Ladder.setPower(0);
          Intakemotor.setPower(0);
        }
        if (gamepad2.x) {
          ((DcMotorEx) Carousel).setVelocity(1600);
        } else {
          ((DcMotorEx) Carousel).setVelocity(0);
        }   
        
        if (gamepad2.dpad_down) {
         
        }
        if (gamepad2.dpad_up) {
          
        } else {
         
        }
         if (gamepad2.dpad_right) {
          
        }
        
         if (gamepad2.left_bumper) {
          Basket.setDirection(Servo.Direction.REVERSE);
          Basket.setPosition(1);
        } else if (gamepad2.right_bumper) {
          Basket.setDirection(Servo.Direction.FORWARD);
          Basket.setPosition(0.5);
         }
        
      }
    }
}
    /*waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        telemetry.update();
        if (gamepad2.x) {
          ((DcMotorEx) Carousel).setVelocity(1500);
        } 
        else {((DcMotorEx) Carousel).setVelocity(0);
        }
      }
    }
  }
}*/