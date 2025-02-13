import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//          Префиксы
        String filePrefix = "";
        String pathOut = "";

        boolean  rewrite = true;
        boolean statisics = false;
        boolean fullStatistics = false;

        int countInt = 0;
        int countFloat = 0;
        int countStr = 0;

        long variable;
        long minInt = Long.MAX_VALUE;
        long maxInt = Long.MIN_VALUE;
        long sumInt = 0;

        float varFloat;
        float minFloat = Float.MAX_VALUE;
        float maxFloat = Float.MIN_VALUE;
        float sumFloat = 0;

        int minLengthStr = Integer.MAX_VALUE;
        int maxLengthStr = Integer.MIN_VALUE;
        int varString ;

        ArrayList<String> filePath = new ArrayList<>();
        if (args.length == 0) {
            System.out.println("В командной строке не переданы аргументы");
            return;
        }
        // Проверка аргументов
        for (int i = 0; i < args.length; i++) {
            if (args[i].endsWith(".txt")) {
                filePath.add(args[i]);
            } else if (args[i].equals("-s")) {
                statisics = true;
            } else if (args[i].equals("-f")) {
                fullStatistics = true;
            } else if (args[i].equals("-p")) {
                filePrefix = args[++i];
            } else if (args[i].equals("-a")) {
                rewrite = false;
            } else if (args[i].equals("-o")) {
                pathOut = args[++i];
                if (!pathOut.endsWith(File.separator)){
                    pathOut += File.separator;
                }
            }
        }

//      Создаем директорию в которую записываем файлы, если такой директории не было
        if (!pathOut.equals("")){
            File  outputDir = new File(pathOut);
            if (!outputDir.exists()){
                if (outputDir.mkdirs()){
                    System.out.println("Создана директория: " + pathOut);
                }else {
                    System.err.println("Директория не создана: " + pathOut);
                    return;
                }
            }
        }

//          Пути выходных файлов

        String filePathInt = pathOut + filePrefix + "integers.txt";
        String filePathFloat = pathOut + filePrefix + "floats.txt";
        String filePathStr = pathOut + filePrefix + "strings.txt";

        File fileOutInt = new File(filePathInt);
        File fileOutFloat = new File(filePathFloat);
        File fileOutStr = new File(filePathStr);


        if (rewrite){
            if (fileOutInt.exists()){
                try (FileWriter writer = new FileWriter(fileOutInt)){
                    writer.write("");
                } catch (IOException e) {
                    System.err.println("Ошибка при очистке файла: " + e.getMessage());
                }
            }
            if (fileOutFloat.exists()){
                try (FileWriter writer = new FileWriter(fileOutFloat)){
                    writer.write("");
                } catch (IOException e) {
                    System.err.println("Ошибка при очистке файла: " + e.getMessage());
                }
            }
            if (fileOutStr.exists()){
                try (FileWriter writer = new FileWriter(fileOutStr)){
                    writer.write("");
                } catch (IOException e) {
                    System.err.println("Ошибка при очистке файла: " + e.getMessage());
                }
            }
        }

        List<BufferedReader> readers = new ArrayList<>();
        List<String> lines;

        try {
            // Инициализируем BufferedReaders для файлов переданных в args
            for (String fP : filePath) {
//                System.out.println("Current directory " + System.getProperty("user.dir"));
                readers.add(new BufferedReader(new FileReader(fP)));
            }

            boolean moreLines = true;
            while (moreLines) {
                moreLines = false;
                lines = new ArrayList<>();

                //Читаем одну строчку из каждого файла и добавляем в  List 'lines'
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        lines.add(line);
                        moreLines = true;
                    }
                }
                for (String line : lines) {
                    if (isInteger(line)) {
                        writeToFile(filePathInt, line);
                        countInt++;
                        if (minInt > (variable  = Long.parseLong(line))){
                            minInt = variable;
                        }if (maxInt < (variable  = Long.parseLong(line))){
                            maxInt = variable;
                        }
                        sumInt += variable;
                    } else if (isFloat(line)) {
                        writeToFile(filePathFloat, line);
                        countFloat++;
                        if (minFloat > (varFloat = Float.parseFloat(line))){
                            minFloat = varFloat;
                        }
                        if (maxFloat < (varFloat = Float.parseFloat(line))){
                            maxFloat = varFloat;
                        }
                        sumFloat += varFloat;
                    } else {
                        writeToFile(filePathStr, line);
                        if (minLengthStr > (varString = line.length())){
                            minLengthStr = varString;
                        }
                        if (maxLengthStr < (varString = line.length())){
                            maxLengthStr = varString;
                        }
                        countStr++;
                    }
                }
            }
            for (BufferedReader reader : readers) {
                reader.close();
            }
            if (statisics == true){
                System.out.println("Количество элементов выгруженные в файл:");
                if (countInt != 0){
                    System.out.println("Целые числа - " + countInt);
                }
                if (countFloat != 0){
                    System.out.println("Вещественные числа - " + countFloat);
                }
                if (countStr != 0){
                    System.out.println("Строки - " + countStr);
                }
                System.out.println("------------------------------------------");
            }
            if (fullStatistics == true){
                System.out.println("-------------- Полная статистика ---------------");
                System.out.println("Количество целых чисел - " + countInt);
                System.out.println("Количество вещественных чисел - " + countFloat);
                System.out.println("Количество строк - " + countStr);
                if (countInt != 0){
                    System.out.println("-------------- Целые числа ----------------------");
                    System.out.println("Минимальное целое - " + minInt);
                    System.out.println("Максимальное целое - " + maxInt);
                    System.out.println("Сумма целых чисел - " + sumInt);
                    System.out.println("Среднее арифметическое - " + sumInt / countInt);
                }
                if (countFloat != 0){
                    System.out.println("-------- Вещественные числа ---------------------");
                    System.out.println("Минимальное вещественное - " + minFloat);
                    System.out.println("Максимальное вещественное - " + maxFloat);
                    System.out.println("Сумма вещественных чисел - " + sumFloat);
                    System.out.println("Среднее арифметическое вещественных - " + sumFloat / countFloat);
                }
                if (countStr != 0){
                    System.out.println("----------------- Строки ------------------------");
                    System.out.println("Длина самой короткой строки - " + minLengthStr);
                    System.out.println("Длина самой длинной строки - " + maxLengthStr);
                }
                System.out.println("-------------------------------------------------");
            }
            if (pathOut == ""){
                System.out.println("Выгрузка данных завершена в директорию - " + System.getProperty("user.dir"));
            }else {
                System.out.println("Выгрузка данных завершена в директорию - " + pathOut);
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + System.getProperty("user.dir") +File.separator + e.getMessage());
        }

    }

    public static void writeToFile(String filePath, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line + "\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + filePath);
        }
    }

    public static boolean isInteger(String str) {
        try {
            Long.parseLong(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}