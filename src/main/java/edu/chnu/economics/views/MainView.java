package edu.chnu.economics.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;

import edu.chnu.economics.Config;

@Route
public class MainView extends VerticalLayout {
	public MainView(@Autowired Config config) {
		super();
		setSizeFull();
		TabSheet tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		tabSheet.add("COCOMO Base", new CocomoBaseComponent());
		tabSheet.add("COCOMO Intermediate", new CocomoIntermediateComponent(config));
		add(tabSheet);
	}
}
