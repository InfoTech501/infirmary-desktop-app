package com.rocs.infirmary.desktop;

import com.rocs.infirmary.desktop.app.facade.dashboard.DashboardFacade;
import com.rocs.infirmary.desktop.app.facade.dashboard.impl.DashboardFacadeImpl;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.MedicineInventoryFacade;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl.MedicineInventoryFacadeImpl;
import com.rocs.infirmary.desktop.app.facade.student.profile.Impl.StudentHealthProfileFacadeImpl;
import com.rocs.infirmary.desktop.app.facade.student.profile.StudentHealthProfileFacade;
import com.rocs.infirmary.desktop.app.facade.student.record.StudentMedicalRecordFacade;
import com.rocs.infirmary.desktop.app.facade.student.record.impl.StudentMedicalRecordFacadeImpl;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import com.rocs.infirmary.desktop.data.model.person.Person;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import static org.slf4j.LoggerFactory.*;


public class InfirmarySystemApplication {

    private static Logger LOGGER =  LoggerFactory.getLogger(InfirmarySystemApplication.class);

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Infirmary System Application");
        System.out.println("Please select which report:");
        System.out.println("1 - Common Ailments Report");
        System.out.println("2 - Medication Trend Report");
        System.out.println("3 - Retrieve Student Medical Record");
        System.out.println("4 - Frequent Visit Report");
        System.out.println("5 - Check Low Stock Medicine");
        System.out.println("6 - View Medicine Inventory List");
        System.out.println("7 - Read Student Medical Record");
        System.out.println("8 - Delete Student Medical Record");
        System.out.println("9 - Delete Medicine");
        System.out.println("10 - Update Student Medical Record");
        System.out.println("11 - Add New Medicine");
        System.out.println("12 - View Student Health Profiles");

