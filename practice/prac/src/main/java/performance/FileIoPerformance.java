package performance;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileIoPerformance {
  
  public static void main(String[] args) throws FileNotFoundException, IOException, RuntimeException{ 
    try(FileOutputStream out = new FileOutputStream("test.dat")) {
      IntStream.range(1, 100).forEach(i -> {
        try {
          out.write((i + "\n").getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      } );
    }
    
    System.out.println("일반적인 포문");
    String keyword = "5";
    int howManyMatchedLines = 2; 
    String fileName = "test.dat";
    BufferedReader reader = null;
    long numLines = 0;
    try {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
      String line = null;
      int matchedLines = 0;
      while ((line = reader.readLine()) != null) {
        if (line.indexOf(keyword) >= 0) {
          System.out.println("매칭된 라인: " + line);
          matchedLines++;
          if (matchedLines >= howManyMatchedLines) {
            break;
          }
        }
        numLines++;
      }
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
    System.out.println("읽은 라인 : " + numLines);
    System.out.println();
    System.out.println();
    System.out.println("Stream 사용");
    
    final AtomicInteger howManyMatchedLines2 = new AtomicInteger(2);
    final AtomicLong lineNum2 = new AtomicLong(1);
    final AtomicInteger matchedLines2 = new AtomicInteger(0);
    try (Stream<String> stream = Files.lines(Paths.get("test.dat"))) {
      stream.peek(line -> lineNum2.incrementAndGet())
      .filter(line -> line.indexOf(keyword) >= 0)
      .forEach(line -> {
        if (matchedLines2.get() < howManyMatchedLines2.get()) {
          System.out.println("매칭된 라인: " + line);
          if (matchedLines2.incrementAndGet() >= howManyMatchedLines2.get()) {
            return;
          }
        }
      });
    }
    System.out.println("읽은 라인 수: " + lineNum2.get());
    
    System.out.println();
    System.out.println("throw 로도 가능은 함 ");
    final AtomicInteger howManyMatchedLines3 = new AtomicInteger(2);

    final AtomicLong lineNum3 = new AtomicLong(1);
    final AtomicInteger matchedLines3 = new AtomicInteger(0);
    
    try (Stream<String> stream = Files.lines(Paths.get("test.dat"))) {
      stream.peek(line -> lineNum3.incrementAndGet())
      .filter(line -> line.indexOf(keyword) >= 0)
      .forEach(line -> {
        if (matchedLines3.get() < howManyMatchedLines3.get()) {
          System.out.println("매칭된 라인: " + line);
          if (matchedLines3.incrementAndGet() >= howManyMatchedLines3.get()) {
            throw new BreakException();
          }
        }
      });
    } catch (BreakException e) {}
    System.out.println("읽은 라인 수: " + lineNum3.get());
  }
  static class  BreakException extends RuntimeException {}
  
}

