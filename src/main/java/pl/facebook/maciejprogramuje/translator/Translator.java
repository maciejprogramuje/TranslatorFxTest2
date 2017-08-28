package pl.facebook.maciejprogramuje.translator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by m.szymczyk on 2017-08-28.
 */
public class Translator {
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    String testLine;
    Map<String, String> map = new HashMap<>();

    public Translator() {
        System.out.println("Start");

        String outputFileName = "src\\main\\resources\\letters\\output.csv";
        try {
            for (int i = 97; i <= 122; i++) {
                char ch = (char) i;
                String inputFileName = "src\\main\\resources\\letters\\" + ch + ".txt";

                bufferedReader = new BufferedReader(new FileReader(inputFileName));

                while ((testLine = bufferedReader.readLine()) != null) {
                    if (!testLine.isEmpty()) {
                        String word = testLine.substring(0, testLine.indexOf("(") - 1).toLowerCase();
                        String form = testLine.substring(testLine.indexOf("(") + 1, testLine.indexOf(")"));
                        if (form.isEmpty()) {
                            form = " ";
                        }
                        String meaning = testLine.substring(testLine.indexOf(")") + 1).trim();
                        if (meaning.isEmpty()) {
                            meaning = " ";
                        }
                        meaning = ": (" + form + ") " + meaning;

                        if (!map.containsKey(word)) {
                            map.put(word, meaning);
                        } else {
                            map.put(word, map.get(word) + "| " + meaning);
                        }
                    }
                }
            }

            bufferedWriter = new BufferedWriter(new FileWriter(outputFileName));

            for (String key : map.keySet()) {
                String line = key + " " + map.get(key) + "\n";
                //System.out.println(line);
                bufferedWriter.append(line);
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findKey(String key) {
        key = key.toLowerCase();

        if (map.keySet().contains(key)) {
            return map.get(key);
        } else {
            return "";
        }
    }
}
