import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessAVLAppGUI  extends AccessAVLApp implements ActionListener {
    private JFrame frame;
    private JPanel pnlSearchStudent, pnlPrintOption;
    private JLabel lblName, lblStudentNum, lblSurname, lblPrintOption, lblOutputName, lblOutputSurname,
            lblOutputStudentID, lblStatus;
    private JTextField fldName, fldStudentNum, fldSurname, fldPrintOption;
    private JButton DisplayButton, PrintButton;
    private int counter;

    /**
     * Constructor to create the GUI
     */
    public AccessAVLAppGUI() {
        //Creating the components
        frame = new JFrame();
        pnlSearchStudent = new JPanel();
        pnlPrintOption = new JPanel();
        lblPrintOption = new JLabel("Would you like to print all students [Yes / No]?");
        lblName = new JLabel("Name (Optional)");
        lblSurname = new JLabel("Surname (Optional)");
        lblStudentNum = new JLabel("Student number");
        fldPrintOption = new JTextField();
        fldName = new JTextField();
        fldSurname = new JTextField();
        fldStudentNum = new JTextField();
        DisplayButton = new JButton("Done");
        PrintButton = new JButton("Done");

        lblOutputName = new JLabel("");
        lblOutputSurname = new JLabel("");
        lblOutputStudentID = new JLabel("");
        lblStatus = new JLabel("");

        //Setting the attributes of the components
        frame.setTitle("Welcome to the AVLTree application");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        pnlSearchStudent.setLayout(null);
        pnlPrintOption.setLayout(null);
        lblPrintOption.setBounds(10, 20, 350, 25);
        fldPrintOption.setBounds(370, 20, 100, 25);
        lblName.setBounds(10, 20, 170, 25);
        fldName.setBounds(200, 20, 165, 25);
        lblSurname.setBounds(10, 50, 170, 25);
        fldSurname.setBounds(200, 50, 165, 25);
        lblStudentNum.setBounds(10, 80, 140, 25);
        fldStudentNum.setBounds(200, 80, 165, 25);
        DisplayButton.setBounds(200, 110, 90, 25);


        lblOutputName.setBounds(10, 140, 250, 25);
        lblOutputSurname.setBounds(10, 170, 250, 25);
        lblOutputStudentID.setBounds(10, 200, 250, 25);
        lblStatus.setBounds(10, 230, 250, 25);

        //Adding the components to the panel and frame
        frame.add(pnlSearchStudent);
        frame.add(pnlPrintOption);
        frame.setVisible(true);
        pnlPrintOption.add(fldPrintOption);
        pnlPrintOption.add(lblPrintOption);

        pnlPrintOption.add(lblName);
        pnlPrintOption.add(fldName);
        pnlPrintOption.add(lblSurname);
        pnlPrintOption.add(fldSurname);
        pnlPrintOption.add(lblStudentNum);
        pnlPrintOption.add(fldStudentNum);
        pnlPrintOption.add(lblOutputName);
        pnlPrintOption.add(lblOutputSurname);
        pnlPrintOption.add(lblOutputStudentID);
        pnlPrintOption.add(lblStatus);

        pnlSearchStudent.add(DisplayButton);
        pnlPrintOption.add(DisplayButton);
        //

        //Set component visibility to false for components meant to appear only after when the search option is selected
        lblStatus.setVisible(false);
        lblOutputStudentID.setVisible(false);
        lblOutputName.setVisible(false);
        lblOutputSurname.setVisible(false);
        fldStudentNum.setVisible(false);
        fldSurname.setVisible(false);
        fldName.setVisible(false);
        lblName.setVisible(false);
        lblSurname.setVisible(false);
        lblStudentNum.setVisible(false);
        //Perform actions with the components
        DisplayButton.addActionListener(this);

    }

    /**
     * Method to return the text inputted when the program starts.
     * @return returns either "Yes" or "No".
     */
    public String getResponse()
    {
        return fldPrintOption.getText();
    }

    /**
     * Method to return the student number of the student ID to be used for searching in the tree.
     * @return returns the input input in the Student number Text-field.
     */
    public String getStudentNumber()
    {
        return fldStudentNum.getText();
    }

    /**
     * Method to change the layout of the panel by making other components visible for appropriate capture.
     */
    public void changePanel()
    {
        lblPrintOption.setVisible(false);
        fldPrintOption.setVisible(false);
        frame.setSize(400, 500);

        fldStudentNum.setVisible(true);
        fldSurname.setVisible(true);
        fldName.setVisible(true);
        lblName.setVisible(true);
        lblSurname.setVisible(true);
        lblStudentNum.setVisible(true);
    }

    /**
     * Method to return the search results from searching for a student in the tree
     * @param results contains string list with the name, surname and status respectively
     */
    public void setSearchResult(String[] results)
    {
        frame.setSize(400, 500);

        lblOutputName.setText("Name: " + results[0]);
        lblOutputSurname.setText("Surname: " + results[1]);
        lblOutputStudentID.setText("Student ID: " + fldStudentNum.getText());
        lblStatus.setText("Login status: " + results[2]);

        lblStatus.setVisible(true);
        lblOutputStudentID.setVisible(true);
        lblOutputName.setVisible(true);
        lblOutputSurname.setVisible(true);
    }

    /**
     * Method to execute an action to print all students or search for a student when the button is clicked
     * @param actionEvent contains action event from the button
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (fldPrintOption.getText().compareTo("Yes") == 0 || ((fldPrintOption.getText()).compareTo("yes") == 0))
        {
            String response[] = {getResponse()  };
            AccessAVLApp.main(response);
        }
        else if (((fldPrintOption.getText()).compareTo("No") == 0) || ((fldPrintOption.getText()).compareTo("no") == 0))
        {
            changePanel();
            setSearchResult(printStudent(getStudentNumber()));
        }

    }
}
