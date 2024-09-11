package edu.chnu.economics.cocomo.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CocomoBase {
	private double pm;
	private double tm;
	private double ss;
	private double p;
	
	@Override
	public String toString() {
		return String.format("""
				PM = %s люд*міс
				TM = %s міс
				SS = %s люд
				P = %s """, pm, tm, ss, p);
	}
}
