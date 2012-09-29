package wlstatistics.server;

import mBeanControl.interfaces.IJDBCRuntime;
import wlstatistics.shared.model.WLDatasource;

public class WLDatasourceTransf {

	public static WLDatasource transform(IJDBCRuntime datasource) {
		WLDatasource result = new WLDatasource();
		result.setName(datasource.getName());
		result.setStatus(datasource.getState());
		result.setConectionCount(datasource.getActiveConnectionsCurrentCount());
		return result;
	}

}
