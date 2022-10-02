package chap2;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BehaviorParameters {
	// 리스트의 모든 요소에 대해 특정 동작 수행
	// 리스트관련 작업 수행 후 다른 동작 수행
	// 리스트 에러 방생시 정해진 다른 동작 수행
	static List<Apple> inventory = Arrays.asList(
			new Apple(80, Color.GREEN),
			new Apple(155, Color.GREEN),
			new Apple(120, Color.RED));

	public static void main(String... args){
		changeableRequirementsColor(Color.GREEN);
		changeableRequirementsWeight(150);

		// 위의 두 코드는 비슷한 내용이 반복됨
		// 인터페이스로 변화되는 내용을 추상화
		List<Apple> result = filterApples(inventory,new AppleCustomFilterPredicate());
		System.out.println("filter behavior parameter(interface)");
		System.out.println(result);

		// 익멱클래스 사용해서 간소화
		List<Apple> result2 = filterApples(inventory, new ApplePredicate() {
			public boolean test(Apple apple) {
				return Color.RED.equals(apple.getColor());
			}
		});
		System.out.println("Annonymous class");
		System.out.println(result2);

		//lambda 사용
		List<Apple> result3 = filterApples(inventory,(Apple apple)->Color.RED.equals(apple.getColor()));
		System.out.println("lambda");
		System.out.println(result3);

		// 더 추상화
		List<Integer> inventory2 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		List<Apple> fil1 = filter(inventory,(Apple a)-> Color.RED.equals(a.getColor()));
		List<Integer> fil2 = filter(inventory2,(Integer i)-> (i % 2) == 0);
		System.out.println("more abstraction");
		System.out.println(fil1);
		System.out.println(fil2);

		comparator();

		runnable();


		callable();
	}

	private static void callable() {
		//current task to thread pool and result to be Future
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> threadName = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Thread.currentThread().getName();
			}
		});

		Future<String> threadName2 = executor.submit(()-> Thread.currentThread().getName());
		System.out.println(threadName);
		System.out.println(threadName2);
	}

	private static void runnable() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello Thread1");
			}
		});
		//using lambda
		Thread t2 = new Thread(()-> System.out.println("Hello Thread2"));
		t1.start();
		t2.start();
	}

	private static void comparator() {
		System.out.println("using comparator");
		List<Apple> test = inventory;
		test.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return Integer.compare(o1.getWeight(), o2.getWeight());
			}
		});
		System.out.println(test);
		Collections.shuffle(test);
		//using lambda
		test.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
		//using comparator method
		test.sort(Comparator.comparingInt(Apple::getWeight));
		System.out.println(test);

	}

	private static void changeableRequirementsWeight(int w) {
		List<Apple> result = new ArrayList<>();
		inventory.forEach(apple ->{
			if(w<apple.getWeight()){
				result.add(apple);
			}}
		);
		System.out.println("filtering By Requirements Weight");
		System.out.println(result);
	}

	private static void changeableRequirementsColor(Color color) {
		List<Apple> result = new ArrayList<>();
		inventory.forEach(apple ->{
			if(color.equals(apple.getColor())){
				result.add(apple);
			}}
		);
		System.out.println("filtering By Requirements Color");
		System.out.println(result);
	}

	private static List<Apple> filterApples(List<Apple> apples,ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : apples){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	enum Color {
		RED,
		GREEN
	}

	public interface ApplePredicate{
		boolean test(Apple apple);
	}
	public static class AppleCustomFilterPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return Color.RED.equals(apple.getColor()) && apple.getWeight()>150;
		}
	}
	public interface Predicate<T> {
		boolean test(T t);
	}
	public static <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<>();
		for(T t : list){
			if(p.test(t)){
				result.add(t);
			}
		}
		return result;
	}

	public static class Apple {

		private int weight = 0;
		private Color color;

		public Apple(int weight, Color color) {
			this.weight = weight;
			this.color = color;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@SuppressWarnings("boxing")
		@Override
		public String toString() {
			return String.format("Apple{color=%s, weight=%d}", color, weight);
		}

	}
}