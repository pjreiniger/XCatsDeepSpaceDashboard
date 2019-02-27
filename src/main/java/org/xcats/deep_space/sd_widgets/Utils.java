package org.xcats.deep_space.sd_widgets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public final class Utils
{
    private Utils()
    {

    }

    public static void setColor(Shape aShape, Color aDefaultColor, double aSpeed)
    {
        if (aDefaultColor != null && Math.abs(aSpeed) < .005)
        {
            aShape.setFill(aDefaultColor);
        }
        else
        {
            aShape.setFill(getMotorColor(aSpeed));
        }
    }

    /**
     * Gets a color for a motor based on a speed.
     * 
     * @param aSpeed
     *            The speed
     * @return The calculated color
     */
    @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
    public static Color getMotorColor(double aSpeed)
    {
        double speed = aSpeed;

        if (Double.isNaN(speed))
        {
            speed = 0;
        }
        if (speed > 1)
        {
            speed = 1;
        }
        else if (speed < -1)
        {
            speed = -1;
        }

        double percent = (speed + 1) / 2;
        double hue = percent * 120; // Sweep lower third of the color wheel for
                                    // red -> green
        double saturation = 1;
        double brightness = 1;

        return Color.hsb(hue, saturation, brightness);
    }
}
