package edu.chnu.economics.cocomo.models;

import java.util.ArrayList;

public class CostDriversList extends ArrayList<CostDriver> {

	public Rating getRatingByName(String name) {
		return this.stream().filter(i -> i.getName().equals(name)).findFirst().get().getRating();
	}
}
