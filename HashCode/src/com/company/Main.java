package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static String[] files = {"a_example.in", "b_small.in", "c_medium.in", "d_quite_big.in", "e_also_big.in"};
    //    static String[] files = {"a_example.in", "b_small.in"};
    static String[] output = {"output1.txt", "output2.txt", "output3.txt", "output4.txt", "output5.txt"};

    public static void main(String[] args) {

        for (int i = 0; i < files.length; i++) {
            int maximum = 0;
            int number_of_slices = 0;
            int[] splitedIntegers = {};

            try {
                File myObj = new File(files[i]);
                Scanner myReader = new Scanner(myObj);

                int LineNumber = 1;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] splited = data.split("\\s+");
                    if (LineNumber == 1) {
                        maximum = Integer.parseInt(splited[0]);
                        number_of_slices = Integer.parseInt(splited[1]);
                    } else {
                        splitedIntegers = Arrays.stream(splited).mapToInt(Integer::parseInt).toArray();
                    }
                    LineNumber++;
                }

                receiveInputs(maximum, number_of_slices, splitedIntegers, output[i]);
                myReader.close();
            } catch (Exception ignored) {
            }
        }
    }


    private static void receiveInputs(int maximumSum, int number_of_slices, int[] slices, String outputFileName) {
        int sum = 0;
        ArrayList<Integer> results = new ArrayList<>();
        int lastcheckedElement = 0;
        for (int i = (number_of_slices - 1); i >= 0; i--) {
            sum += slices[i];
            if (sum < maximumSum) {
                results.add(i);
                lastcheckedElement = i;
            } else {
                sum -= slices[i];

//                break;
            }

        }
//        for (int i = 0; i < lastcheckedElement; i++) {
//            sum += slices[i];
//            if (sum < maximumSum) {
//                results.add(i);
//            } else {
//                break;
//            }
//        }


        Collections.sort(results);


        String output = results.size() + "\n";
        StringBuilder slicess = new StringBuilder();

        for (int i = 0; i < results.size(); i++) {
            slicess.append(results.get(i)).append(" ");
        }
        slicess.toString().trim();

        output += slicess;
        System.out.println(output);
        writeUsingOutputStream(output, outputFileName);


    }

    private static void writeUsingOutputStream(String data, String outPutFileName) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(outPutFileName));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
