package com.rocs.infirmary.desktop;

import com.rocs.infirmary.desktop.app.facade.dashboard.DashboardFacade;
import com.rocs.infirmary.desktop.app.facade.dashboard.impl.DashboardFacadeImpl;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl.MedicineInventoryDaoImpl;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.model.person.Person;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
import com.rocs.infirmary.desktop.app.facade.student.record.impl.StudentMedicalRecordFacadeImpl;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.MedicineInventoryFacade;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl.MedicineInventoryFacadeImpl;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;

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
        System.out.println("6 - View Medicine Inventory List");
        System.out.println("7 - Delete Medicine Inventory List");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

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
                    System.out.print("Enter grade level (enter to skip): ");
                    String gradeLevel = scanner.nextLine().trim();
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
            case 7: {
                try {
                    scanner.nextLine();
                    MedicineInventoryDaoImpl medicineInventoryDao = new MedicineInventoryDaoImpl();
                    System.out.println("Enter Medicine Name to delete: ");
                    String medicineName = scanner.nextLine();
                    System.out.println("Are you sure you want to delete Medicine: " + medicineName + "? (Confirm/Cancel): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();
                    if (confirmation.equals("confirm")) {
                        boolean isDeleted = medicineInventoryDao.deleteMedicineByName(medicineName);
                        if (isDeleted) {
                            System.out.println("Medicine with name '" + medicineName + "' has been successfully deleted.");
                        } else {
                            System.out.println("Failed to delete medicine. Medicine name may not exist.");
                        }
                    } else {
                        System.out.println("Deletion canceled.");
                    }
                } catch (RuntimeException e) {
                    System.err.println("Error deleting medicine: " + e.getMessage());
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
