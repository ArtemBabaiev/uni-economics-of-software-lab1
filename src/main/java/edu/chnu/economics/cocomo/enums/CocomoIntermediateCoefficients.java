package edu.chnu.economics.cocomo.enums;

import lombok.Getter;

@Getter
public enum CocomoIntermediateCoefficients {
	ORGANIC(3.2, 1.05, 2.5, 0.38), SEMI_DATACHED(3.0, 1.12, 2.5, 0.35), EMBEDDED(2.8, 1.2, 2.5, 0.32);

	private CocomoIntermediateCoefficients(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	private double a;
	private double b;
	private double c;
	private double d;

}
