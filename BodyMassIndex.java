import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BodyMassIndex {
    public static void main(String[] args) {
        String inputString = "118 2.05\n" +
                "106 1.77\n" +
                "87 1.83\n" +
                "45 1.12\n" +
                "70 1.87\n" +
                "54 1.57\n" +
                "105 1.76\n" +
                "50 1.96\n" +
                "114 1.76\n" +
                "72 2.45\n" +
                "53 2.10\n" +
                "66 2.25\n" +
                "54 1.50\n" +
                "95 1.62\n" +
                "86 1.72\n" +
                "62 1.57\n" +
                "65 2.24\n" +
                "72 1.43\n" +
                "93 2.01\n" +
                "109 3.01\n" +
                "106 2.97\n" +
                "77 1.69\n" +
                "114 2.09\n" +
                "98 1.72\n" +
                "85 2.46\n" +
                "113 1.94\n" +
                "53 1.77\n" +
                "106 2.30";

        Pattern pattern = Pattern.compile("^*\\d{2,3}\\s\\d[.]\\d{2}");
        Matcher matcher = pattern.matcher(inputString);

        String resultString = "";
        while (matcher.find()) {
            String string = matcher.group();
            String[] parts = string.split(" ");
            double result = Double.parseDouble(parts[0]) / Math.pow(Double.parseDouble(parts[1]), 2);

            if(result < 18.5) {
                resultString += "under ";
            } else if(18.5 <= result && result < 25) {
                resultString += "normal ";
            } else if(25 <= result && result < 30) {
                resultString += "over ";
            } else if(30 <= result) {
                resultString += "obese ";
            }
        }
        System.out.println(resultString);
    }
}
