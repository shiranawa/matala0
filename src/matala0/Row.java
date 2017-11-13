package matala0;



public class Row {

	@Override
	public String toString() {
		return  MAC +","+ SSID + "," + AuthMode + "," + FirstSeen + "," + Channel + "," + RSSI + "," + CurrentLatitude
				+ "," + CurrentLongitude + "," + AltitudeMeters + "," + AccuracyMeters + "," + type ;
	}


	public double getCurrentLongitude() {
		return CurrentLongitude;
	}


	public double getCurrentLatitude() {
		return CurrentLatitude;
	}


	public double getAccuracyMeters() {
		return AccuracyMeters;
	}


	public int getRSSI() {
		return RSSI;
	}


	public int getChannel() {
		return Channel;
	}


	public int getAltitudeMeters() {
		return AltitudeMeters;
	}


	public String getAuthMode() {
		return AuthMode;
	}


	public String getSSID() {
		return SSID;
	}


	public String getMAC() {
		return MAC;
	}


	public String getType() {
		return type;
	}


	public String getFirstSeen() {
		return FirstSeen;
	}


	double CurrentLongitude, CurrentLatitude, AccuracyMeters;
	int RSSI, Channel, AltitudeMeters;
	String AuthMode, SSID, MAC, type, FirstSeen;


	public Row(double currentLongitude, double currentLatitude, double accuracyMeters, int rSSI, int channel,
			int altitudeMeters, String authMode, String sSID, String mAC, String type, String firstSeen) {
		super();
		MAC = mAC;
		SSID = sSID;
		AuthMode = authMode;
		FirstSeen = firstSeen;
		Channel = channel;
		RSSI = rSSI;
		CurrentLatitude = currentLatitude;
		CurrentLongitude = currentLongitude;
		AltitudeMeters = altitudeMeters;
		AccuracyMeters = accuracyMeters;
		this.type = type;

	}


	public Row(String line) {
		String[] split = line.split(",");
		MAC = split[0];
		SSID = split[1];
		AuthMode = split[2];
		FirstSeen = split[3];
		Channel = Integer.parseInt(split[4]);
		RSSI = Integer.parseInt(split[5]);
		CurrentLatitude = Double.parseDouble(split[6]);
		CurrentLongitude = Double.parseDouble(split[7]);
		AltitudeMeters = Integer.parseInt(split[8]);
		AccuracyMeters = Double.parseDouble(split[9]);
		type = split[10];

	}


}