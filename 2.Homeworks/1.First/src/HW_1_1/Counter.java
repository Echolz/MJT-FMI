package HW_1_1;

public class Counter {
    public int countTriples(String str) {

        if (str.length() == 0) {
            return 0;
        }

        int answer = 0;

        int currentSequence = 0;
        char currentChar = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if (currentChar == str.charAt(i)) {
                currentSequence ++;
            } else {
                currentChar = str.charAt(i);
                currentSequence = 0;
            }

            if (currentSequence >= 2) {
                answer ++;
            }
        }


        return answer;
    }
}
