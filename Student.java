public class Student implements Comparable<Student> {
    private String Student_no;
    private String Name;
    private String Surname;

    /***
     * Method to create the student object
     * @param student_no contains the student ID
     * @param name contains the student name
     * @param surname conatins the student surname
     */
    public Student(String student_no, String name, String surname)
    {
            Student_no = student_no;
            Name = name;
            Surname = surname;
    }

    /**
     * Method to return the student ID
     * @return returns the string value of the student ID
     */
    public String getStudent_no()
    {
            return Student_no;
    }

    /**
     * Method to update the student ID
     * @param student_no contains the new student ID
     */
    public void setStudent_no(String student_no)
    {
            Student_no = student_no;
    }

    /**
     *  Method to return the student name
     * @return returns the string value of the name
     */
    public String getName()
    {
            return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname()
    {
        return Surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname)
    {
        Surname = surname;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "Student_no: " + Student_no + ' ' +
                    ", Name: " + Name + ' ' +
                    ", Surname: " + Surname + ' ';
    }

    /**
     * Method to compare two Student objects using their student ID
     * @param target contains the object to compare with
     * @return returns 0, negative if less than and positive integer if current object is greater than target object.
     */
    @Override
    public int compareTo(Student target)
    {
            return (this.getStudent_no().compareTo(target.getStudent_no()));
    }
}