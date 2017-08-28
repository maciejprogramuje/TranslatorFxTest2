package pl.facebook.maciejprogramuje.translator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by m.szymczyk on 2017-08-28.
 */
public class Translator {
    private SortedMap<String, String> map = new TreeMap<>();

    public Translator() {
        System.out.println("Start");

        String outputFileName = "src\\main\\resources\\letters\\output.csv";
        try {
            BufferedReader bufferedReader = null;
            for (int i = 97; i <= 122; i++) {
                char ch = (char) i;
                String inputFileName = "src\\main\\resources\\letters\\" + ch + ".txt";

                bufferedReader = new BufferedReader(new FileReader(inputFileName));

                String testLine;
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
                        meaning = "(" + form + ") " + meaning;

                        if (!map.containsKey(word)) {
                            map.put(word, meaning);
                        } else {
                            map.put(word, map.get(word) + "| " + meaning);
                        }
                    }
                }
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName));

            for (String key : map.keySet()) {
                String line = key + " " + map.get(key) + "\n";
                bufferedWriter.append(line);
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findKey(String prefix) {
        if(prefix.isEmpty()) {
            return "";
        }

        prefix = prefix.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, String> e : map.entrySet()) {
            if (e.getKey().startsWith(prefix)) {
                /*stringBuilder.append(e.getKey());
                stringBuilder.append(": ");
                stringBuilder.append(e.getValue());
                stringBuilder.append("\n");*/
                stringBuilder.append(e);
                stringBuilder.append("\n");
                System.out.println(stringBuilder.toString());
            }
        }

        return stringBuilder.toString();
    }
}
