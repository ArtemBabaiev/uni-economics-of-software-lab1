package edu.chnu.economics.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import edu.chnu.economics.cocomo.CocomoUtils;
import edu.chnu.economics.cocomo.enums.CocomoBaseСoefficients;

public class CocomoBaseComponent extends VerticalLayout {

	NumberField klocField = new NumberField("KLOC");
	Button calcBtn = new Button("Calculate", e -> handleCalculateClick());

	HorizontalLayout resultDisplay = new HorizontalLayout();

	public CocomoBaseComponent() {
		super();
		add(klocField, calcBtn, resultDisplay);
		setSizeFull();
		resultDisplay.setWidthFull();
		resultDisplay.setJustifyContentMode(JustifyContentMode.AROUND);
	}

	private void handleCalculateClick() {
		long kloc = klocField.getValue().longValue();
		resultDisplay.removeAll();
		resultDisplay.add(new CocomonBaseResult(CocomoBaseСoefficients.ORGANIC.toString(),
				CocomoUtils.calculateBase(kloc, CocomoBaseСoefficients.ORGANIC)));
		resultDisplay.add(new CocomonBaseResult(CocomoBaseСoefficients.SEMI_DATACHED.toString(),
				CocomoUtils.calculateBase(kloc, CocomoBaseСoefficients.SEMI_DATACHED)));
		resultDisplay.add(new CocomonBaseResult(CocomoBaseСoefficients.EMBEDDED.toString(),
				CocomoUtils.calculateBase(kloc, CocomoBaseСoefficients.EMBEDDED)));
	}
}
