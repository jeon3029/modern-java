package chap3_lambda;

public class Lambda {
	public static void main(String[] args) {
		System.out.println("Lambda Examples");

		//save lambda
		Runnable r1 = ()-> System.out.println("Hello World 1");

		//anonymous class
		Runnable r2 = new Runnable() {
		 @Override
		 public void run() {
			 System.out.println("Hello World 2");
		 }
		};
		process(r1);
		process(r2);

		//직접 전달
		process(()-> System.out.println("Hello World 3"));

		r1.run();
		r2.run();
	}
	public static void process(Runnable r) {
		r.run();
	}
}
