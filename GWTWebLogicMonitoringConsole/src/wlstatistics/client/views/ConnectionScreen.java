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
package wlstatistics.client.views;

import wlstatistics.client.WeblogicMonitorServiceAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConnectionScreen extends Composite {
	WeblogicMonitorServiceAsync serverCall;
	public ConnectionScreen()
	{
		
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public ConnectionScreen(WeblogicMonitorServiceAsync weblogicMonitorServiceAsync) {
		super();
		serverCall = weblogicMonitorServiceAsync;
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("338px", "305px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("338px", "30px");
		
		Label lblHost = new Label("HostName");
		horizontalPanel.add(lblHost);
		
		final TextBox host = new TextBox();
		horizontalPanel.add(host);
		horizontalPanel.setCellHorizontalAlignment(host, HasHorizontalAlignment.ALIGN_RIGHT);
		
		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_4);
		horizontalPanel_4.setWidth("338px");
		
		Label lblPort = new Label("Port");
		horizontalPanel_4.add(lblPort);
		
		final TextBox port = new TextBox();
		horizontalPanel_4.add(port);
		horizontalPanel_4.setCellHorizontalAlignment(port, HasHorizontalAlignment.ALIGN_RIGHT);
		
		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_3);
		horizontalPanel_3.setWidth("338px");
		
		Label lblUsername = new Label("Username");
		horizontalPanel_3.add(lblUsername);
		
		final TextBox user = new TextBox();
		horizontalPanel_3.add(user);
		horizontalPanel_3.setCellHorizontalAlignment(user, HasHorizontalAlignment.ALIGN_RIGHT);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_1);
		horizontalPanel_1.setWidth("338px");
		
		Label lblPassword = new Label("Password");
		horizontalPanel_1.add(lblPassword);
		
		final TextBox password = new TextBox();
		horizontalPanel_1.add(password);
		horizontalPanel_1.setCellHorizontalAlignment(password, HasHorizontalAlignment.ALIGN_RIGHT);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_2);
		horizontalPanel_2.setWidth("337px");
		
		Button btnNewButton = new Button("Connect");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*serverCall.ConnectToServer(host.getValue(), port.getValue(), user.getValue(), password.getValue(), new AsyncCallback<Void>() {
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicar con servidor");						
					}

					@Override
					public void onSuccess(Void result) {
						
						RootPanel rootPanel = RootPanel.get();
						rootPanel.clear();
						MainServerViewController mainController = new MainServerViewController(UtilGWT.getInstance());
						mainController.showServerView();
					
					}
				});*/
			}
		});
		horizontalPanel_2.add(btnNewButton);
		horizontalPanel_2.setCellHorizontalAlignment(btnNewButton, HasHorizontalAlignment.ALIGN_CENTER);
	}

}
