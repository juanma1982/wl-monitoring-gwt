/*
 * Copyright 2011 Juan Cruz Basso
 * juancruzbasso@gmail.com
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wlstatistics.server;

import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IJDBCRuntime;
import mBeanControl.interfaces.IServer;
import weblogic.health.HealthState;
import wlstatistics.shared.model.WLServer;

public class WLServerTransfor {

	public static WLServer transform(IServer server) {
		WLServer wLServer = new WLServer();
		try {
			wLServer.setName(server.getName());
			wLServer.setHost(server.getListenAddress());
			wLServer.setPort(server.getListenPort().toString());
			wLServer.setVersion(server.getVersion());
			wLServer.setServerStatus(server.getState());
			wLServer.setHealth(server.getHealthState());
			if (server.getIJDBCRuntimeService() != null)
				for (IJDBCRuntime datasource : server.getIJDBCRuntimeService()
						.getIJDBCRuntimes()) {
					wLServer.addDatasource(WLDatasourceTransf
							.transform(datasource));
				}
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wLServer;
	}
	

}
