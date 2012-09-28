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
package wlstatistics.client.views.connectionComponent;

import wlstatistics.client.WeblogicMonitorService.UtilGWT;

import com.google.gwt.user.client.ui.PopupPanel;
import com.smartgwt.client.widgets.Window;

public class ConnectionPopUp extends Window {
	private ConnectionScreen component = new ConnectionScreen(UtilGWT.getInstance());
	public ConnectionPopUp(){				
		this.addItem(component);
		this.setSize("400px", "350px");
		this.setTitle("Nueva Conexión");
		//this.centerInPage();
	}
	public ConnectionScreen getComponent(){
		return component;
	}
}
