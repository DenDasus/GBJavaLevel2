package Lesson_2.Task2Exceptions;

public class ExceptionTest {
    public static void main(String[] args) {
        String stringArr[][] = {{"1", "1.1", "1", "1"}, {"2", "2", "2", "2"}, {"3", "3", "3", "3"}, {"4", "4", "4", "4"}};
        try {
            System.out.println(new ExceptionTest().MyArraySum(stringArr));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int MyArraySum(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4 || arr[0].length != 4 || arr[1].length != 4 || arr[2].length != 4 || arr[3].length != 4) {
            throw new MyArraySizeException("Размер массива не равен 4х4!");
        }
        
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Недопустимые данные", i, j);
                }
            }
        }
        return sum;
    }
    
    class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }
    
    class MyArrayDataException extends Exception {
        int row;
        int col;
        
        MyArrayDataException(String message, int i, int j) {
            super(message + " в ячейке [" + i + "][" + j + "]!");
            this.row = i;
            this.col = j;
        }
        
        public int getRow() {
            return row;
        }
        
        public int getCol() {
            return col;
        }
    }
}
