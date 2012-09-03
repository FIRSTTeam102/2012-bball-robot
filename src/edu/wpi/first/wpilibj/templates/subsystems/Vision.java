/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.RGBImage;

import Team102Lib.BackboardParticle;
import Team102Lib.VisionTargetSDBData;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.templates.RobotMap;
/**
 *
 * @author Administrator
 */
public class Vision extends Subsystem {
//    AxisCamera camera;          // the axis camera object (connected to the switch)
    CriteriaCollection cc;      // the criteria for doing the particle filter operation
    int currentImageIndex;      // When testing without a camera, this keeps track of which image we are looking at.
    public BackboardParticle bottomBoard = null;
    VisionTargetSDBData smartdashBoardData;
    String imageName = null;
    Relay lightSwitch;
    boolean lightsOn;                //


    public Vision()
    {
//        camera = AxisCamera.getInstance();  // get an instance ofthe camera
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
        currentImageIndex = 0;
        lightSwitch = new Relay(RobotMap.cameraLightsSwitchChannel, Relay.Direction.kForward);
        lightsOn = false;

        smartdashBoardData = new VisionTargetSDBData();
    }
    public void initDefaultCommand() {
        // Uncomment this if you want FindTarget to run continuously.
        // setDefaultCommand(new FindTarget());
    }
        public void switchLights(boolean switchOn){
        if((switchOn && lightsOn) || (!switchOn && !lightsOn))
            return;
        if (switchOn && !lightsOn)
        {
            lightSwitch.set(Relay.Value.kOn);
            lightsOn = true;
            System.out.println(Timer.getFPGATimestamp() + ", Lights On");
        }
        else
        {
            lightSwitch.set(Relay.Value.kOff);
            lightsOn = false;
            System.out.println(Timer.getFPGATimestamp() + ", Lights Off");
        }
    }

    public boolean findTarget()
    {
        try
        {
            ColorImage image = getImage();
            bottomBoard = processImage(image);
//            SmartDashboard.putData("Camera:", image);  // SmartDashboard gets images from the camera automatically.
            if(imageName != null)
                SmartDashboard.putString("Image: ", imageName);
            if(bottomBoard != null)
            {
                SmartDashboard.putString("Target Status: ", "Found");
                SmartDashboard.putDouble("Target Position: ", bottomBoard.x);
                SmartDashboard.putDouble("Target Distance: ", bottomBoard.distance);
                SmartDashboard.putDouble("Target Width: ", bottomBoard.width);
                SmartDashboard.putDouble("Target Aspect Ratio: ", bottomBoard.aspectRatio);
                SmartDashboard.putDouble("Target Quality: ", bottomBoard.particle.particleQuality);
                SmartDashboard.putString("Target W x H: ", bottomBoard.particle.boundingRectWidth + " x "
                        + bottomBoard.particle.boundingRectHeight);

                smartdashBoardData.showBackboardParticle(bottomBoard);
                SmartDashboard.putData("Backboard Target", smartdashBoardData);

                return true;
            }
            else
            {
                SmartDashboard.putString("Target Status: ", "Not Found");
                SmartDashboard.putDouble("Target Position: ", -1.0);
                SmartDashboard.putDouble("Target Distance: ", -1.0);
                SmartDashboard.putDouble("Target Width: ", -1.0);
                SmartDashboard.putDouble("Target Aspect Ratio: ", -1.0);
                SmartDashboard.putString("Target W x H: ", "--");
                return false;
            }
        }
        catch(NIVisionException ex1)
        {
            ex1.printStackTrace();
        }
        catch(Exception ex1)
        {
            ex1.printStackTrace();
        }
        return false;
    }
    protected ColorImage getImage() throws NIVisionException
    {

        //ColorImage image = camera.getImage();     // comment if using stored images
//        String[] imageList = {"10ft2.jpg", "20Ft2.jpg", "30Ft2.jpg", "40Ft2.jpg", "46feet.jpg", "40Ft2.jpg"
//                , "30Ft2.jpg", "20Ft2.jpg", "10ft2.jpg"
        String[] imageList = {"12ft.jpg", "12ft2.jpg","12ft3.jpg","12ft4.jpg", "Test1.bmp", "Test2.bmp"
                ,"Test3.bmp","Test4.bmp"
            };
        int imageIndex = currentImageIndex++;
        currentImageIndex %= imageList.length;
        imageName = imageList[imageIndex];
       return new RGBImage("/VisionImages/" + imageName);
//        return new RGBImage("/Test1.jpg");
//        return new RGBImage("/10ft2.jpg");
    }
    public BackboardParticle processImage(ColorImage image) throws NIVisionException
    {
        BinaryImage thresholdImage = image.thresholdRGB(25, 255, 0, 45, 0, 47);   // keep only red objects
        BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);  // remove small artifacts
        BinaryImage convexHullImage = bigObjectsImage.convexHull(false);          // fill in occluded rectangles
        BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // find filled in rectangles

        ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results

        BackboardParticle highest = null;
        BackboardParticle lowest = null;
        BackboardParticle leftmost = null;
        BackboardParticle rightmost = null;

        for (int i = 0; i < reports.length; i++) {                                // print results
            ParticleAnalysisReport r = reports[i];
            System.out.println(Timer.getFPGATimestamp() + ": Particle: " + i );

            // Keep track of where each particle fits on the 'map' of backboards.
            BackboardParticle bp = new BackboardParticle(r);
            System.out.println(bp.toString());

            if((highest == null) || (bp.y > highest.y))
                highest = bp;
            if((lowest == null) || (bp.y < lowest.y))
                lowest = bp;
            if((leftmost == null) || (bp.x < leftmost.x))
                leftmost = bp;
            if((rightmost == null) || (bp.x > rightmost.x))
                rightmost = bp;

        }
        System.out.println(filteredImage.getNumberParticles() + "  " + Timer.getFPGATimestamp());
        if(highest != null)
        {
            System.out.println("HIGHEST:");
            System.out.println(highest.toString());
        }
        if(lowest != null)
        {
            System.out.println("LOWEST:");
            System.out.println(lowest.toString());
        }
        if(leftmost != null)
        {
            System.out.println("LEFTMOST:");
            System.out.println(leftmost.toString());
        }
        if(rightmost != null)
        {
            System.out.println("RIGHTMOST:");
            System.out.println(rightmost.toString());
        }
        /**
         * all images in Java must be freed after they are used since they are allocated out
         * of C data structures. Not calling free() will cause the memory to accumulate over
         * each pass of this loop.
         */
        filteredImage.free();
        convexHullImage.free();
        bigObjectsImage.free();
        thresholdImage.free();
        image.free();

        // Figure out if we have a unique highest particle.  This assumes particleArea is unique.
//        if((highest != null)
//                && (highest.particle.particleArea != leftmost.particle.particleArea)
//                && (highest.particle.particleArea != rightmost.particle.particleArea)
//                && (highest.particle.particleArea != lowest.particle.particleArea)
//                )
//            return highest;
        // Find the lowest instead.  The logic being, it will be the one with the least distortion.
        if(lowest != null)
            return lowest;
        else
            return null;
    }
}