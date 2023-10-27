import com.service.Operations;
import com.service.Task1;
import com.service.Task2;
import com.util.UtilClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Программа чтения и обработки файлов XML определенной структуры");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Укажите, какой метод необходимо выполнить: ");
        System.out.println("1 - метод, который по переданному набору идентификаторов OBJECTID, выведет описание адресов (тип + название) на переданную дату. Путь к файлам «зашит» в коде приложения.");
        System.out.println("2 - метод, который выведет актуальные полные адреса, в которых встречается тип адреса = «проезд»");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Boolean repeatOperation = true;

        while (repeatOperation) {
            System.out.println("Введите номер метода: ");
            Integer methodNumber = Integer.parseInt(reader.readLine());

            if (methodNumber != 1 && methodNumber != 2) {
                System.out.println("Ошибка, должно быть введено число 1 или 2. Повторите попытку.");
            } else if (methodNumber == 1) {
                try {
                    List<String> objectIdList = readAndCreateObjectIdList(reader);
                    LocalDate targetDate = readAndCreateTargetDate(reader);
                    performMethod1(objectIdList, targetDate);
                    repeatOperation = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (methodNumber == 2) {
                try {
                    performMethod2();
                    repeatOperation = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Работа программы завершена.");
        reader.close();
    }

    public static void performMethod2() {
        Task2 operationTask2 = new Task2();
        operationTask2.process();
    }
    public static void performMethod1(List<String> objectIdList, LocalDate targetDate) {
        Task1 operationTask1 = new Task1();
        operationTask1.setObjectIdList(objectIdList);
        operationTask1.setTargetDate(targetDate);
        operationTask1.process();
    }
    static List<String> readAndCreateObjectIdList(BufferedReader reader) throws IOException {

        Boolean correctArr = false;
        List<String> objectIdList = new ArrayList<>();

        while (!correctArr) {
            System.out.println("Введите номера ObjectId, отделенные запятой. Пример ввода: 1422396, 1450759, 1449192, 1451562");

            String inputString = reader.readLine();

            objectIdList = createListFromStrings(inputString);

            if (objectIdList.size() > 0) correctArr = true;
            else System.out.println("Введена некорректная строка. Попробуйте еще раз.");
        }

        return objectIdList;
    }

    static LocalDate readAndCreateTargetDate(BufferedReader reader) throws IOException {

        Boolean correctDate = false;
        LocalDate date = null;
        while (!correctDate) {
            System.out.println("Введите дату в формате yyyy-MM-dd. Пример ввода: 2010-01-01");
            String dateStr = reader.readLine();
            try {
                date = UtilClass.parseDate(dateStr);
                correctDate = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    static List<String> createListFromStrings (String inputString) {

        List <String> objectIdList  = new ArrayList<>();

        String[] values = inputString.split("\\s*,\\s*");

        for (String value : values) {
            System.out.println("Считано значение: " + value);
            objectIdList.add(value.trim());
        }
        return objectIdList;
    }

}
