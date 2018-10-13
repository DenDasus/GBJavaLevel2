package Lesson_3.Task_1;

import java.util.HashMap;
import java.util.Map;

public class DuplicateWords {
    public static void main(String[] args) {
        String[] arr = new String[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "Test_" + (int) (Math.random() * 10);
        }

        findDuplicates(arr);
    }

    public static void findDuplicates(String[] arr) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String s : arr) {
            Integer count = hashMap.get(s);
            hashMap.put(s, count == null ? 1 : count + 1);
        }

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("Слово: \"" + entry.getKey() + "\", количество повторений: " + entry.getValue());
        }
    }
}
