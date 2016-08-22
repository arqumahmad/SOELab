import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

public class Home {

    private JFrame frame;
    private JPanel panel;

    ArrayList<Subject> subjects;
    ArrayList<Student> students;
    private JComboBox<String> subjectChoice;

    public Home() {
        subjects = new ArrayList<Subject>();
        subjects.add(new Subject("ISOE 532C"));
        subjects.add(new Subject("IGVC 532C"));
        subjects.add(new Subject("ICNW 532C"));
        subjects.add(new Subject("IAIN 532C"));
        subjects.add(new Subject("MPOE 530C"));

        students = new ArrayList<Student>();
        students.add(new Student("StudentA"));
        students.add(new Student("StudentB"));
        students.add(new Student("StudentC"));


        for (Subject subject : subjects) {
            for (Student student : students) {
                student.addSubjectsTaken(subject);
                subject.studentsEnrolled.add(student);
            }
        }

    }

    void run() {
        //Set up Gui
        frame = new JFrame("Welcome");
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(bl);
        
        JLabel heading = new JLabel("HOME");
        heading.setFont(new Font("Myraid Pro", Font.BOLD, 20));

        JPanel subjectPanel = new JPanel();
        fillSubjectPanel(subjectPanel);
                
        JPanel reportPanel = new JPanel();
        fillReportPanel(reportPanel);

        JPanel addRecordPanel = new JPanel();
        fillAddRecordPanel(addRecordPanel);

        panel.add(heading);
        panel.add(subjectPanel);
        panel.add(addRecordPanel);
        panel.add(reportPanel);

        setUpFrame();
    }

    void fillSubjectPanel(JPanel subjectPanel) {
        JLabel subjectLabel = new JLabel("Select Subject ");
        subjectChoice = new JComboBox<String>();
        fillSubjectChoice();
        subjectPanel.add(subjectLabel);
        subjectPanel.add(subjectChoice);
        subjectPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        subjectPanel.setMaximumSize(subjectPanel.getPreferredSize());
    }

    void fillReportPanel(JPanel reportPanel) {
        JButton attendanceButton = new JButton("Get Attendance");
        attendanceButton.addActionListener(new AttendanceButtonListener());

        JButton marksButton = new JButton("Get Marks");
        marksButton.addActionListener(new MarksButtonListener());

        reportPanel.add(attendanceButton);
        reportPanel.add(marksButton);
        reportPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        reportPanel.setMaximumSize(reportPanel.getPreferredSize());
    }

    void fillAddRecordPanel(JPanel addRecordPanel) {
        JButton addAttendance = new JButton("Add Attendance");
        addAttendance.addActionListener(new AddAttendanceListener());

        JButton addMarks = new JButton("Add Marks");
        addMarks.addActionListener(new AddMarksListener());

        addRecordPanel.add(addAttendance);
        addRecordPanel.add(addMarks);
        addRecordPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        addRecordPanel.setMaximumSize(addRecordPanel.getPreferredSize());
    }

    void setUpFrame() {
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 400);
        //frame.pack();
    }

    void fillSubjectChoice() {
        for (Subject subject : subjects) {
            subjectChoice.addItem(subject.name);
        }
    }

    class AttendanceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            Subject selectedSubject = subjects.get(subjectChoice.getSelectedIndex());
            new DisplayAttendance().run(selectedSubject);
        }
    }
    
    class MarksButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            Subject selectedSubject = subjects.get(subjectChoice.getSelectedIndex());
            new DisplayMarks().run(selectedSubject);
        }
    }

    class AddAttendanceListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            Subject selectedSubject = subjects.get(subjectChoice.getSelectedIndex());
            new AddAttendance().run(selectedSubject);
        }
    }

    class AddMarksListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            Subject selectedSubject = subjects.get(subjectChoice.getSelectedIndex());
            new AddMarks().run(selectedSubject);
        }
    }

}

