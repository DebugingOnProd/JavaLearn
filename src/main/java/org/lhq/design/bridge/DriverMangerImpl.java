package org.lhq.design.bridge;

public class DriverMangerImpl extends DriverManger {

	private String username;
	private String password;
	private String url;



	public DriverMangerImpl(String username,
							   String password,
							   String url,
							   DriverAPI driver) {
		super(driver);
		this.username = username;
		this.password = password;
		this.url = url;
	}

	@Override
	public void doDriver() {
		driverAPI.doConnect(username, password,url);
	}
}
