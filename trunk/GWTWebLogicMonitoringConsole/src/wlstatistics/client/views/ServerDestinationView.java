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

import java.util.ArrayList;

import wlstatistics.client.views.destinationComponent.DestinationSelect;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.Window;

public class ServerDestinationView extends Window{
	private VerticalPanel verticalPanel;
	private Button buttonOk;
	private ArrayList<DestinationSelect> destinations= new ArrayList<DestinationSelect>();
	public ServerDestinationView() {
		
		verticalPanel = new VerticalPanel();
		this.addItem(verticalPanel);
		verticalPanel.setSize("350px", "184px");
		this.setWidth(300);
		this.setTitle("Seleccione JMS Destinations a monitorear");
		//Label lblSeleccioneJmsDestinations = new Label();
		//verticalPanel.add(lblSeleccioneJmsDestinations);
		buttonOk = new Button("Ok");
		
	}
	
	public void addButtonAdd(){
		verticalPanel.add(buttonOk);
		buttonOk.setWidth("82px");		
	}

	public Button getButtonAdd(){
		return buttonOk;
	}
	
	public void addDestinationCheck(String name){
		DestinationSelect child = new DestinationSelect();
		child.setName(name);
		destinations.add(child);
		verticalPanel.add(child);		
	}
	
	public ArrayList<DestinationSelect> getDestinations(){
		ArrayList<DestinationSelect> result = new ArrayList<DestinationSelect>();
		for (int i=0;i<verticalPanel.getWidgetCount();i++) {
			if ( DestinationSelect.class == verticalPanel.getWidget(i).getClass())
			{
				result.add((DestinationSelect)verticalPanel.getWidget(i));
			}
					
		}
		return result;
	}
}
