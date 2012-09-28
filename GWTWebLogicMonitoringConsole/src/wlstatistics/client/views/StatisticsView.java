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

import wlstatistics.client.views.statisticsComponent.StatisticElement;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.Window;

public class StatisticsView extends Window {
	private Button buttonOk;
	private VerticalPanel verticalPanel;
	
	/**
	 * @wbp.parser.constructor
	 */
	public StatisticsView() {
		
		verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.addItem(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		buttonOk = new Button("Ok");
		this.setWidth(250);
	
	}
	public void addItem(StatisticElement item){
		this.verticalPanel.add(item);		
	}
	public void addButtonOk(){
		verticalPanel.add(buttonOk);
		buttonOk.setWidth("82px");		
	}

	public Button getButtonAdd(){
		return buttonOk;
	}
}
