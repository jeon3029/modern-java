# moden java study

## 공부하고 싶은 내용

- 눈으로 보기 좋은것이 좋은 코드이다? 정도의 느낌만 있음
  - 내부적으로 어떻게 컴파일되고 최적화되는지 최소치로는 알아야 할 필요성
  - 모던 자바 스타일에 익숙하지 않음. 람다표현식, 스트림, 함수형 등 필요할 때 잘 쓰자

- 여러가지 이유로 인해 함수형 프로그래밍이 언제나 더 나은 답은 아니다.
  - 언제 함수형 프로그래밍을 쓰는 것이 좋은지, 어떨때 객체지향/절차형 방식을 고수하는 것이 좋은지 직관적으로 이해할 수 있어야 한다

## 자바에서의 일급 객체?

1급 객체의 충족 조건 세가지

1. 변수나 데이터에 할당 할 수 있어야 한다.
2. 객체의 인자로 넘길 수 있어야 한다.
3. 객체의 리턴값으로 리턴 할 수 있어야 한다.

- JAVA에서는 위의 조건에 해당되지 않는다
- 하지만 java의 Lambda는 `메서드가 1개만 존재하는 인터페이스/클래스`를 통해, 마치 함수를 전달하는 것처럼 함
  - 함수를 1급 객체로 취급하지 않는 java의 단점을 어느정도 해결한 것

## foreach vs for loop

- 중간에 break 하는 경우 for-loop 는 중단, stream 은 전체 수행
- file io 예시(code)

## 참고 자료

[for-loop-를-Stream-forEach-로-바꾸지-말아야-할-3가지-이유/](http://homoefficio.github.io/2016/06/26/for-loop-%EB%A5%BC-Stream-forEach-%EB%A1%9C-%EB%B0%94%EA%BE%B8%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-3%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0/)
[java8-stream은-loop가-아니다](https://www.popit.kr/java8-stream%EC%9D%80-loop%EA%B0%80-%EC%95%84%EB%8B%88%EB%8B%A4/)