import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class RazorAHRSData extends Structure {
	public double yaw, pitch, roll;
	public double Yaw;
	public double Pitch;
	public double Roll;

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "yaw", "pitch", "roll", "Yaw", "Pitch", "Roll" });
	}
}
