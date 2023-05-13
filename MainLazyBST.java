import java.io.*;
import java.util.Scanner;

public class MainLazyBST {
    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1];

        LazyBinarySearchTree tree = new LazyBinarySearchTree();

        File inputFile = new File(inputPath);
        File outputFile = new File (outputPath);

        Scanner input = new Scanner(inputFile);
        FileWriter output = new FileWriter(outputFile);

        while (input.hasNextLine()){
            String line = input.nextLine();
            String[] operation = line.split(":");

            if (operation[0].equals("Insert")){
                if (operation.length == 1)
                    output.write("Error in line: " + operation[0] + "\n");
                else {
                    try {
                        output.write(tree.insert(Integer.parseInt(operation[1])) + "\n");
                    }
                    catch (IllegalArgumentException exception) {
                        output.write("Error in insert: IllegalArgumentException raised\n");
                    }

                }
            }

            else if (operation[0].equals("Delete")){
                if (operation.length == 1)
                    output.write("Error in line: " + operation[0] + "\n");
                else {
                    try {
                        output.write(tree.delete(Integer.parseInt(operation[1])) + "\n");
                    }
                    catch (IllegalArgumentException exception) {
                        output.write("Error in delete: IllegalArgumentException raised\n");

                    }

                }
            }

            else if (operation[0].equals("FindMax")){
                    output.write(tree.findMax() + "\n");
            }

            else if (operation[0].equals("FindMin")){
                    output.write(tree.findMin() + "\n");
            }

            else if (operation[0].equals("Contains")){
                if (operation.length == 1)
                    output.write("Error in line: " + operation[0] + "\n");
                else {
                    try {
                        output.write(tree.contains(Integer.parseInt(operation[1])) + "\n");
                    }
                    catch (IllegalArgumentException exception) {
                        output.write("Error in contains: IllegalArgumentException raised\n");
                    }

                }
            }

            else if (operation[0].equals("PrintTree")){
                output.write(tree + "\n");
            }

            else if (operation[0].equals("Height")){
                output.write(tree.height() + "\n");
            }

            else if (operation[0].equals("Size")){
                output.write(tree.size() + "\n");
            }

            else {
                output.write("Error in line: " + operation[0] + "\n");
            }
        }
        output.close();
    }
}