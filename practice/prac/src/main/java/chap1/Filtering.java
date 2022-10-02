package chap1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Filtering {
	public static void main(String[] args) {
		behaviorParameters();
	}

	private static void behaviorParameters() {
		//init sample data
		List<Apple> inventory = Arrays.asList(
				new Apple(80, "green"),
				new Apple(155, "green"),
				new Apple(120, "red")
		);
		//sample data print
		System.out.println("before sort : " + inventory);

		Collections.sort(inventory, new Comparator<Apple>() {
			public int compare(Apple o1, Apple o2) {
				return Integer.compare(o1.getWeight(), o2.getWeight());
			}
		});
		System.out.println("after sort : " + inventory);

		Collections.shuffle(inventory);

		System.out.println("############ANOTHER METHOD############");

		System.out.println("before sort : " + inventory);
		inventory.sort(comparing(Apple::getWeight));
		System.out.println("after sort : " + inventory);
	}


	public static class Apple {

		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color) {
			this.weight = weight;
			this.color = color;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		@SuppressWarnings("boxing")
		@Override
		public String toString() {
			return String.format("Apple{color='%s', weight=%d}", color, weight);
		}

	}
}

