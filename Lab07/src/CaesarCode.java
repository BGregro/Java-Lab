public class CaesarCode {
    public static String caesarCode(String input, char offset) {
        input = input.toUpperCase();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String output = "";
        int offsetIdx = abc.indexOf(offset);
        int iterIdx;

        for (int i = 0; i < input.length(); ++i) {
            iterIdx = abc.indexOf(input.charAt(i));
            if (iterIdx + offsetIdx > 25) {
                output += abc.charAt((iterIdx+offsetIdx)-26);
            } else {
                output += abc.charAt(iterIdx + offsetIdx);
            }
        }
        return output;
    }

    public static String caesarDecode(String input, char offset) {
        input = input.toUpperCase();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String output = "";
        int offsetIdx = abc.indexOf(offset);
        int iterIdx;

        for (int i = 0; i < input.length(); ++i) {
            iterIdx = abc.indexOf(input.charAt(i));
            if (iterIdx - offsetIdx < 0) {
                output += abc.charAt(26+(iterIdx - offsetIdx));
            } else {
                output += abc.charAt(iterIdx - offsetIdx);
            }
        }
        return output;
    }
}