        int choice = 0;
        while (true) {
            try {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 12) {
                    break;
                } else {
                    System.out.println("Invalid Choice. Please select a valid option. ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        DashboardFacade dashboardFacade = new DashboardFacadeImpl();
        switch (choice) {
            case 1: {
                try {

                    LOGGER.info(" User Access the Common Ailment Report ");
                    scanner.nextLine();
                    System.out.println("Common Ailments Report");


                    Date startDate = getValidInputDate(scanner, dateFormat, "Enter start date (yyyy-MM-dd): ");
                    Date endDate = getValidInputDate(scanner, dateFormat, "Enter end date (yyyy-MM-dd): ");

                    String gradeLevel = selectGradeLevel();
                    gradeLevel = gradeLevel.isEmpty() ? null : gradeLevel;


                    System.out.print("Enter section (enter to skip): ");
                    String section = scanner.nextLine().trim();
                    section = section.isEmpty() ? null : section;


                    List<CommonAilmentsReport> reports = dashboardFacade.generateCommonAilmentReport(startDate, endDate, gradeLevel, section);
                    if (reports == null || reports.isEmpty()) {
                        LOGGER.info("Failed on Generating Report ");
                    } else {
                        displayCommonAilmentsReport(reports, startDate, endDate, gradeLevel, section);
                        LOGGER.info("Report Successfully Generated");
                        LOGGER.info("Program Ended Successfully");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input detected. Please enter valid data.");
                    scanner.nextLine();
                } catch (RuntimeException e) {
                    LOGGER.error("Runtime Exception Occured " + e);
                    System.out.println("Report generation failed: " + e.getMessage());
                }

                break;
            }

            case 2: {

                try {
                    LOGGER.info(" User Access the Medication Trend Report ");
                    scanner.nextLine();
                    System.out.println("\nWelcome to Medication Trend Report");
                    SimpleDateFormat displayFormat = new SimpleDateFormat("MMMM dd, yyyy");
                    Date startDate = getValidInputDate(scanner, dateFormat, "Please enter start date (yyyy-MM-dd): ");
                    Date endDate = getValidInputDate(scanner, dateFormat, "Please enter end date (yyyy-MM-dd): ");

                    List<MedicationTrendReport> medicationTrendReportList = dashboardFacade.generateMedicationReport(startDate, endDate);
                    if (medicationTrendReportList == null || medicationTrendReportList.isEmpty()) {
                        LOGGER.info("No data found for the selected criteria);");
                        System.out.println("No data available for the selected criteria.");
                        return;
                    } else {
                        System.out.println("\nMedication Trend report");
                        System.out.println("Period date: " + displayFormat.format(startDate) + " to " + displayFormat.format(endDate));
                        System.out.println("\nTotal no. of medicine usage within the period date: " + medicationTrendReportList.size());
                        for (MedicationTrendReport report : medicationTrendReportList) {
                            System.out.print("\nMedication Usage: " + report.getUsage());
                            System.out.print(" | Medicine: " + report.getMedicineName());
                            System.out.print(" | Medication Stocks: " + report.getStocks());

                            LOGGER.info("Generate Medication Trend Report Successfully");
                            LOGGER.info("Program Successfully Ended");
                        }
                    }
                } catch (RuntimeException e) {
                    LOGGER.error(" RuntimeException " + e);
                    System.out.println("Error generating: " + e.getMessage());
                }
                break;
            }

            case 3: {
                scanner.nextLine();
                StudentMedicalRecordFacadeImpl studentMedicalRecord = new StudentMedicalRecordFacadeImpl();

                String LRN = getValidLRN(scanner, "Search Student Medical Records using LRN: ");

                try {
                    Student record = studentMedicalRecord.findMedicalInformationByLRN(Long.parseLong(LRN));
                    if (record == null) {
                        LOGGER.info("No student record found ");
                        System.out.println("Student Not Found");
                    } else {
                        System.out.println("Firstname             : " + record.getFirstName());
                        System.out.println("Middlename            : " + record.getMiddleName());
                        System.out.println("Lastname              : " + record.getLastName());
                        System.out.println("Age                   : " + record.getAge());
                        System.out.println("Gender                : " + record.getGender());
                        System.out.println("Symptoms              : " + record.getSymptoms());
                        System.out.println("Temperature Readings  : " + record.getTemperatureReadings());
                        System.out.println("Visit Date            : " + record.getVisitDate());
                        System.out.println("Treatment             : " + record.getTreatment());

                        LOGGER.info("Retrieved Medical Record Successfully");
                        LOGGER.info("Program Successfully Ended");

                    }
                } catch (InputMismatchException e) {
                    LOGGER.error("Input Mismatch Exception " + e);
                    System.out.println("Error: the LRN you entered is not valid. Please enter only numbers.");
                    scanner.nextLine();

                } catch (RuntimeException e) {
                    LOGGER.error("Runtime Exception " + e);
                    System.out.println("No Student Found!");

                }
                break;
            }

            case 4: {

                LOGGER.info(" User Access the Frequent Visit Report ");
                scanner.nextLine();
                try {
                    System.out.println("Frequent Visit Report");
                    SimpleDateFormat displayFormat = new SimpleDateFormat("MMMM dd, yyyy");

                    Date frequentVisitStartDate = getValidInputDate(scanner, dateFormat, "Enter start date (yyyy-MM-dd): ");
                    Date frequentVisitEndDate = getValidInputDate(scanner, dateFormat, "Enter end date (yyyy-MM-dd): ");

                    String frequentVisitGradeLevel;
                    while (true) {
                        System.out.println("Select Grade Level for Frequent Visit \n(1 = Grade 11, 2 = Grade 12): ");
                        String gradeInput = scanner.nextLine().trim();
                        if (gradeInput.equals("1")) {
                            frequentVisitGradeLevel = "Grade 11";
                            break;
                        } else if (gradeInput.equals("2")) {
                            frequentVisitGradeLevel = "Grade 12";

                            break;
                        } else {
                            System.out.println("Invalid Input. Please Enter 1 or 2");
                        }
                    }

                    List<FrequentVisitReport> reports = dashboardFacade.generateFrequentVisitReport(frequentVisitStartDate, frequentVisitEndDate, frequentVisitGradeLevel);

                    if (reports == null || reports.isEmpty()) {
                        LOGGER.info("No data found for the selected criteria);");
                        System.out.println("No data available for the selected criteria.");
                    } else {
                        System.out.println("Frequent Visit Report");
                        System.out.println("Period of Date: " + displayFormat.format(frequentVisitStartDate) + " to " + displayFormat.format(frequentVisitEndDate));
                        System.out.println("Total no. of Visit: " + reports.size());
                        for (FrequentVisitReport report : reports) {
                            System.out.println("\nStudent First Name: " + report.getFirstName());
                            System.out.println("Student Last Name: " + report.getLastName());
                            System.out.println("Visit Date: " + report.getVisitDate());
                            System.out.println("Grade Level: " + report.getGradeLevel());
                            System.out.println("Health Concern: " + report.getSymptoms());
                            System.out.println("Total Visit: " + report.getVisitCount());

                        }
                        LOGGER.info("Generate Frequent Visit Report Successfully");
                        LOGGER.info("Program Successfully Ended");
                    }


                } catch (RuntimeException e) {
                    LOGGER.error(" RuntimeException " + e);
                    System.out.println("Report generation failed: " + e.getMessage());
                }
                break;

            }
            case 5: {
                LOGGER.info(" User Access the Check Low Stock Medicine ");
                try {
                    List<LowStockReport> lowStockItems = dashboardFacade.findAllLowStockMedicine();
                    for (LowStockReport medicineInventory : lowStockItems) {
                        System.out.println("Medicine Name: " + medicineInventory.getDescription());
                        System.out.println("Current Stock Level: " + medicineInventory.getQuantityAvailable());
                        System.out.println("Notification: The stock level of " + medicineInventory.getDescription() + " is low. Current stock level: " + medicineInventory.getQuantityAvailable() + ". Please reorder supplies.");
                    }

                    LOGGER.info("Alerting Check Low Stock Medicine Successfully");
                    LOGGER.info("Program Successfully Ended");
                } catch (RuntimeException e) {
                    LOGGER.error(" RuntimeException " + e);
                    System.out.println("Error checking low stock items: " + e.getMessage());
                }
                break;
            }

            case 6: {
                LOGGER.info(" User Access View Medicine Inventory List ");
                MedicineInventoryFacade inventoryFacade = new MedicineInventoryFacadeImpl();
               try{
                List<Medicine> medicineInventoryItems = inventoryFacade.findAllMedicine();
                if (medicineInventoryItems.isEmpty()) {
                    LOGGER.info("Medicine Inventory List is empty.");
                    System.out.println("The list of items is empty.");
                } else {
                    System.out.println("Medicine Inventory Retrieval Success"+ medicineInventoryItems.size());
                    System.out.println("LIST OF ITEMS:");
                    {
                        for (Medicine medicine : medicineInventoryItems) {
                            System.out.println("Name of Medicine:  " + medicine.getItemName() +
                                    "\nItem Type:    " + medicine.getItemType() +
                                    "\nDescription:  " + medicine.getDescription() +
                                    "\nStock Level:  " + medicine.getQuantity() +
                                    "\nExpiry date:  " + medicine.getExpirationDate() + "\n");
                        }

                    }
                    LOGGER.info("Retrieving View Medicine Inventory List Successfully");
                    LOGGER.info("Program Successfully Ended");
                }
               }catch (RuntimeException e) {
                   LOGGER.error(" RuntimeException " + e);
                   System.out.println("Error retrieving medicine inventory: " + e.getMessage());
               }
                break;
            }
            case 7: {

                    StudentMedicalRecordFacadeImpl studentMedical = new StudentMedicalRecordFacadeImpl();
                    List<Student> medicalRecords = studentMedical.readAllStudentMedicalRecords();
                   try{
                    if (medicalRecords.isEmpty()) {
                        LOGGER.info("Failed to Student Medical Record ");
                        System.out.println("No record Found ");
                        return;
                    }

                    for (Student record : medicalRecords) {
                        System.out.println();
                        System.out.println("Firstname             : " + record.getFirstName());
                        System.out.println("Middlename            : " + record.getMiddleName());
                        System.out.println("Lastname              : " + record.getLastName());
                        System.out.println("Age                   : " + record.getAge());
                        System.out.println("Gender                : " + record.getGender());
                        System.out.println("Symptoms              : " + record.getSymptoms());
                        System.out.println("Temperature Readings  : " + record.getTemperatureReadings());
                        System.out.println("Visit Date            : " + record.getVisitDate());
                        System.out.println("Treatment             : " + record.getTreatment());

                        System.out.println();

                        LOGGER.info("Retrieving Read Student Medical Record Successfully");
                        LOGGER.info("Program Successfully Ended");
                    }

                } catch (RuntimeException e) {
                    LOGGER.error("Runtime Exception Occurred " + e.getMessage());
                    System.out.println("Error retrieving Medical Record : " + e.getMessage());
                    }
                    break;

            }

            case 8: {
                Scanner sc = new Scanner(System.in);
                StudentMedicalRecordFacadeImpl studentMedicalRecordFacade = new StudentMedicalRecordFacadeImpl();

                String LRN = getValidLRN(sc, "Enter the LRN of Student to Delete: ");
                long lrn = Long.parseLong(LRN);

                int confirmation;
                while (true) {
                    System.out.print("Are you sure you want to delete this record? This action cannot be undone. \n(Select 1 for YES and 2 for NO/CANCEL): ");

                    try {
                        confirmation = sc.nextInt();
                        sc.nextLine();

                        if (confirmation == 1) {
                            try {
                                studentMedicalRecordFacade.deleteStudentMedicalRecordByLrn(lrn);
                                System.out.println("LRN Deleted Successfully");
                            } catch (NullPointerException e) {
                                System.out.println("Error: The record does not exist or could not be deleted.");
                            } catch (RuntimeException e) {
                                System.out.println("Unexpected error occurred during deletion.");
                            }
                            break;
                        } else if (confirmation == 2) {
                            System.out.println("Cancel the Deletion");
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 1 for YES or 2 for NO.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number (1 or 2).");
                        sc.nextLine();
                    }
                }

                break;
            }

            case 9: {
                try {
                    scanner.nextLine();

                    MedicineInventoryFacade medicineInventoryFacade = new MedicineInventoryFacadeImpl();
                    System.out.println("Delete Medicine By Item Name : ");
                    String itemName = scanner.nextLine().trim();

                    if (itemName.isEmpty()) {
                        System.out.println("No data Detected");
                        return;

                    } else if (!medicineInventoryFacade.IsAvailable(itemName)) {
                        System.out.println("This medicine " + itemName + " " + "does not exist");
                        return;
                    }
                    String confirmationMessage = "Are you sure you want to delete this Medicine Item? \n This action cannot be undone. ";
                    int confirmation = InfirmarySystemApplication.getUserConfirmation(scanner, confirmationMessage);

                    if (confirmation == 1) {
                        boolean success = medicineInventoryFacade.deleteMedicineByItemName(itemName);
                        System.out.println(success ? "Successfully Deleted" : "Failed to Delete");

                    } else {
                        System.out.println("Cancel the deletion. ");
                    }

                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case 10: {

                scanner.nextLine();
                StudentMedicalRecordFacade studentMedicalRecordFacade = new StudentMedicalRecordFacadeImpl();
                try {

                    String LRN = getValidLRN(scanner,"Enter a Student LRN to update : ");

                    Student student = studentMedicalRecordFacade.findMedicalInformationByLRN(Long.parseLong((LRN)));

                    if (student == null) {
                        System.out.println("No student found.");
                        return;
                    }
                    System.out.println(" ");
                    System.out.println("Symptoms (Enter to skip): ");
                    String symptom = scanner.nextLine();

                    System.out.println("Temperature Readings (Enter to skip): ");
                    String temperatureReadings = scanner.nextLine();


                    Date visitDate = getValidInputDate(scanner, dateFormat,"Visit Date ( yyyy-MM-dd ):");

                    System.out.println("Treatment (Enter to skip) : ");
                    String treatment = scanner.nextLine();

                    boolean success = studentMedicalRecordFacade.updateStudentMedicalRecord(symptom, temperatureReadings, visitDate, treatment, Long.parseLong((LRN)));

                    if (success) {
                        System.out.println("Successfully Updated");

                    } else {
                        System.out.println("Failed to Update ");
                    }

                } catch (RuntimeException e) {
                    System.out.println("Runtime Exception Occurred:"+ e.getMessage());
                }
            break;
        }
            case 11: {
                scanner.nextLine();
                MedicineInventoryFacade medicineInventoryFacade = new MedicineInventoryFacadeImpl();

                try {
                    System.out.println("Add New Medicine");

                    System.out.println("Enter Medicine Name: ");
                    String itemName = scanner.nextLine();

                    System.out.println("Enter Medicine Description: ");
                    String itemDescription = scanner.nextLine();

                    Date expirationDate = getValidInputDate(scanner, dateFormat, "Enter Expiration Date (yyyy-MM-dd): ");

                    String[] words = itemName.trim().split(" ");
                    String medicineID = "";

                    if (words.length == 1) {
                            medicineID += itemName.substring(0, 2).toUpperCase();
                    } else {
                        for (String word : words) {
                                medicineID += Character.toUpperCase(word.charAt(0));
                        }
                    }

                    Medicine newMedicine = new Medicine();
                    newMedicine.setMedicineId(medicineID);
                    newMedicine.setItemName(itemName);
                    newMedicine.setDescription(itemDescription);
                    newMedicine.setExpirationDate(new java.sql.Timestamp(expirationDate.getTime()));

                    boolean success = medicineInventoryFacade.addMedicine(newMedicine);

                    if (success) {
                        System.out.println("Medicine added successfully.");
                    } else {
                        System.out.println("Failed to add medicine.");
                    }
                } catch (Exception e) {
                    System.out.println("Error occurred while adding medicine: " + e.getMessage());
                }
                break;
            }case 12:{
                try {
                    StudentHealthProfileFacade studentHealthProfileFacade = new StudentHealthProfileFacadeImpl();
                    List<Student>studentList = studentHealthProfileFacade.getAllStudentHealthProfile();
                    if (studentList == null) {
                        System.out.println("No student found.");
                        return;
                    }
                    System.out.println("Student Health Profiles");
                        for (Student student : studentList) {
                            System.out.println("LRN                :" + student.getLrn());
                            System.out.println("Fist Name          : " + student.getFirstName());
                            System.out.println("Middle Name        : " + student.getMiddleName());
                            System.out.println("Last Name          : " + student.getLastName());
                            System.out.println("Grade and section  :" + student.getGradeLevel() + " " + student.getSection());
                            System.out.println("Adviser            :" + student.getStudentAdviser() + "\n");
                        }
                        try {
                            System.out.print("Enter lrn to view detailed health profile: ");
                            long LRN = scanner.nextLong();
                            List<Student>studentListProfile = studentHealthProfileFacade.getStudentHealthProfileByLRN(LRN);
                            if (studentListProfile == null) {
                                System.out.println("No student Profile found.");
                                return; 
                            }else{
                                System.out.println("Student Health Profile");
                                for (Student student:studentListProfile){
                                    System.out.println("Name          : " + student.getFirstName()+" "+student.getLastName());
                                    System.out.println("Contact info.");
                                    System.out.println("Contact Number     :"+student.getContactNumber());
                                    System.out.println("Email Address      :"+student.getEmail());
                                    System.out.println("Address            :"+student.getAddress());
                                    System.out.println();
                                    System.out.println("Recent Clinic Visit");
                                    System.out.println("Symptoms           : " + student.getSymptoms());
                                    System.out.println("Temperature        : " + student.getTemperatureReadings());
                                    System.out.println("Treatment          : " + student.getTreatment());
                                    System.out.println("Visit date         : " + student.getVisitDate());
                                    System.out.println("Nurse in Charge    : " + student.getNurseInCharge()+"\n");
                                }
                            }

                        }catch (InputMismatchException e){
                            System.out.println("Error: the LRN you entered is not valid. Please enter only numbers.");
                            scanner.nextLine();
                        }
                }catch (NullPointerException np){
                    LOGGER.error("List containing the retrieved data is empty: {}",np.getMessage());
                }
            }
            break;
        }

}

    private static void displayCommonAilmentsReport(List<CommonAilmentsReport> reports, Date startDate, Date endDate, String gradeLevel, String section) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No data available for the selected criteria.");
            return;
        }

        SimpleDateFormat displayFormat = new SimpleDateFormat("MMMM dd, yyyy");
        System.out.println("Common Ailments Report");
        System.out.println("Period: " + displayFormat.format(startDate) + " to " + displayFormat.format(endDate));
        System.out.println("Grade level: " + (gradeLevel != null ? gradeLevel : "ALL"));
        System.out.println("Section: " + (section != null ? section : "ALL"));

        for (CommonAilmentsReport report : reports) {
            printAilmentSection(report);
        }

        System.out.println("\nReport Summary");
        System.out.println("Total no. of different ailments: " + reports.size());

        int totalOccurrences = reports.stream().mapToInt(CommonAilmentsReport::getOccurrences).sum();
        System.out.println("Total no. of occurrences: " + totalOccurrences);
    }

    private static void printAilmentSection(CommonAilmentsReport report) {
        System.out.println("\nAffected students:");
        for (Person student : report.getAffectedPeople()) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }

        System.out.println("Ailment: " + report.getAilment());
        System.out.println("No. of occurrences per ailment: " + report.getOccurrences());
        System.out.println("Grade Level: " + report.getGradeLevel());
        System.out.println("Strand: " + report.getStrand());

    }


    private static Date getValidInputDate(Scanner scanner, SimpleDateFormat dateFormat, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                Date date = dateFormat.parse(input);

                if (date.after(new Date())) {
                    System.err.println("Please enter a present or past date.");
                    continue;
                }
                return date;

            } catch (ParseException e) {
                System.err.println("Invalid date format, use yyyy-MM-dd.");
            }
        }
    }

    public static String selectGradeLevel() {
        Scanner scanner = new Scanner(System.in);
        String gradeLevel = "";

        while (true) {
            System.out.println("Select Grade Level:");
            System.out.println("1. Grade 11");
            System.out.println("2. Grade 12");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    gradeLevel = "Grade 11";
                    break;
                case 2:
                    gradeLevel = "Grade 12";
                    break;
                default:
                    System.out.println("Invalid input. Please choose 1 or 2.");
                    continue;
            }
            break;
        }

        return gradeLevel;
    }

    public static String getValidLRN(Scanner scanner, String promptMessage) {
        String LRN;
        while (true) {
            System.out.println(promptMessage);
            LRN = scanner.nextLine().trim();

            if(LRN.length() == 12 ) {
                try {
                    Long.parseLong(LRN);
                    return LRN;
                } catch (NumberFormatException e) {
                    System.out.println("Error: The LRN must contain only 12 numeric digits.");
                }
            } else {
                System.out.println("Error: Please enter a valid 12-Digit LRN.");
                LOGGER.info("User entered invalid LRN length" );
            }
        }
    }

    public static int getUserConfirmation(Scanner scanner, String promptMessage) {
        int confirmation = -1;
        while (true) {
            try {
                System.out.println(promptMessage);
                System.out.println("1 - Confirm");
                System.out.println("2 - Cancel");

                confirmation = scanner.nextInt();
                scanner.nextLine();

                if (confirmation == 1 || confirmation == 2) {
                    return confirmation;
                }else {
                    System.out.println("Invalid Choice. Please enter 1 to CONFIRM or 2 to CANCEL. ");
                }
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value (1 or 2). ");
                    scanner.nextLine();
                }
            }
        }

}