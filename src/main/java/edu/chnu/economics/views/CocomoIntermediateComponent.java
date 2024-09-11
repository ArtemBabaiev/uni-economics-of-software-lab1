package edu.chnu.economics.views;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;

import edu.chnu.economics.Config;
import edu.chnu.economics.cocomo.CocomoUtils;
import edu.chnu.economics.cocomo.enums.CocomoBaseСoefficients;
import edu.chnu.economics.cocomo.enums.CocomoIntermediateCoefficients;
import edu.chnu.economics.cocomo.models.CostDriver;
import edu.chnu.economics.cocomo.models.CostDriversList;
import edu.chnu.economics.cocomo.models.Rating;
import lombok.Getter;
import lombok.Setter;

public class CocomoIntermediateComponent extends VerticalLayout {

	IntermediateForm form;
	HorizontalLayout resultDisplay = new HorizontalLayout();

	public CocomoIntermediateComponent(Config config) {
		super();
		form = new IntermediateForm(config.getCostDrivers());
		form.calcBtn.addClickListener(e -> {
			if (form.binder.validate().isOk()) {
				var bean = form.binder.getBean();
				resultDisplay.removeAll();
				resultDisplay.add(new CocomoIntermediateResult(CocomoBaseСoefficients.ORGANIC.toString(),
						CocomoUtils.calculateIntermediate(bean.values, bean.kloc,
								CocomoIntermediateCoefficients.ORGANIC, config.getCostDrivers())));
				resultDisplay.add(new CocomoIntermediateResult(CocomoBaseСoefficients.EMBEDDED.toString(),
						CocomoUtils.calculateIntermediate(bean.values, bean.kloc,
								CocomoIntermediateCoefficients.EMBEDDED, config.getCostDrivers())));
				resultDisplay.add(new CocomoIntermediateResult(CocomoBaseСoefficients.SEMI_DATACHED.toString(),
						CocomoUtils.calculateIntermediate(bean.values, bean.kloc,
								CocomoIntermediateCoefficients.SEMI_DATACHED, config.getCostDrivers())));

			}
		});
		add(form, resultDisplay);
	}

	private static class IntermediateForm extends FormLayout {
		Binder<IntermediateFormModel> binder = new Binder<>(IntermediateFormModel.class);
		NumberField kloc = new NumberField("KLOC");
		Button calcBtn = new Button("Calculate");

		public IntermediateForm(CostDriversList costDrivers) {
			super();
			for (CostDriver costDriver : costDrivers) {
				Select<Rating.Levels> select = new Select<Rating.Levels>();
				select.setId(costDriver.getName());
				select.setLabel(costDriver.getName());
				select.setTooltipText(costDriver.getDesc());
				select.setItems(Rating.Levels.values());
				binder.forField(select).asRequired().bind(i -> i.get(select.getId().get()),
						(i, value) -> i.set(select.getId().get(), value));
				add(select);
			}
			setResponsiveSteps(new ResponsiveStep("100px", 3));
			binder.forField(kloc).asRequired().bind(IntermediateFormModel::getKloc, IntermediateFormModel::setKloc);
			add(kloc);
			this.add(new HorizontalLayout(calcBtn), 3);
			binder.setBean(new IntermediateFormModel());
		}
	}

	@Getter
	@Setter
	private static class IntermediateFormModel {
		Double kloc;
		Map<String, Rating.Levels> values = new HashMap<String, Rating.Levels>();

		public void set(String key, Rating.Levels value) {
			values.put(key, value);
		}

		public Rating.Levels get(String key) {
			return values.get(key);
		}
	}
}
