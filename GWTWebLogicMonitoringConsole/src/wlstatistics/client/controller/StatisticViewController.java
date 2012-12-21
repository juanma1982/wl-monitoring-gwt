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
package wlstatistics.client.controller;

import java.util.ArrayList;

import wlstatistics.client.WeblogicMonitorServiceAsync;
import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.controller.statisticComponent.StatisticElementController;
import wlstatistics.client.views.StatisticsView;
import wlstatistics.client.views.statisticsComponent.StatisticElement;
import wlstatistics.shared.model.Domain;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StatisticViewController {
	private StatisticsView view;
	private Domain domain;
	private ArrayList<StatisticElementController> statisticItems =  new ArrayList<StatisticElementController>();
	private WeblogicMonitorServiceAsync serverCall = UtilGWT.getInstance();
	
	public StatisticViewController(StatisticsView v,ArrayList<String> items,Domain domainL){
		view = v;
		domain = domainL;
		
		for (String itemName : domain.getJmsDestinationSelected()) {
			StatisticElementController elemController = new StatisticElementController();
			StatisticElement elem = new StatisticElement();
			
			elemController.setElementView(elem);
			elemController.setLabelName(itemName);
			
			statisticItems.add(elemController);
			view.addItem(elem);
		}
		Timer t = new Timer() {
		      public void run() {
		    	  refreshStatistics();
		      };};
		     t.scheduleRepeating(5000);
		 view.addButtonOk();
		 view.getButtonAdd().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				view.hide();
			}
		});
		 //view.centerInPage();
		 view.setAutoSize(true);
		 view.draw();
	}
	
	public void refreshStatistics(){
		for (final StatisticElementController item : statisticItems) {
			serverCall.getItemValue(domain.getKey(),item.getPropertyToWatch(), new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
					item.refreshValue(result);					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error al obtener dato de estadistica " +item.getPropertyToWatch());
				}
			});
			
		}
	}
}
