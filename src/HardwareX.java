import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class HardwareX {
	static {
		Native.register("hardwarex");
	}

	public static native Pointer CreateRazorAHRSx();

	public static native void DestroyRazorAHRSx(Pointer pRazorAHRS);

	public static native RazorAHRSData CreateRazorAHRSDatax();

	public static native void DestroyRazorAHRSDatax(RazorAHRSData pRazorAHRSData);

	public static native int GetLatestDataRazorAHRSx(Pointer pRazorAHRS, RazorAHRSData pRazorAHRSData);

	public static native int ConnectRazorAHRSx(Pointer pRazorAHRS, String szCfgFilePath);

	public static native int DisconnectRazorAHRSx(Pointer pRazorAHRS);

	public static native int GetLatestDataFromThreadRazorAHRSx(Pointer pRazorAHRS, RazorAHRSData pRazorAHRSData);

	public static native int StartThreadRazorAHRSx(Pointer pRazorAHRS);

	public static native int StopThreadRazorAHRSx(Pointer pRazorAHRS);

	public static native Pointer CreateNMEADevicex();

	public static native void DestroyNMEADevicex(Pointer pNMEADevice);

	public static native NMEAData CreateNMEADatax();

	public static native void DestroyNMEADatax(NMEAData pNMEAData);

	public static native int GetLatestDataNMEADevicex(Pointer pNMEADevice, NMEAData pNMEAData);

	public static native int ConnectNMEADevicex(Pointer pNMEADevice, String szCfgFilePath);

	public static native int DisconnectNMEADevicex(Pointer pNMEADevice);

	public static native int GetLatestDataFromThreadNMEADevicex(Pointer pNMEADevice, NMEAData pNMEAData);

	public static native int StartThreadNMEADevicex(Pointer pNMEADevice);

	public static native int StopThreadNMEADevicex(Pointer pNMEADevice);

	public static native Pointer CreateSSC32x();

	public static native void DestroySSC32x(Pointer pSSC32);

	public static native int SetPWMSSC32x(Pointer pSSC32, int channel, int pw);

	public static native int SetAllPWMsSSC32x(Pointer pSSC32, int[] selectedchannels, int[] pws);

	public static native int ConnectSSC32x(Pointer pSSC32, String szCfgFilePath);

	public static native int DisconnectSSC32x(Pointer pSSC32);

	public static native int SetAllPWMsFromThreadSSC32x(Pointer pSSC32, int[] selectedchannels, int[] pws);

	public static native int StartThreadSSC32x(Pointer pSSC32);

	public static native int StopThreadSSC32x(Pointer pSSC32);

	public static native Pointer CreateMaestrox();

	public static native void DestroyMaestrox(Pointer pMaestro);

	public static native int GetValueMaestrox(Pointer pMaestro, int channel, int[] pValue);

	public static native int SetPWMMaestrox(Pointer pMaestro, int channel, int pw);

	public static native int SetAllPWMsMaestrox(Pointer pMaestro, int[] selectedchannels, int[] pws);

	public static native int ConnectMaestrox(Pointer pMaestro, String szCfgFilePath);

	public static native int DisconnectMaestrox(Pointer pMaestro);

	public static native int SetAllPWMsFromThreadMaestrox(Pointer pMaestro, int[] selectedchannels, int[] pws);

	public static native int GetValueFromThreadMaestrox(Pointer pMaestro, int channel, int[] pValue);

	public static native int StartThreadMaestrox(Pointer pMaestro);

	public static native int StopThreadMaestrox(Pointer pMaestro);
}
