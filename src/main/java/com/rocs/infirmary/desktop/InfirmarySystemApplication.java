package com.rocs.infirmary.desktop;

import com.rocs.infirmary.desktop.app.facade.dashboard.DashboardFacade;
import com.rocs.infirmary.desktop.app.facade.dashboard.impl.DashboardFacadeImpl;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.MedicineInventoryFacade;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl.MedicineInventoryFacadeImpl;
import com.rocs.infirmary.desktop.app.facade.student.record.impl.StudentMedicalRecordFacadeImpl;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import com.rocs.infirmary.desktop.data.model.person.Person;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class InfirmarySystemApplication {
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

        int choice = 0;
        while (true) {
            try {
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                break;
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
                    displayCommonAilmentsReport(reports, startDate, endDate, gradeLevel, section);
                } catch (RuntimeException e) {
                    System.out.println("Report generation failed: " + e.getMessage());
                }
                break;
            }

            case 2: {

                try {
                    scanner.nextLine();
                    System.out.println("\nWelcome to Medication Trend Report");
                    SimpleDateFormat displayFormat = new SimpleDateFormat("MMMM dd, yyyy");
                    Date startDate = getValidInputDate(scanner, dateFormat, "Please enter start date (yyyy-MM-dd): ");
                    Date endDate = getValidInputDate(scanner, dateFormat, "Please enter end date (yyyy-MM-dd): ");

                    List<MedicationTrendReport> medicationTrendReportList = dashboardFacade.generateMedicationReport(startDate, endDate);
                    if (medicationTrendReportList == null || medicationTrendReportList.isEmpty()) {
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
                        }
                    }
                } catch (RuntimeException e) {
                    System.out.println("Error generating: " + e.getMessage());
                }
                break;
            }
            case 3: {
                try {
                    scanner.nextLine();
                    StudentMedicalRecordFacadeImpl studentMedicalRecord = new StudentMedicalRecordFacadeImpl();
                    System.out.println("Search Student Medical Records using LRN: ");
                    long LRN = scanner.nextLong();
                    Student record = studentMedicalRecord.findMedicalInformationByLRN(LRN);
                    if (record == null) {
                        System.out.println(" Not Student Found");
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
                    }
                } catch (RuntimeException e) {
                    System.out.println("No Student Found!");

                }
                break;
            }

            case 4: {
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
                    }


                } catch (RuntimeException e) {
                    System.out.println("Report generation failed: " + e.getMessage());
                }
                break;

            }
            case 5: {
                try {
                    List<LowStockReport> lowStockItems = dashboardFacade.findAllLowStockMedicine();
                    for (LowStockReport medicineInventory : lowStockItems) {
                        System.out.println("Medicine Name: " + medicineInventory.getDescription());
                        System.out.println("Current Stock Level: " + medicineInventory.getQuantityAvailable());
                        System.out.println("Notification: The stock level of " + medicineInventory.getDescription() + " is low. Current stock level: " + medicineInventory.getQuantityAvailable() + ". Please reorder supplies.");
                    }
                } catch (RuntimeException e) {
                    System.out.println("Error checking low stock items: " + e.getMessage());
                }
                break;
            }

            case 6: {
                MedicineInventoryFacade inventoryFacade = new MedicineInventoryFacadeImpl();
                List<Medicine> medicineInventoryItems = inventoryFacade.findAllMedicine();
                if (medicineInventoryItems.isEmpty()) {
                    System.out.println("The list of items is empty.");
                } else {
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
                }
                break;
            }
            case 7: {

                StudentMedicalRecordFacadeImpl studentMedical = new StudentMedicalRecordFacadeImpl();
                List<Student> medicalRecords = studentMedical.readAllStudentMedicalRecords();

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
                }
                break;
            }
            case 8: {

                StudentMedicalRecordFacadeImpl studentMedicalRecordFacade = new StudentMedicalRecordFacadeImpl();
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter the LRN of student to delete: ");
                long lrn = sc.nextLong();
                System.out.print("Are you sure you want to delete this record? This action cannot be undone. (Select 1. for YES and 2. for NO/CANCEL): ");
                int confirmation = sc.nextInt();
                if (confirmation == 1) {
                    studentMedicalRecordFacade.deleteStudentMedicalRecordByLrn(lrn);
                    System.out.println("Deleted successfully");
                } else if (confirmation == 2) {
                    System.out.println("Cancel the Deletion");

                } else {
                    System.out.println("invalid input");
                }


                break;
            }

            default:
                System.out.println("Invalid choice. Please select a valid option.");
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


}