import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class NMEAData extends Structure {
	public double utc, date;
	public double pressure, temperature;
	public byte cpressure, ctemperature;
	public double winddir, windspeed;
	public byte cwinddir, cwindspeed;
	public double awinddir, awindspeed;
	public byte cawinddir, cawindspeed;
	public int latdeg, longdeg;
	public double latmin, longmin;
	public byte[] szlatdeg = new byte[3];
	public byte[] szlongdeg = new byte[4];
	public byte north, east;
	public int GPS_quality_indicator;
	public int nbsat;
	public double hdop;
	public double height_geoid;
	public byte status;
	public double sog, cog, mag_cog;
	public double heading, deviation, variation;
	public byte dev_east, var_east;
	public int nbsentences;
	public int sentence_number;
	public int seqmsgid;
	public byte AIS_channel;
	public int nbfillbits;
	public double Latitude;
	public double Longitude;
	public double Altitude;
	public double SOG;
	public double COG;
	public int year, month, day, hour, minute;
	public double second;
	public double Heading;
	public double WindDir;
	public double WindSpeed;
	public double ApparentWindDir;
	public double ApparentWindSpeed;
	public double AIS_Latitude;
	public double AIS_Longitude;
	public double AIS_SOG;
	public double AIS_COG;

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "utc", "date", "pressure", "temperature", "cpressure", "ctemperature",
				"winddir", "windspeed", "cwinddir", "cwindspeed", "awinddir", "awindspeed", "cawinddir", "cawindspeed",
				"latdeg", "longdeg", "latmin", "longmin", "szlatdeg", "szlongdeg", "north", "east",
				"GPS_quality_indicator", "nbsat", "hdop", "height_geoid", "status", "sog", "cog", "mag_cog", "heading",
				"deviation", "variation", "dev_east", "var_east", "nbsentences", "sentence_number", "seqmsgid",
				"AIS_channel", "nbfillbits", "Latitude", "Longitude", "Altitude", "SOG", "COG", "year", "month", "day",
				"hour", "minute", "second", "Heading", "WindDir", "WindSpeed", "ApparentWindDir", "ApparentWindSpeed",
				"AIS_Latitude", "AIS_Longitude", "AIS_SOG", "AIS_COG" });
	}
}
