import com.sun.jna.Pointer;

public class NMEADevice {
	private Pointer nmeadevice;
	private NMEAData nmeadata;
	private boolean bConnected = false;
	private boolean bThreadStarted = false;

	public NMEADevice() throws Exception {
		nmeadevice = HardwareX.CreateNMEADevicex();
		if (nmeadevice == null)
			throw new Exception("Out of memory");
		nmeadata = HardwareX.CreateNMEADatax();
		if (nmeadata == null) {
			HardwareX.DestroyNMEADevicex(nmeadevice);
			throw new Exception("Out of memory");
		}
	}

	public void connect(String cfgFilePath) throws Exception {
		int result = HardwareX.ConnectNMEADevicex(nmeadevice, cfgFilePath);
		if (result != 0)
			throw new Exception("Error " + result);
		bConnected = true;
	}

	public void disconnect() throws Exception {
		if (bConnected) {
			int result = HardwareX.DisconnectNMEADevicex(nmeadevice);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bConnected = false;
	}

	public void startThread() throws Exception {
		int result = HardwareX.StartThreadNMEADevicex(nmeadevice);
		if (result != 0)
			throw new Exception("Error " + result);
		bThreadStarted = true;
	}

	public void stopThread() throws Exception {
		if (bThreadStarted) {
			int result = HardwareX.StopThreadNMEADevicex(nmeadevice);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bThreadStarted = false;
	}

	public NMEAData getLatestData() throws Exception {
		int result = HardwareX.GetLatestDataNMEADevicex(nmeadevice, nmeadata);
		if (result != 0)
			throw new Exception("Error " + result);
		return nmeadata;
	}

	public NMEAData getLatestDataFromThread() throws Exception {
		int result = HardwareX.GetLatestDataFromThreadNMEADevicex(nmeadevice, nmeadata);
		if (result != 0)
			throw new Exception("Error " + result);
		return nmeadata;
	}

	protected void finalize() throws Throwable {
		if (bConnected)
			HardwareX.DisconnectNMEADevicex(nmeadevice);
		bConnected = false;
		HardwareX.DestroyNMEADatax(nmeadata);
		HardwareX.DestroyNMEADevicex(nmeadevice);
		super.finalize();
	}

	public static void main(String[] args) {
		try {
			NMEADevice nmeadevice = new NMEADevice();
			// Check and modify the configuration file if needed...
			nmeadevice.connect("NMEADevice0.txt");
			NMEAData nmeadata = nmeadevice.getLatestData();
			System.out.println("Latitude=" + nmeadata.Latitude + ", Longitude=" + nmeadata.Longitude + ", Altitude="
					+ nmeadata.Altitude);
			// If getLatestData() takes too much time, use a thread to access
			// data faster...
			nmeadevice.startThread();
			while (true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {
				}
				nmeadata = nmeadevice.getLatestDataFromThread();
				System.out.println("Latitude=" + nmeadata.Latitude + ", Longitude=" + nmeadata.Longitude + ", Altitude="
						+ nmeadata.Altitude);
			}
			// nmeadevice.stopThread();
			// nmeadevice.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
