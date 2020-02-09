package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    public ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private TextField emailTextField;
    @FXML
    private ComboBox roleComboBox;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private RadioButton onlineRadioButton;
    @FXML
    private RadioButton awayRadioButton;
    @FXML
    private RadioButton busyRadioButton;
    @FXML
    private RadioButton offlineRadioButton;
    @FXML
    private TextArea notesTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        roleComboBox.getItems().addAll("Janitor","Part-Time Student", "Full-Time Student", "Professor",
                "Researcher", "Dean of COT", "Support Desk");

        employeeListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue< ? extends Employee > ov, Employee old_val, Employee new_val)->
                {
                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();

                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    emailTextField.setText(((Employee)selectedItem).email);
                    roleComboBox.setValue(((Employee)selectedItem).role);
                    hireDatePicker.setValue(LOCAL_DATE((((Employee)selectedItem).hiredate)));
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                    notesTextArea.setText(((Employee)selectedItem).notes);

                    //Code to set radio buttons
                    String ifonline = "online";
                    String ifaway = "away";
                    String ifbusy = "busy";
                    String ifoffline = "offline";


                    if (new_val.getStatus().toUpperCase().equals(ifonline.toUpperCase()))
                    {
                        onlineRadioButton.setSelected(true);
                        awayRadioButton.setSelected(false);
                        busyRadioButton.setSelected(false);
                        offlineRadioButton.setSelected(false);
                    }
                    else if(new_val.getStatus().toUpperCase().equals(ifaway.toUpperCase()))
                    {
                        onlineRadioButton.setSelected(false);
                        awayRadioButton.setSelected(true);
                        busyRadioButton.setSelected(false);
                        offlineRadioButton.setSelected(false);
                    }
                    else if (new_val.getStatus().toUpperCase().equals(ifbusy.toUpperCase()))
                    {
                        onlineRadioButton.setSelected(false);
                        awayRadioButton.setSelected(false);
                        busyRadioButton.setSelected(true);
                        offlineRadioButton.setSelected(false);
                    }
                    else if (new_val.getStatus().toUpperCase().equals(ifoffline.toUpperCase()))
                    {
                        onlineRadioButton.setSelected(false);
                        awayRadioButton.setSelected(false);
                        busyRadioButton.setSelected(false);
                        offlineRadioButton.setSelected(true);
                    }
                }
        );

        ObservableList<Employee> items = employeeListView.getItems();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        employee1.email = "robsmith67@companyemail.com";
        employee1.hiredate = "21-01-2020";
        employee1.role = "Full-Time Student";
        employee1.hire();
        employee1.setStatus("online");
        employee1.notes = "Currently learning JavaFX.";

        employee2.firstName = "Lisa";
        employee2.lastName = "Smith";
        employee2.email = "lissmith45@companyemail.com";
        employee2.hiredate = "04-04-2004";
        employee2.role = "Professor";
        employee2.hire();
        employee2.setStatus("away");
        employee2.notes = "Teaching database design to 150 students.";

        items.add(employee1);
        items.add(employee2);

        for (int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.email = "genemployee" + i + "@companyemail.com";
            employee.hiredate = "07-02-2020";
            employee.role = "Support Desk";
            employee.hire();
            items.add(employee);
            employee.setStatus("offline");
            employee.notes = "Testing feature for future implementation.";
        }

        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";
        staff1.email = "stagoodworker1@companyemail.com";
        staff1.hiredate = "22-07-1999";
        staff1.role = "Researcher";
        staff1.hire();
        staff1.setStatus("busy");
        staff1.notes = "Implementing Artifical Intelligence onto artificial limbs.";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";
        faculty1.email = "facterribleworker1@companyemail.com";
        faculty1.hiredate = "12-12-2010";
        faculty1.role = "Janitor";
        faculty1.fire();
        faculty1.setStatus("offline");
        faculty1.notes = "No longer with the company.";


        items.add(staff1);
        items.add(faculty1);

    }

    public static final LocalDate LOCAL_DATE (String dateString)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
