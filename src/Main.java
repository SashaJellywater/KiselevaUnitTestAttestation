import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(10);
        list1.add(15);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(9);
        list2.add(13);


        ListContainer listContainer = new ListContainer(list1, list2);
        AverageCalculator calculator = new AverageCalculator();

        calculator.compareAverages(listContainer);
    }
}