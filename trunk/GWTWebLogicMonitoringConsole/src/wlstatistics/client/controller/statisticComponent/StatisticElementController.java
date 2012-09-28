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
package wlstatistics.client.controller.statisticComponent;

import wlstatistics.client.views.statisticsComponent.StatisticElement;

public class StatisticElementController {
	private StatisticElement elementView;
	private String propertyToWatch;
	
	public String getPropertyToWatch() {
		return propertyToWatch;
	}

	public void setPropertyToWatch(String propertyToWatch) {
		this.propertyToWatch = propertyToWatch;
	}

	public StatisticElement getElementView() {
		return elementView;
	}

	public void setElementView(StatisticElement elementView) {
		this.elementView = elementView;
	}
	
	public void setLabelName(String value){
		this.elementView.setLabel(value);
		this.propertyToWatch = value;
	}
	
	public void refreshValue(String value){
		this.elementView.setDataTextBox(value);
	}
}
