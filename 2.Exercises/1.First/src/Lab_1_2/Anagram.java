package Lab_1_2;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public boolean isAnagram(String input) {
        if (input.equals(" ")) {
            return false;
        }

        if (input.length() == 2) {
            return false;
        }

        String firstWord = input.split(" ")[0];
        String secondWord = input.split(" ")[1];


        // none of them have letters
        if (checkSpecialCase(firstWord, secondWord)) {
            return true;
        }

        firstWord = getAllLettersLowerCase(firstWord);
        secondWord = getAllLettersLowerCase(secondWord);

        // if the two words have different amount of lower case letters
        if (firstWord.length() != secondWord.length()) {
            return false;
        }

        Map<Character, Integer> firstWordLetters = new HashMap<>();

        Character currentChar;
        for (int i = 0; i < firstWord.length(); i++) {
            currentChar = firstWord.charAt(i);

            if (!firstWordLetters.containsKey(currentChar)) {
                firstWordLetters.put(currentChar, 0);
            }

            firstWordLetters.put(currentChar, firstWordLetters.get(currentChar) + 1);
        }

        for (int i = 0; i < secondWord.length(); i++) {
            currentChar = secondWord.charAt(i);

            if (!firstWordLetters.containsKey(currentChar)) {
                return false;
            }

            firstWordLetters.put(currentChar, firstWordLetters.get(currentChar) - 1);
        }

        for (Integer numberOfRepetitions : firstWordLetters.values()) {
            if (numberOfRepetitions != 0) {
                return false;
            }
        }

        return true;
    }

    String getAllLettersLowerCase(String word) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (!(Character.isLetter(word.charAt(i)))) {
                continue;
            }

            answer.append(Character.toLowerCase(word.charAt(i)));
        }

        return answer.toString();
    }

    boolean checkSpecialCase(String firstWord, String secondWord) {
        for (int i = 0; i < firstWord.length(); i++) {
            if (Character.isLetter(firstWord.charAt(i))) {
                return false;
            }
        }

        for (int i = 0; i < secondWord.length(); i++) {
            if (Character.isLetter(secondWord.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
