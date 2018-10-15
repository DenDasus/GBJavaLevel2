package Lesson_3.Task_2;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();
    
    public void add(String name, String phoneNumber) {
        ArrayList<String> arr = phoneBook.get(name);
        if(arr != null) {
            for (String number: arr) {
                if(number.equals(phoneNumber)) {
                    return;
                }
            }
        } else {
            arr = new ArrayList<>();
        }
        arr.add(phoneNumber);
        phoneBook.put(name, arr);
    }
    
    public ArrayList<String> get(String name) {
            return phoneBook.get(name);
    }
}
