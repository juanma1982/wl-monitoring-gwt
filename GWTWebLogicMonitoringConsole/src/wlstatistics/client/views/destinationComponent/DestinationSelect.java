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
package wlstatistics.client.views.destinationComponent;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class DestinationSelect extends Composite{
	private Label destLabel;
	private SimpleCheckBox simpleCheckBox;
	public DestinationSelect() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		horizontalPanel.setSize("312px", "26px");
		
		simpleCheckBox = new SimpleCheckBox();
		horizontalPanel.add(simpleCheckBox);
		
		destLabel = new Label("JMSDEST");
		horizontalPanel.add(destLabel);
	}
	public void setName(String name){
		destLabel.setText(name);
	}
	public String getName(){
		return destLabel.getText();
	}
	public boolean isChecked() {
		return simpleCheckBox.getValue();
	}
}
