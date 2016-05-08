import com.sun.jna.Pointer;

public class RazorAHRS {
	private Pointer razorahrs;
	private RazorAHRSData razorahrsdata;
	private boolean bConnected = false;
	private boolean bThreadStarted = false;

	public RazorAHRS() throws Exception {
		razorahrs = HardwareX.CreateRazorAHRSx();
		if (razorahrs == null)
			throw new Exception("Out of memory");
		razorahrsdata = HardwareX.CreateRazorAHRSDatax();
		if (razorahrsdata == null) {
			HardwareX.DestroyRazorAHRSx(razorahrs);
			throw new Exception("Out of memory");
		}
	}

	public void connect(String cfgFilePath) throws Exception {
		int result = HardwareX.ConnectRazorAHRSx(razorahrs, cfgFilePath);
		if (result != 0)
			throw new Exception("Error " + result);
		bConnected = true;
	}

	public void disconnect() throws Exception {
		if (bConnected) {
			int result = HardwareX.DisconnectRazorAHRSx(razorahrs);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bConnected = false;
	}

	public void startThread() throws Exception {
		int result = HardwareX.StartThreadRazorAHRSx(razorahrs);
		if (result != 0)
			throw new Exception("Error " + result);
		bThreadStarted = true;
	}

	public void stopThread() throws Exception {
		if (bThreadStarted) {
			int result = HardwareX.StopThreadRazorAHRSx(razorahrs);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bThreadStarted = false;
	}

	public RazorAHRSData getLatestData() throws Exception {
		int result = HardwareX.GetLatestDataRazorAHRSx(razorahrs, razorahrsdata);
		if (result != 0)
			throw new Exception("Error " + result);
		return razorahrsdata;
	}

	public RazorAHRSData getLatestDataFromThread() throws Exception {
		int result = HardwareX.GetLatestDataFromThreadRazorAHRSx(razorahrs, razorahrsdata);
		if (result != 0)
			throw new Exception("Error " + result);
		return razorahrsdata;
	}

	protected void finalize() throws Throwable {
		if (bConnected)
			HardwareX.DisconnectRazorAHRSx(razorahrs);
		bConnected = false;
		HardwareX.DestroyRazorAHRSDatax(razorahrsdata);
		HardwareX.DestroyRazorAHRSx(razorahrs);
		super.finalize();
	}

	public static void main(String[] args) {
		try {
			RazorAHRS razorahrs = new RazorAHRS();
			// Check and modify the configuration file if needed...
			razorahrs.connect("RazorAHRS0.txt");
			RazorAHRSData razorahrsdata = razorahrs.getLatestData();
			System.out.println("Yaw=" + razorahrsdata.Yaw * 180 / 3.14 + ", Pitch=" + razorahrsdata.Pitch * 180 / 3.14
					+ ", Roll=" + razorahrsdata.Roll * 180 / 3.14);
			// If getLatestData() takes too much time, use a thread to access
			// data faster...
			razorahrs.startThread();
			while (true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {
				}
				razorahrsdata = razorahrs.getLatestDataFromThread();
				System.out.println("Yaw=" + razorahrsdata.Yaw * 180 / 3.14 + ", Pitch="
						+ razorahrsdata.Pitch * 180 / 3.14 + ", Roll=" + razorahrsdata.Roll * 180 / 3.14);
			}
			// razorahrs.stopThread();
			// razorahrs.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
