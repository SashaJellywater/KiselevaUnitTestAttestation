import java.util.List;

public class AverageCalculator {
        public double calculateAverage(List<Integer> list) {
            double sum = 0;
            for (int num : list) {
                sum += num;
            }
            return list.isEmpty() ? 0 : sum / list.size();
        }

        public void compareAverages(ListContainer listContainer) {
            List<Integer> list1 = listContainer.getList1();
            List<Integer> list2 = listContainer.getList2();

            if (list1.isEmpty() || list2.isEmpty()) {
                System.out.println("Один из списков пустой. Невозможно вычислить среднее значение");
                return;
            }

            double averageList1 = calculateAverage(list1);
            double averageList2 = calculateAverage(list2);

            if (averageList1 > averageList2) {
                System.out.println("Первый список имеет большее среднее значение");
            } else if (averageList1 < averageList2) {
                System.out.println("Второй список имеет большее среднее значение");
            } else {
                System.out.println("Средние значения равны");
            }
        }
    }