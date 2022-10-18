package chap3_lambda;


@FunctionalInterface
public interface MyInterface {
  void process(Runnable r);
}

//이와같은 형태가 함수형 인터페이스 임