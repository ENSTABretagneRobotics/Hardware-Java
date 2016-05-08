import com.sun.jna.Pointer;

public class Maestro {
	private Pointer maestro;
	private boolean bConnected = false;
	private boolean bThreadStarted = false;

	public Maestro() throws Exception {
		maestro = HardwareX.CreateMaestrox();
		if (maestro == null)
			throw new Exception("Out of memory");
	}

	public void connect(String cfgFilePath) throws Exception {
		int result = HardwareX.ConnectMaestrox(maestro, cfgFilePath);
		if (result != 0)
			throw new Exception("Error " + result);
		bConnected = true;
	}

	public void disconnect() throws Exception {
		if (bConnected) {
			int result = HardwareX.DisconnectMaestrox(maestro);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bConnected = false;
	}

	public void startThread() throws Exception {
		int result = HardwareX.StartThreadMaestrox(maestro);
		if (result != 0)
			throw new Exception("Error " + result);
		bThreadStarted = true;
	}

	public void stopThread() throws Exception {
		if (bThreadStarted) {
			int result = HardwareX.StopThreadMaestrox(maestro);
			if (result != 0)
				throw new Exception("Error " + result);
		}
		bThreadStarted = false;
	}

	public void setPWM(int channel, int pw) throws Exception {
		int result = HardwareX.SetPWMMaestrox(maestro, channel, pw);
		if (result != 0)
			throw new Exception("Error " + result);
	}

	public void setAllPWMs(int[] selectedchannels, int[] pws) throws Exception {
		int result = HardwareX.SetAllPWMsMaestrox(maestro, selectedchannels, pws);
		if (result != 0)
			throw new Exception("Error " + result);
	}

	public int getValue(int channel) throws Exception {
		int[] value = { 0 };
		int result = HardwareX.GetValueMaestrox(maestro, channel, value);
		if (result != 0)
			throw new Exception("Error " + result);
		return value[0];
	}

	public void setAllPWMsFromThread(int[] selectedchannels, int[] pws) throws Exception {
		int result = HardwareX.SetAllPWMsFromThreadMaestrox(maestro, selectedchannels, pws);
		if (result != 0)
			throw new Exception("Error " + result);
	}

	public int getValueFromThread(int channel) throws Exception {
		int[] value = { 0 };
		int result = HardwareX.GetValueFromThreadMaestrox(maestro, channel, value);
		if (result != 0)
			throw new Exception("Error " + result);
		return value[0];
	}

	protected void finalize() throws Throwable {
		if (bConnected)
			HardwareX.DisconnectMaestrox(maestro);
		bConnected = false;
		HardwareX.DestroyMaestrox(maestro);
		super.finalize();
	}

	public static void main(String[] args) {
		try {
			Maestro maestro = new Maestro();
			// Check and modify the configuration file if needed...
			maestro.connect("Maestro0.txt");
			int[] selectedchannels = { 1, 1, 1, 0, 0 };
			int[] pws = { 1000, 2000, 1000, 1500, 1500 };
			maestro.setAllPWMs(selectedchannels, pws);
			int value = maestro.getValue(11);
			System.out.println("value = " + value);
			// If getValue(), setAllPWMs()... take too much time, use a thread
			// to access data faster...
			maestro.startThread();
			int a = 0;
			while (true) {
				if ((a % 2) == 0) {
					pws = new int[] { 1000, 2000, 1250, 1500, 1500 };
					maestro.setAllPWMsFromThread(selectedchannels, pws);
				} else {
					pws = new int[] { 2000, 1000, 1750, 1500, 1500 };
					maestro.setAllPWMsFromThread(selectedchannels, pws);
				}
				a++;
				value = maestro.getValueFromThread(11);
				System.out.println("value = " + value);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) {
				}
			}
			// maestro.stopThread();
			// maestro.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
