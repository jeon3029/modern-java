package chap1;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Filtering {
	//init sample data
	static List<Apple> inventory = Arrays.asList(
			new Apple(80, "green"),
			new Apple(155, "green"),
			new Apple(120, "red")
	);

	public static void main(String[] args) {
		behaviorParameters();
		lambda();
		streaming();
	}

	private static void streaming() {
		System.out.println("streaming parallel");

		System.out.println("--------------------------------");
		System.out.println("stream sequential");
		List<Apple> heavyApples = inventory.stream().filter((Apple a)-> a.getWeight() > 150).collect(toList());
		System.out.println(heavyApples);
		System.out.println("stream parallel");
		List<Apple> heavyApples2 = inventory.parallelStream().filter((Apple a)-> a.getWeight() > 150).collect(toList());
		System.out.println(heavyApples2);
	}

	private static void lambda() {
		System.out.println("pridicate method");
		List<Apple> greenApples = filterApples(inventory, Filtering::isGreenApple);
		System.out.println("green(color=green)");
		System.out.println(greenApples);


		List<Apple> heavyApples = filterApples(inventory, Filtering::isHeavyApple);

		System.out.println("heavy(weight>150)");
		System.out.println(heavyApples);

		List<Apple> filterLambda = filterApples(inventory, (Apple a) -> a.getWeight()>150 || "red".equals(a.getColor()));
		System.out.println("lambda : heavy or red(>150 or RED)");
		System.out.println(filterLambda);


	}

	private static void behaviorParameters() {
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

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterHeavyApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > 150) {
				result.add(apple);
			}
		}
		return result;
	}

	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

	private interface Predicate<T>{
		boolean test(T t);
	}
	private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		inventory.forEach(apple->{
			if(p.test(apple)) result.add(apple);
		});
		return result;
	}
}

