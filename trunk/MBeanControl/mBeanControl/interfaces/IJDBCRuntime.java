package mBeanControl.interfaces;

public interface IJDBCRuntime {
	public String getName();

	public String getState();
	
	public Integer getActiveConnectionsCurrentCount();
	
}
