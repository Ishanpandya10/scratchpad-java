package scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * Create a histogram for a given range of Marks
 * Buckets are divided into:
 * 1.Below Average(0,35),
 * 2.Needs Improvement (36, 50),
 * 3.Good (51, 60),
 * 4.Excellent (60,70),
 * 5.Outstanding (71, 100)
 * Final result should have grades and count of marks
 */
public class Histogram {
    public static void main(String[] args) {
        List<Bucket> bucketList = Bucket.getBuckets();
        System.out.println(bucketList);

        List<Integer> inputInts = IntStream.generate(() -> new Random().nextInt(100)).limit(100).boxed()
                .toList();
        System.out.println(inputInts);

        CustomHistogramCollector customCollector = new CustomHistogramCollector();

        HashMap<Bucket, List<Integer>> map = inputInts.stream()
                .collect(customCollector);

        map.forEach((key, value) -> System.out.println(key.getGrade() + " : " + value.size()));

    }
}

class CustomHistogramCollector implements Collector<Integer, HashMap<Bucket, List<Integer>>, HashMap<Bucket, List<Integer>>> {

    @Override
    public Supplier<HashMap<Bucket, List<Integer>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<HashMap<Bucket, List<Integer>>, Integer> accumulator() {
        return (T, marks) -> {
            Bucket bucket = Bucket.getBucketFromInt(marks);
            if (T.containsKey(bucket)) {
                T.get(bucket).add(marks);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(marks);
                T.put(bucket, list);
            }
        };
    }

    @Override
    public BinaryOperator<HashMap<Bucket, List<Integer>>> combiner() {
        return (op, op1) -> op;
    }

    @Override
    public Function<HashMap<Bucket, List<Integer>>, HashMap<Bucket, List<Integer>>> finisher() {
        return (val1) -> val1;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }
}

class Bucket {
    private int fromRange;
    private int toRange;
    private String grade;

    static Bucket getBucketFromInt(int marks) {
        List<Bucket> bucketList = getBuckets();
        return bucketList.stream()
                .filter(bucket -> (marks >= bucket.getFromRange() && marks <= bucket.getToRange()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Bucket not found for marks: " + marks));
    }

    static List<Bucket> getBuckets() {
        List<Bucket> bucketList = new ArrayList<>();

        Bucket bucket = new Bucket();
        bucket.setFromRange(0);
        bucket.setToRange(35);
        bucket.setGrade("Below Average");
        bucketList.add(bucket);

        bucket = new Bucket();
        bucket.setFromRange(36);
        bucket.setToRange(50);
        bucket.setGrade("Needs Improvement");
        bucketList.add(bucket);

        bucket = new Bucket();
        bucket.setFromRange(51);
        bucket.setToRange(60);
        bucket.setGrade("Good");
        bucketList.add(bucket);

        bucket = new Bucket();
        bucket.setFromRange(60);
        bucket.setToRange(70);
        bucket.setGrade("Excellent");
        bucketList.add(bucket);

        bucket = new Bucket();
        bucket.setFromRange(71);
        bucket.setToRange(100);
        bucket.setGrade("Outstanding");
        bucketList.add(bucket);
        return bucketList;
    }

    public int getFromRange() {
        return fromRange;
    }

    public void setFromRange(int fromRange) {
        this.fromRange = fromRange;
    }

    public int getToRange() {
        return toRange;
    }

    public void setToRange(int toRange) {
        this.toRange = toRange;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "fromRange=" + fromRange +
                ", toRange=" + toRange +
                ", grade='" + grade + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return fromRange == bucket.fromRange && toRange == bucket.toRange && Objects.equals(grade, bucket.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromRange, toRange, grade);
    }


}



