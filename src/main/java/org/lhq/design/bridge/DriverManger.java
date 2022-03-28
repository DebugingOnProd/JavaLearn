package org.lhq.design.bridge;

public abstract class DriverManger {

	protected DriverAPI driverAPI;



	protected DriverManger(DriverAPI driverAPI){
		this.driverAPI = driverAPI;
	}

	public abstract void doDriver();
}
