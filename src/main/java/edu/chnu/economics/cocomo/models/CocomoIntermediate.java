package edu.chnu.economics.cocomo.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CocomoIntermediate {
	private double eaf;
	private double pm;
	private double tm;
	private double ss;
	private double p;
}
