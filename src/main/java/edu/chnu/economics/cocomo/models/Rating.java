package edu.chnu.economics.cocomo.models;

import java.util.function.Function;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
	private Double veryLow;
	private Double low;
	private Double nominal;
	private Double high;
	private Double veryHigh;
	private Double extraHigh;

	@Getter
	public static enum Levels {
		VERY_LOW(Rating::getVeryLow), LOW(Rating::getLow), NOMINAL(Rating::getNominal), HIGH(Rating::getHigh),
		VERY_HIGH(Rating::getVeryHigh), EXTRA_HIGH(Rating::getExtraHigh),;

		Function<Rating, Double> getter;

		Levels(Function<Rating, Double> getter) {
			this.getter = getter;
		}
	}
}
