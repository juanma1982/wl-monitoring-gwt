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
package wlstatistics.client;

import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.controller.MainServerViewController;

import com.google.gwt.core.client.EntryPoint;


public class WebLogicConsole implements EntryPoint {
	
	public void onModuleLoad() {
		//RootPanel rootPanel = RootPanel.get();
		//rootPanel.add(new ConnectionScreen(UtilGWT.getInstance()));
		
		MainServerViewController mainController = new MainServerViewController(UtilGWT.getInstance());
		mainController.showServersView();
		//mainController.showServerView();
		//mainController.showCVDomainServersView();
		//mainController.test();
	}
}
