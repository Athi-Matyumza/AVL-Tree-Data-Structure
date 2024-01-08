import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class AccessAVLApp {
    private static AVLTree<Student> students = new AVLTree<>();
    private static String AllStudentsIDs[] = new String[5000];
    private static int InsertOpCountStore[] = new int[5000]; //Stores all count values for insert
    private static boolean SubsetExec = false; //Variable to prevent the GUI from being opened everytime a dataset of values is searched
    private static boolean isTreeBuilt = false; //flag to check whether the tree has been created to prevent creation of the tree everytime a student is to be searched for.
    /**
     * Main function is controlled by the GUI and it is responsible for printing all students when the user indicates so
     * @param args parameter that contains input from the user in order to allow communication between user and program
     */
    public static void  main(String[] args) {
        BuildTree();
        isTreeBuilt = true;
        //To check whether there are any arguments passed from the user and if so, execute the corresponding code to the input
        printAllStudents();
    }

    /**
     * Builds the tree by inserting nodes and populates arrays (InsertOpCountStore & AllStudentsIDs)
     */
    public static void BuildTree()
    {
        Scanner fileIn = null;
        URL url = AccessAVLApp.class.getResource("oklist.txt");

        try {
            fileIn = new Scanner(new File(url.getPath()));

            int index = 0;

            while (fileIn.hasNext())
            {
                Student currentStudent = new Student(fileIn.next(), fileIn.next(), fileIn.next());
                students.insert(currentStudent);
                AllStudentsIDs[index] = currentStudent.getStudent_no();

                //To store the counts for each individual object inserted into the tree and resets count each time.
                InsertOpCountStore[index] = students.getOpCountInsert();
                students.resetCounts();
                index++;
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

    /**
     * Method to search in the tree whether a student ID is in the tree or not, then returns a status message back to the GUI.
     * @param studentID contains the value passed from the gui, in the student number text field.     * @param studentID
     * @return returns string list with the name, surname and status respectively
     */
    public static String[] printStudent(String studentID)
    {
        if (!isTreeBuilt)
        {
            BuildTree();
            isTreeBuilt = true;
        }

        Student student = new Student(studentID, "", "");

        BinaryTreeNode<Student> FindResult = students.find(student);

        if ((FindResult == null) && (SubsetExec == false)){
            String results[] = new String[3];
            results[0] = "N/A";
            results[1] = "N/A";
            results[2] = "Access denied!";

            return results;
        }

        else if (SubsetExec == false)
        {
            String results[] = new String[3];
            results[0] = FindResult.data.getName();
            results[1] = FindResult.data.getSurname();
            results[2] = "found";

            return results;
        }

        return null;
    }

    /**
     * Method to print all the student in the list in an ascending order of the student ID.
     */
    public static void printAllStudents ()
    {
        students.inOrder();
    }

    /**
     * Method to print in the console the corresponding count value for insert and find for each individual student.
     */
    public static void printNumOperations()
    {
        System.out.println("The number of comparisons is for find: " + students.getOpCountFind());
        System.out.println("The number of comparisons is for insert: " + students.getOpCountInsert());
    }

    /**
     * Method to calculate the count values for insert and find, then finds the best, worst and average case for each dataset of size n.
     */
    public static void subsets() {
        SubsetExec = true;
        int[] datasetFind, datasetInsert;
        int sumFind = 0, sumInsert = 0;

        for (int n = 500; n <= 5000; n += 500) {
            datasetFind = new int[n];
            datasetInsert = new int[n];

            int i;

            for (i = 0; i < n; i++) {
                AVLTree.resetCounts();
                printStudent(AllStudentsIDs[i]);
                datasetFind[i] = students.getOpCountFind();
                datasetInsert[i] = InsertOpCountStore[i];
                sumFind += students.getOpCountFind();
                sumInsert += datasetInsert[i];
            }

            //Sorting the arrays to find the worst and best case easier
            Arrays.sort(datasetFind);
            Arrays.sort(datasetInsert);

            //
            int bestFind = datasetFind[0];
            int worstFind = datasetFind[n - 1];
            float averageFind = (sumFind / n);

            int bestInsert = datasetInsert[0];
            int worstInsert = datasetInsert[n - 1];
            float averageInsert = (sumInsert / n);

            //Creating a textfile to print the Best, Worst & average case for each subset of n.
            String FileTitle = "AccessAVLApp_Dataset(n)_for_n_=_" + String.valueOf(n) + ".txt";

            FileWriter writer = null;
            try {
                writer = new FileWriter(new File(FileTitle));
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter printer = new PrintWriter(writer);

            //Printing to textfile Best, Worst & average case for each subset of n for the insert and find procedure then closing file.
            printer.println("Operations count values for find with a subset of " + n);
            printer.println("Best case: " + bestFind);
            printer.println("Average case: " + averageFind);
            printer.println("Worst case: " + worstFind);

            printer.println("\nOperations count values for insert with a subset of " + n);
            printer.println("Best case: " + bestInsert);
            printer.println("Average case: " + averageInsert);
            printer.println("Worst case: " + worstInsert);

            printer.close();

            //Clearing the arrays with count values and reseting the sum to 0.
            datasetFind = null;
            datasetInsert = null;
            sumFind = 0;
            sumInsert = 0;
        }

        SubsetExec = false; // returns variable to its original state after complete execution of the method
    }
}

