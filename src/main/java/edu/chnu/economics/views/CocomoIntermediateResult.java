package edu.chnu.economics.views;

import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Paragraph;

import edu.chnu.economics.cocomo.models.CocomoIntermediate;

public class CocomoIntermediateResult extends Details {
	public CocomoIntermediateResult(String type, CocomoIntermediate base) {
		super();
		this.setSummaryText(type);
		this.setOpened(true);
		this.add(new Paragraph(String.format("EAF = %.2f", base.getEaf())));
		this.add(new Paragraph(String.format("PM = %.2f люд*міс", base.getPm())));
		this.add(new Paragraph(String.format("TM = %.2f міс", base.getTm())));
		this.add(new Paragraph(String.format("SS = %.2f люд", base.getSs())));
		this.add(new Paragraph(String.format("P = %.2f", base.getP())));
	}
}