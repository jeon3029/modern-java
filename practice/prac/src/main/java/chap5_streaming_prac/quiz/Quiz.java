package chap5_streaming_prac.quiz;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;






public class Quiz {
  private static List<Transaction> transactions;
  
  private static void init(){
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    transactions = Arrays.asList(
      new Transaction(brian, 2011, 300),
      new Transaction(raoul, 2012, 1000),
      new Transaction(raoul, 2011, 300),
      new Transaction(mario, 2012, 710),
      new Transaction(mario, 2012, 700),
      new Transaction(alan, 2012, 950)
    );
  }
  public static void main(String[] args){
    init();
    System.out.println("hello");
    solve();
  }
  
  private static void solve() {
    //q1 : //2011 년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정렬  
    List<Transaction> q1 = transactions.stream()
      .filter(t -> t.getYear() == 2011)
      .sorted(Comparator.comparing(Transaction::getValue))
      .collect(toList());
    System.out.println("Q1 : " + q1);
    
    //q2 거래자가 근무하는 모든 도시를 중복없이 출력
    List<String> q2 = transactions.stream()
      .map(t->t.getTrader().getCity())
      .distinct()
      .collect(Collectors.toList());
    System.out.println("Q2 : " + q2);

    //q3 케임브리지 근무자 도든 거래자를 찾아 이름순으로 나열

    List<Trader> q3 = transactions.stream()
      .map(Transaction::getTrader)
      .filter(t->t.getCity().equals("Cambridge"))
      .distinct()
      .sorted(Comparator.comparing(Trader::getName))
      .collect(Collectors.toList());
    System.out.println("Q3 : " + q3);

    //q4 모든 거래자 이름을 알파베틱하게 정렬
    String q4 = transactions.stream()
      .map(Transaction::getTrader)
      .map(Trader::getName)
      .distinct()
      .sorted()
      .reduce(",", (n1,n2)-> n1 + n2);
    String q4_2 = transactions.stream()
      .map(Transaction::getTrader)
      .map(Trader::getName)
      .distinct()
      .sorted()
      .collect(joining(","));
    System.out.println("Q4 : "+ q4);
    System.out.println("Q4_2 : "+ q4_2);

    //q5 milan 거래자 찾기
    boolean q5 = transactions.stream()
      .anyMatch(t->t.getTrader().getCity().equals("Milan"));
    System.out.println("Q5 : " + q5);

    //q6 Cambridge 거주 거래자 모든 트랜잭션 값 추출
    transactions.stream()
    .filter(t->t.getTrader().getCity().equals("Cambridge"))
    .map(t->t.getValue())
    .forEach(e->System.out.println(e + ","));

    ///q7 트랙잭션 값 최댓값
    Optional<Integer> max = transactions.stream()
      .map(Transaction::getValue)
      .reduce(Integer::max);
    System.out.println("Q7 : "+max.orElse(0));

    //q8 최솟값
    Optional<Transaction> min = transactions.stream()
      // .reduce(Integer::min);
      .reduce((t1,t2)->t1.getValue() < t2.getValue()? t1:t2);
    System.out.println("Q8 : " + min.get());
  }
}
