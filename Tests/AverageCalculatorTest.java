import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class AverageCalculatorTest {

    @Test // Тест проверяет корректную работу геттеров - возвращает нужные нам списки
    public void testListContainerGetters() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list2.add(3);

        ListContainer listContainer = new ListContainer(list1, list2);

        assertEquals(list1, listContainer.getList1());
        assertEquals(list2, listContainer.getList2());
    }

    @Test // Тест проверяет, что списки содержат только Integer элементы
    public void testListsContainIntegerElements() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);

        List<Integer> list2 = new ArrayList<>();
        list2.add(10);

        ListContainer listContainer = new ListContainer(list1, list2);

        assertTrue(validateIntegerElements(listContainer.getList1()));
        assertTrue(validateIntegerElements(listContainer.getList2()));
    }

    private boolean validateIntegerElements(List<Integer> list) {
        for (Integer num : list) {
            if (!(num instanceof Integer)) {
                return false;
            }
        }
        return true;
    }

    @Test // Тест проверяет взаимодействие функции вычисления среднего значения с пустым списком
    public void testCalculateAverageEmptyList() {
        AverageCalculator averageCalculator = new AverageCalculator();
        assertEquals(0, averageCalculator.calculateAverage(Arrays.asList()), 0);
    }

    @Test // Тест проверяет корректность вычислений функции вычисления среднего значения не пустого списка
    public void testCalculateAverage() {
        AverageCalculator averageCalculator = new AverageCalculator();
        assertEquals(3.5, averageCalculator.calculateAverage(Arrays.asList(2, 4, 3, 5)), 0);
    }

    @Test  // Тест проверяет правильность выведенного сообщения при условии, что первый список имеет большее среднее значение
    public void testCompareAveragesMessageFirstListGreater() {
        ListContainer listContainer = mock(ListContainer.class);
        when(listContainer.getList1()).thenReturn(List.of(1, 2, 3));
        when(listContainer.getList2()).thenReturn(List.of(1, 2));
    AverageCalculator averageCalculator = new AverageCalculator();
    averageCalculator = spy(averageCalculator);

    doReturn(2.0).when(averageCalculator).calculateAverage(List.of(1, 2, 3));
    doReturn(1.5).when(averageCalculator).calculateAverage(List.of(1, 2));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        averageCalculator.compareAverages(listContainer);

    assertEquals("Первый список имеет большее среднее значение\r\n", outContent.toString());
        System.setOut(System.out);
    }
    @Test  // Тест проверяет правильность выведенного сообщения при условии, что второй список имеет большее среднее значение
    public void testCompareAveragesMessageSecondListGreater() {
        ListContainer listContainer = mock(ListContainer.class);
        when(listContainer.getList1()).thenReturn(List.of(1, 2));
        when(listContainer.getList2()).thenReturn(List.of(1, 2, 3));
        AverageCalculator averageCalculator = new AverageCalculator();
        averageCalculator = spy(averageCalculator);

        doReturn(1.5).when(averageCalculator).calculateAverage(List.of(1, 2));
        doReturn(2.0).when(averageCalculator).calculateAverage(List.of(1, 2, 3));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        averageCalculator.compareAverages(listContainer);

        assertEquals("Второй список имеет большее среднее значение\r\n", outContent.toString());
        System.setOut(System.out);
    }
    @Test  // Тест проверяет правильность выведенного сообщения при условии, что средние значения списков равны
    public void testCompareAveragesMessageEqualAverages() {
        ListContainer listContainer = mock(ListContainer.class);
        when(listContainer.getList1()).thenReturn(List.of(1, 3, 1));
        when(listContainer.getList2()).thenReturn(List.of(1, 2, 2));
        AverageCalculator averageCalculator = new AverageCalculator();
        averageCalculator = spy(averageCalculator);

        doReturn(2.5).when(averageCalculator).calculateAverage(List.of(1, 3, 1));
        doReturn(2.5).when(averageCalculator).calculateAverage(List.of(1, 2, 2));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        averageCalculator.compareAverages(listContainer);

        assertEquals("Средние значения равны\r\n", outContent.toString());
        System.setOut(System.out);
    }

    private AverageCalculator averageCalculator;

    @Mock
    private ListContainer mockListContainer;

    @Before
    public void setup() {
        averageCalculator = new AverageCalculator();
        mockListContainer = mock(ListContainer.class);
    }

    @Test // Тест проверяет правильность выведенного сообщения если один из списков пустой
    public void testCompareAverages_OneListEmpty() {
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> nonEmptyList = Collections.singletonList(5);

        when(mockListContainer.getList1()).thenReturn(emptyList);
        when(mockListContainer.getList2()).thenReturn(nonEmptyList);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        averageCalculator.compareAverages(mockListContainer);

        assertEquals("Один из списков пустой. Невозможно вычислить среднее значение\r\n", outContent.toString());

        System.setOut(System.out);
    }
    @Test //Тест проверяет работу обоих классов в совместном использовании, сравнивая средние значения двух списков
    public void testCompareAverages_Integration() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        ListContainer listContainer = new ListContainer(list1, list2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        AverageCalculator averageCalculator = new AverageCalculator();
        averageCalculator.compareAverages(listContainer);

        assertEquals("Второй список имеет большее среднее значение\r\n", outContent.toString());

        System.setOut(System.out);
    }

        @Test
        public void testEndToEnd() {
            List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
            List<Integer> list2 = Arrays.asList(2, 4, 6, 8);

            ListContainer listContainer = new ListContainer(list1, list2);
            AverageCalculator averageCalculator = new AverageCalculator();

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            averageCalculator.compareAverages(listContainer);

            String expectedOutput = "Второй список имеет большее среднее значение\r\n";
            assertEquals(expectedOutput, outContent.toString());

            System.setOut(System.out);
        }
    }