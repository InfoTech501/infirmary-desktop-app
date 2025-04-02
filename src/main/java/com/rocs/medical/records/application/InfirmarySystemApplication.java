package com.rocs.medical.records.application;

import com.rocs.medical.records.application.app.facade.commonAilmentsReport.CommonAilmentsReportFacade;
import com.rocs.medical.records.application.app.facade.commonAilmentsReport.impl.CommonAilmentsReportFacadeImpl;
import com.rocs.medical.records.application.app.facade.lowStockMedicine.LowStockMedicineFacade;
import com.rocs.medical.records.application.app.facade.lowStockMedicine.impl.LowStockMedicineFacadeImpl;
import com.rocs.medical.records.application.app.facade.medicalRecord.impl.StudentMedicalRecordFacadeImpl;
import com.rocs.medical.records.application.model.inventory.LowStockItem;
import com.rocs.medical.records.application.model.reports.CommonAilmentsReport;
import com.rocs.medical.records.application.model.person.Person;

import com.rocs.medical.records.application.app.facade.reportMedicationTrend.ReportMedicationTrendFacade;
import com.rocs.medical.records.application.app.facade.reportMedicationTrend.impl.ReportMedicationTrendFacadeImpl;
import com.rocs.medical.records.application.model.reports.MedicationTrendReport;

import com.rocs.medical.records.application.app.facade.frequentVisitReport.FrequentVisitReportFacade;
import com.rocs.medical.records.application.app.facade.frequentVisitReport.impl.FrequentVisitReportFacadeImpl;
import com.rocs.medical.records.application.model.reports.FrequentVisitReport;

import com.rocs.medical.records.application.app.facade.deleteMedicalRecord.StudentMedicalRecordsFacade;
import com.rocs.medical.records.application.app.facade.deleteMedicalRecord.impl.StudentMedicalRecordsFacadeImpl;
import com.rocs.medical.records.application.model.deleteMedicalRecord.StudentMedicalRecords;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("6 - Delete Medical Record");


        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        switch (choice) {
            case 1: {
                CommonAilmentsReportFacade ailmentsReportFacade = new CommonAilmentsReportFacadeImpl();

                try {
                    scanner.nextLine();
                    System.out.println("Common Ailments Report");

                    Date startDate = getValidInputDate(scanner, dateFormat, "Enter start date (yyyy-MM-dd): ");
                    Date endDate = getValidInputDate(scanner, dateFormat, "Enter end date (yyyy-MM-dd): ");

                    System.out.print("Enter grade level (enter to skip): ");
                    String gradeLevel = scanner.nextLine().trim();
                    gradeLevel = gradeLevel.isEmpty() ? null : gradeLevel;

                    System.out.print("Enter section (enter to skip): ");
                    String section = scanner.nextLine().trim();
                    section = section.isEmpty() ? null : section;

                    List<CommonAilmentsReport> reports = ailmentsReportFacade.generateReport(startDate, endDate, gradeLevel, section);
                    displayCommonAilmentsReport(reports, startDate, endDate, gradeLevel, section);
                } catch (RuntimeException e) {
                    System.err.println("Report generation failed: " + e.getMessage());
                }
                break;
            }

            case 2: {
                scanner.nextLine();
                ReportMedicationTrendFacade medicationTrendFacade = new ReportMedicationTrendFacadeImpl();

                try {
                    System.out.println("\nWelcome to Medication Trend Report");

                    Date startDate = getValidInputDate(scanner, dateFormat, "Please enter start date (yyyy-MM-dd): ");
                    Date endDate = getValidInputDate(scanner, dateFormat, "Please enter end date (yyyy-MM-dd): ");

                    List<MedicationTrendReport> reports = medicationTrendFacade.generateReport(startDate, endDate);
                    medicationTrendFacade.displayMedTrendReport(reports, startDate, endDate);


                } catch (RuntimeException e) {
                    System.err.println("Error generating: " + e.getMessage());
                }
                break;
            }


            case 3: {
                try {
                    scanner.nextLine();

                    StudentMedicalRecordFacadeImpl studentMedical = new StudentMedicalRecordFacadeImpl();

                    System.out.println("Search Student Medical Records using LRN: ");
                    long LRN = scanner.nextLong();

                    studentMedical.findMedicalInformationByLRN(LRN);


                } catch (RuntimeException e) {
                    System.err.println("Error generating: " + e.getMessage());
                }
                break;
            }

            case 4: {
                scanner.nextLine();
                FrequentVisitReportFacade frequentVisitReportFacade = new FrequentVisitReportFacadeImpl();

                try {
                    System.out.println("Frequent Visit Report");

                    Date frequentVisitStartDate = getValidInputDate(scanner, dateFormat, "Enter start date (yyyy-MM-dd): ");
                    Date frequentVisitEndDate = getValidInputDate(scanner, dateFormat, "Enter end date (yyyy-MM-dd): ");
                    System.out.print("Enter grade level for Frequent Visit: ");
                    String frequentVisitGradeLevel = scanner.nextLine().trim();

                    List<FrequentVisitReport> reports = frequentVisitReportFacade.generateReport(frequentVisitStartDate, frequentVisitEndDate, frequentVisitGradeLevel);
                    frequentVisitReportFacade.handleFrequentVisit(reports, frequentVisitStartDate, frequentVisitEndDate, frequentVisitGradeLevel);

                } catch (RuntimeException e) {
                    System.err.println("Report generation failed: " + e.getMessage());
                }
                break;

            }
            case 5: {
                LowStockMedicineFacade lowStockMedicineFacade = new LowStockMedicineFacadeImpl();
                try {
                    List<LowStockItem> lowStockItems = lowStockMedicineFacade.checkLowStockAndNotify();
                } catch (RuntimeException e) {
                    System.err.println("Error checking low stock items: " + e.getMessage());
                }
                break;
            }


            case 6: {

                StudentMedicalRecordsFacadeImpl StudentMedicalRecordsFacade = new StudentMedicalRecordsFacadeImpl();
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter the id of the item to delete: ");
                String id = sc.nextLine();

                if (id == null) {
                    System.out.println("Item to delete not found.");

                } else {

                    System.out.print("Are you sure you want to delete this record? This action cannot be undone. (yes/no): ");
                    String confirmation = sc.nextLine();

                    if (confirmation.equals("yes")) {
                        boolean result = StudentMedicalRecordsFacade.deleteStudentMedicalRecordById(id);

                        if (result) {
                            System.out.println("Item successfully deleted.");
                        } else {
                            System.out.println("Item cannot be deleted.");
                        }

                    }

                    if (confirmation.equals("no")) {
                        System.out.println("Cancel the Deletion");

                    }

                }
                break;
            }

            default:
                System.out.println("Invalid choice. Please select a valid option.");
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
}