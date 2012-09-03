/*
 * Class to extend particle to let us do calculations on the backboards of FRC 2012
 */


package Team102Lib;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class BackboardParticle
{
//    public static final double CAMERA_FIELD_OF_VIEW =  43.5; //  degrees

    // NOTE: These need to be tuned.
    public static final double TanOfHalfCameraFieldWidth = 0.3989595459737194d;
    public static final double TanOfHalfCameraFieldHeight = 0.29921965948028956d;   // Derived.
    public static final double bbBoxHeight = 18.0; // height from bottom of tape to top of tape in inches.
    public static final double bbBoxWidth = 24.0; // width in inches.
    public static boolean calcByWidth = false;   // Calc distance using height or width?

    public ParticleAnalysisReport particle;
    public final double x;
    public final double y;
    public final double distance;
    public final double boundingRectRight;
    public final double boundingRectBottom;
    public final double width;
    public final double aspectRatio;
    public BackboardParticle(ParticleAnalysisReport par)
    {
        particle = par;

        // Calculate the distance either using the height of the backboard rectangle or the width.
        double scaleFactorToInches;
        if(calcByWidth)
            scaleFactorToInches = bbBoxWidth / (double) particle.boundingRectWidth;
        else
            scaleFactorToInches = bbBoxHeight / (double) particle.boundingRectHeight;

        double viewHeightInInches = (double) particle.imageHeight * scaleFactorToInches;
        double viewWidthInInches = (double) particle.imageWidth * scaleFactorToInches;

        if(calcByWidth)
            distance = ((viewWidthInInches / 2.0) / TanOfHalfCameraFieldWidth);
        else
            distance = ((viewHeightInInches / 2.0) / TanOfHalfCameraFieldHeight);

        x = (particle.center_mass_x - (particle.imageWidth / 2.0)) / (particle.imageWidth / 2.0);
        y = -(particle.center_mass_y - (particle.imageHeight / 2.0)) / (particle.imageHeight / 2.0);

        // Get the width on the -1 to 1 scale
        width = (2.0 * (double) particle.boundingRectWidth) / (double) particle.imageWidth;

//        System.out.println("View Inches W x H: " + viewWidthInInches + " x " + viewHeightInInches);
//        System.out.println("Camera Vertical Angle / 2: " + MathLib.atan(viewHeightInInches / 121.0));
//        System.out.println("Ratio of height to distance: " + (viewHeightInInches / 2) / distance);

        boundingRectRight = particle.boundingRectLeft + particle.boundingRectWidth;
        boundingRectBottom = particle.boundingRectTop + particle.boundingRectHeight;
        aspectRatio = (double) particle.boundingRectWidth / (double) particle.boundingRectHeight;
    }

        public String toString() {
                    return "Particle Report: \n"
                + "    Image W x H    : " + particle.imageHeight + " x " + particle.imageWidth + "\n"
                + "    Center of mass  : ( " + particle.center_mass_x + ", " + particle.center_mass_y + " )\n"
                + "      normalized    : ( " + particle.center_mass_x_normalized + ", " + particle.center_mass_y_normalized + " )\n"
                + "    Area            : " + particle.particleArea + "\n"
                + "      percent       : " + particle.particleToImagePercent + "\n"
                + "    Bounding Rect   : ( " + particle.boundingRectLeft + ", " + particle.boundingRectTop
                            + " ) -> (" + boundingRectRight + ", " + boundingRectBottom + ")\n"
                + "        W x H       : " + particle.boundingRectWidth + " x " + particle.boundingRectHeight + "\n"
                + "    Quality         : " + particle.particleQuality + "\n"
                + "    Position (-1, 1): " + x + ", " + y + "\n"
                + "    Distance        : " + distance + "\n"
                + "    Aspect Ratio    : " + aspectRatio + "\n"
                ;

    }
}
