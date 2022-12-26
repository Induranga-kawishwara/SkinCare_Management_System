package Console;


import GUI.Gui_main;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    public static  ArrayList<Doctor> doctorArray  = new ArrayList<>();
    public static ArrayList <Consultation> consult = new ArrayList <>();
    
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        WestminsterSkinConsultationManager west = new WestminsterSkinConsultationManager();
        west.loaddata();
        boolean start = true;
        do {
            try{
                System.out.print("""
                      \n=======================================================================================
                      ||                                     MENU                                          ||
                      ||                    (Induranga_Kawishwara-20200688-w1913278 )                      ||
                      =======================================================================================
                      ||                                                                                   ||
                      ||             [1] add a doctor                [5] read the file                     ||
                      ||             [2] delete a doctor             [6] load data into the system         ||
                      ||             [3] print the list of doctor    [7] load the GUI                      ||
                      ||             [4] save all to a file          [8] Exit from system                  ||
                      ||                                                                                   ||
                      =======================================================================================
                     
                      What is your choice ?\s""");
                String input = scanner.next();
                switch (input) {
                    case "1" -> west.AddDoctor();
                    case "2" -> west.DeleteDoctor();
                    case "3" -> west.PrintListOfDoctors();
                    case "4" -> west.SaveFile();
                    case "5" -> west.ReadFile();
                    case "6" -> west.loaddata();
                    case "7" -> new Gui_main();
                    case "8" -> start = false;
                    default -> System.out.println("\nInvalid input pls try again later\n");
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Invalid input");
            }
        }while (start);
    }
    public String Alldetails(String all){
        System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", "Name", "SurName", "Birthday","Mobile-NO","Licence","Specialisation");
        String run = "Stop";
        if(all.equals("sort")){
            int i = doctorArray.size();
            String [] sort = new String[i];
            for (int j = 0; j < i;j++) {
                sort[j]=doctorArray.get(j).getSurname();
            }
//            for (int k = 0 ; k < sort.length ;k++) {
//                for(int l =k+1 ;l<sort.length; l++ ){
//                    if(sort[k].compareTo(sort[l])>0){
//                        String temp = sort[k];
//                        sort[k] = sort[l];
//                        sort[l]=temp;
//                    }
//                }
//            }
            Arrays.sort(sort);
            for (String s : sort) {
                for (Doctor doctor : doctorArray) {
                    if (s.equals(doctor.getSurname())) {
                        run = "start";
                        System.out.println("-----------------------------------------------------------------------------------------");
                        System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation());
                    }
                }
            }
        }else {
            for (Doctor doctor : doctorArray) {
                run = "start";
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------\n");
        return run;
    }
    @Override
    public void AddDoctor() {
        boolean virgin = true;
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern p = Pattern.compile(regex);
        if(10>doctorArray.size()) {
            System.out.println("------------------------ADD DOCTORS------------------------");
            System.out.print("enter your name : ");
            String name = scanner.next().trim();
            System.out.print("enter your surname : ");
            String surname = scanner.next().trim();
            Matcher f = p.matcher(name);
            Matcher s = p.matcher(surname);
            if(f.matches() && s.matches()){
                System.out.print("Enter date of birth(YYYY-MM-DD) : ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.next());
                //int dat = dateOfBirth.getYear();
                //System.out.println(dat);
                //Year thisYear = Year.now().minusYears(100);
                //System.out.println(thisYear);dateOfBirth.isBefore(LocalDate.parse("2000-01-01"))
                if(dateOfBirth.isAfter(LocalDate.now().minusYears(100)) && dateOfBirth.isBefore(LocalDate.now().minusYears(22))){
                    System.out.print("Enter mobile number: ");
                    String mobile = scanner.next().trim();
                    // validation for mobilenumber
                    if ( 10 == mobile.length()){
                        Integer.parseInt(mobile);
                        System.out.print("Enter the licence : ");
                        String licence = scanner.next().trim();
                        for (Doctor doctor : doctorArray) {
                            if (licence.equals(doctor.getMedicalLicence())) {
                                System.out.println("\n-Medical licence already exists-");
                                System.out.println("-Please re-enter the correct details-\n");
                                virgin = false;
                                break;
                            }
                        }
                        if (virgin) {
                            System.out.print("specialisation : ");
                            String spec = scanner.next().trim();

                            doctorArray.add(new Doctor(name, surname, dateOfBirth, mobile, licence, spec));
                        }
                    }else{
                        System.out.println("Check entered phone number!!!");
                    }
                }else {
                    System.out.println("Check your birthday!!");
                }
//                System.out.print("Enter mobile number: ");
//                String mobile = scanner.next().trim();
//                // validation for mobilenumber
//                if ( 10 == mobile.length()){
//                    Integer.parseInt(mobile);
//                    System.out.print("Enter the licence : ");
//                    String licence = scanner.next().trim();
//                    for (Doctor doctor : doctorArray) {
//                        if (licence.equals(doctor.getMedicalLicence())) {
//                            System.out.println("\n-Medical licence already exists-");
//                            System.out.println("-Please re-enter the correct details-\n");
//                            virgin = false;
//                            break;
//                        }
//                    }
//                    if (virgin) {
//                        System.out.print("specialisation : ");
//                        String spec = scanner.next().trim();
//
//                        doctorArray.add(new Doctor(name, surname, dateOfBirth, mobile, licence, spec));
//                    }
//                }else{
//                    System.out.println("Check entered phone number!!!");
//                }
            }else {
                System.out.println("Check entered  First name or Sure name !!!!");
            }
        }
    }
    @Override
    public void DeleteDoctor() {
        System.out.print("""
                -----------------------------------------------------------------------------------------
                                                     DELETE DOCTORS
                                            (All doctors details can see bellow)   \s
                -----------------------------------------------------------------------------------------
                """);
        if(Alldetails("non-sort").equals("start")){
            System.out.print("Enter the medical Licence that what you want remove : ");
            String dele = scanner.next().trim();
            for(int i = 0 ; i < doctorArray.size() ; i++){
                if (dele.equals(doctorArray.get(i).getMedicalLicence())){
                    System.out.print("""
                            -----------------------------------------------------------------------------------------
                                                            DELETED DOCTOR'S DETAILS
                            -----------------------------------------------------------------------------------------
                            """);
                    System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", "Name", "SurName", "Birthday","Mobile-NO","Licence","Specialisation");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n",doctorArray.get(i).getName(),doctorArray.get(i).getSurname(),doctorArray.get(i).getDateOfBirth(),doctorArray.get(i).getMobileNo(),doctorArray.get(i).getMedicalLicence(),doctorArray.get(i).getSpecialisation());
                    System.out.println("-----------------------------------------------------------------------------------------\n");
                    doctorArray.remove(i);
                    if(doctorArray.size()==1){
                        System.out.print("""
                          -----------------------------------------------------------------------------------------
                                                    Successfully removed a doctor's details
                                                   There is one doctor details can see bellow \s
                         -----------------------------------------------------------------------------------------\s
                         """);
                        Alldetails("non-sort");
                    } else if (doctorArray.size()>1) {
                        System.out.print("""
                          -----------------------------------------------------------------------------------------
                                                   Successfully removed a doctor's details
                                                   There are\s""" +doctorArray.size()+" doctors details can see bellow\n"+ """
                         -----------------------------------------------------------------------------------------\s
                         """);
                        Alldetails("non-sort");
                    } else{
                        System.out.print("""
                          -----------------------------------------------------------------------------------------
                                                    Successfully removed a doctor's details
                                                        Any doctor  is  not exist now \s
                         -----------------------------------------------------------------------------------------\s
                         """);
                    }
                    break;
                }else{
                    if(i == doctorArray.size()-1){
                        System.out.println("Not exist any doctor using this licence ");
                    }
                }
            }

        }else{ System.out.println("Can't find any doctor details.");}
    }

    @Override
     public void PrintListOfDoctors() {
        System.out.print("""
                -----------------------------------------------------------------------------------------
                                 ALL DOCTORS DETAILS AND SORT ALPHABETICALLY USING SURNAMES
                -----------------------------------------------------------------------------------------
                """);
        if(Alldetails("sort").equals("Stop")){
            System.out.println("Can't find any doctor details to find.");
        }
    }

    @Override
    public void SaveFile() {
        try {
            BufferedWriter writer  = new BufferedWriter(new FileWriter("details.txt"));
            BufferedWriter temp  = new BufferedWriter(new FileWriter("temp.txt"));
            for (Doctor doctor : doctorArray) {
                temp.write(doctor.getName() + "\n" + doctor.getSurname() + "\n" + doctor.getDateOfBirth() + "\n" + doctor.getMobileNo() + "\n" + doctor.getMedicalLicence() + "\n" + doctor.getSpecialisation() + "\n\n");
            }

            for(int i =0 ; i<doctorArray.size();i++){
                if(1 == (i+1)){
                    writer.write("""
                
                --------------------------------------------------------------------
                                        1st DOCTOR'S DETAILS
                --------------------------------------------------------------------
                
                """);
                    writer.write (String.valueOf(doctorArray.get(i)));
                } else if (2 == (i+1)) {
                    writer.write("""
                
                --------------------------------------------------------------------
                                        2nd DOCTOR'S DETAILS
                --------------------------------------------------------------------
                
                """);
                    writer.write (String.valueOf(doctorArray.get(i)));
                } else if (3 == (i+1)) {
                    writer.write("""
                
                --------------------------------------------------------------------
                                        3rd DOCTOR'S DETAILS
                --------------------------------------------------------------------
                
                """);
                    writer.write (String.valueOf(doctorArray.get(i)));

                }else {
                    writer.write("""
                          
                          --------------------------------------------------------------------
                                  \t\t\t"""+(i+1)+"th DOCTOR'S DETAILS\n"+ """
                         --------------------------------------------------------------------
                          
                         """);
                    writer.write (String.valueOf(doctorArray.get(i)));
                }
            }
            System.out.println("Successfully store data in 'details.txt' file ");
            writer.close();
            temp.close();
        }catch (IOException e){
            System.out.println("Something Wrong !!!!! ");
        }
    }
    @Override
    public void ReadFile() {
        try {
            String line ;
            BufferedReader reader  = new BufferedReader(new FileReader("details.txt"));
            while ((line= reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Something Wrong !!!!! ");
        }
    }
    @Override
    public void loaddata() {
            try {
                String tempword;
                ArrayList<String> tempArray = new ArrayList<>();
                BufferedReader tempre = new BufferedReader(new FileReader("temp.txt"));
                while ((tempword = tempre.readLine()) != null) {
                    if (tempword.equals("")) {
                        continue;
                    } else {
                        tempArray.add(tempword);
                    }
                }
                if(tempArray.size()==0){
                    System.out.println("\nNO old data found!!!!! ");
                }
                while ( 0 < (tempArray.size() / 6)) {
                    if (doctorArray.size() == 0) {
                        doctorArray.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                        tempArray.subList(0, 6).clear();
                    } else {
                        if (doctorArray.size() <= 10) {
                            boolean virgin = true;
                            for (Doctor doctor : doctorArray) {
                                if(tempArray.size()>0){
                                    if (doctor.getMedicalLicence().equals(tempArray.get(4))) {
                                        virgin = false;
                                        tempArray.subList(0, 6).clear();
                                    } else {
                                        virgin = true;
                                        break;
                                    }
                                }else{
                                    virgin = false;
                                    break;
                                }
                            }
                            if (virgin) {
                                doctorArray.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                                tempArray.subList(0, 6).clear();
                            }
                        }
                    }
                }
                tempre.close();
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("Something Wrong!!!!! ");

            }
        }
    }

