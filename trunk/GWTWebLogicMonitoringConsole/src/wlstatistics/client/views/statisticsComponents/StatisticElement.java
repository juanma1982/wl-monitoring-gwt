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
package wlstatistics.client.views.statisticsComponents;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class StatisticElement extends Composite {
	private Label lblStatistic;
	private TextBox dataTextBox;
	public StatisticElement() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		horizontalPanel.setHeight("39px");
		horizontalPanel.setWidth("100%");
		lblStatistic = new Label("Statistic");
		horizontalPanel.add(lblStatistic);
		horizontalPanel.setCellHorizontalAlignment(lblStatistic, HasHorizontalAlignment.ALIGN_CENTER);
		lblStatistic.setWidth("231px");
		
		dataTextBox = new TextBox();
		horizontalPanel.add(dataTextBox);
		dataTextBox.setWidth("100px");
	}
	public Label getLblStatistic() {
		return lblStatistic;
	}
	public TextBox getDataTextBox() {
		return dataTextBox;
	}
	
	public void setLabel(String value) {
		lblStatistic.setText(value);
	}
	
	public void setDataTextBox(String value) {
		dataTextBox.setValue(value);
	}
}
