import com.sun.jna.Pointer;

public class SSC32 {
	private Pointer ssc32;
	private boolean bConnected = false;
	private boolean bThreadStarted = false;

	public SSC32() throws Exception {
		ssc32 = HardwareX.CreateSSC32x();
		if (ssc32 == null)
			throw new Exception("Out of memory");
	}

	public void connect(String cfgFilePath) throws Exception {
		int result = HardwareX.ConnectSSC32x(ssc32, cfgFilePath);
		if (result != 0)
			throw new Exception("Error " + result);
		bConnected = true;
	}

	public void disconnect() throws Exception {
		if (bConnected) {
			int result = HardwareX.DisconnectSSC32x(ssc32);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bConnected = false;
	}

	public void startThread() throws Exception {
		int result = HardwareX.StartThreadSSC32x(ssc32);
		if (result != 0)
			throw new Exception("Error " + result);
		bThreadStarted = true;
	}

	public void stopThread() throws Exception {
		if (bThreadStarted) {
			int result = HardwareX.StopThreadSSC32x(ssc32);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bThreadStarted = false;
	}

	public void setPWM(int channel, int pw) throws Exception {
		int result = HardwareX.SetPWMSSC32x(ssc32, channel, pw);
		if (result != 0)
			throw new Exception("Error " + result);
	}

	public void setAllPWMs(int[] selectedchannels, int[] pws) throws Exception {
		int result = HardwareX.SetAllPWMsSSC32x(ssc32, selectedchannels, pws);
		if (result != 0)
			throw new Exception("Error " + result);
	}

	public void setAllPWMsFromThread(int[] selectedchannels, int[] pws) throws Exception {
		int result = HardwareX.SetAllPWMsFromThreadSSC32x(ssc32, selectedchannels, pws);
		if (result != 0)
			throw new Exception("Error " + result);
	}

	protected void finalize() throws Throwable {
		if (bConnected)
			HardwareX.DisconnectSSC32x(ssc32);
		bConnected = false;
		HardwareX.DestroySSC32x(ssc32);
		super.finalize();
	}

	public static void main(String[] args) {
		try {
			SSC32 ssc32 = new SSC32();
			// Check and modify the configuration file if needed...
			ssc32.connect("SSC320.txt");
			int[] selectedchannels = { 1, 1, 1, 0, 0 };
			int[] pws = { 1000, 2000, 1000, 1500, 1500 };
			ssc32.setAllPWMs(selectedchannels, pws);
			// If setPWM(), setAllPWMs()... take too much time, use a thread to
			// access data faster...
			ssc32.startThread();
			int a = 0;
			while (true) {
				if ((a % 2) == 0) {
					pws = new int[] { 1000, 2000, 1250, 1500, 1500 };
					ssc32.setAllPWMsFromThread(selectedchannels, pws);
				} else {
					pws = new int[] { 2000, 1000, 1750, 1500, 1500 };
					ssc32.setAllPWMsFromThread(selectedchannels, pws);
				}
				a++;
				System.out.println("" + a);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) {
				}
			}
			// ssc32.stopThread();
			// ssc32.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
