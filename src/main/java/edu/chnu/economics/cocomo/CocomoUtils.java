package edu.chnu.economics.cocomo;

import java.util.Map;

import edu.chnu.economics.cocomo.enums.CocomoBaseСoefficients;
import edu.chnu.economics.cocomo.enums.CocomoIntermediateCoefficients;
import edu.chnu.economics.cocomo.models.CocomoBase;
import edu.chnu.economics.cocomo.models.CocomoIntermediate;
import edu.chnu.economics.cocomo.models.CostDriversList;
import edu.chnu.economics.cocomo.models.Rating;

public class CocomoUtils {

	public static CocomoBase calculateBase(long kloc, CocomoBaseСoefficients base) {
		double E = base.getA() * Math.pow(kloc, base.getB());
		double T = base.getC() * Math.pow(E, base.getD());
		double S = E / T;
		double P = kloc / E;
		return CocomoBase.builder().pm(E).tm(T).ss(S).p(P).build();
	}

	public static CocomoIntermediate calculateIntermediate(Map<String, Rating.Levels> selectedLevels, double kloc, CocomoIntermediateCoefficients coef,
			CostDriversList costDrivers) {
		Double eaf = 1.0;
		for (var pair : selectedLevels.entrySet()) {
			Double cd = pair.getValue().getGetter().apply(costDrivers.getRatingByName(pair.getKey()));
			if (cd != null) {
				eaf *= cd;
			}
		}
		double E = coef.getA() * Math.pow(kloc, coef.getB()) * eaf;
		double T = coef.getC() * Math.pow(E, coef.getD());
		double S = E / T;
		double P = kloc / E;
		
		return CocomoIntermediate.builder().eaf(eaf).pm(E).tm(T).ss(S).p(P).build();
	}

}
