package wlstatistics.shared.model;

import java.io.Serializable;

public class WLDatasource implements Serializable {
	private static String RUNNING = "RUNNING";
	private static String RUNNINGDS = "Running";
	private static String DOWN = "DOWN";
	private String name;
	private String status;
	private Integer conectionCount;
	
	public WLDatasource(){
		name ="";
		status = "";
		conectionCount = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getConectionCount() {
		return conectionCount;
	}
	public void setConectionCount(Integer conectionCount) {
		this.conectionCount = conectionCount;
	}
	public String getIndicatorResult(){
		String result;
		if(this.status.equals(RUNNINGDS))
			result = RUNNING;
		else
			result = DOWN;
		
		return result;
	}
	
}
