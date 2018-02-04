package main;

import compiler.generated.Scanner;
import compiler.generated.Parser;
import util.Log;

import java.io.*;


public class App {
    public static Scanner scanner = null;

    public static void main(String[] args) {
        Long initialTime = System.currentTimeMillis();

        File input = new File("input/code.txt");
        String filePath = input.getAbsolutePath();

        try {
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            scanner = new Scanner(br);

            @SuppressWarnings("deprecation")
            Parser parser = new Parser(scanner);
            parser.parse();

            /**
            if (Parser.errors == 0) {
                Long totalTime = System.currentTimeMillis() - initialTime;
                Log.log("Tempo: " + totalTime + " ms");

                Log.log("Compilaçao efetuada com sucesso.");
            }
             **/
        } catch (Exception e) {
            Log.logErro(e.getMessage());
            e.printStackTrace();
        }

    }
}
