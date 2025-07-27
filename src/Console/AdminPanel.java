package Console;


import GUI.Gui_main;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminPanel implements SkinConsultationManager{

    // CREATED DOCTOR OBJECT ARRAY LIST
    public static  ArrayList<Doctor> doctorArray  = new ArrayList<>();
    // CREATED CONSULTATION OBJECT ARRAY LIST
    public static ArrayList <Consultation> consult = new ArrayList <>();
    //CREATED A SCANNER OBJECT AND SEATED THE PUBLIC AND STATIC TO USE EVERY METHOD
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        //CREATED AN OBJECT
        AdminPanel west = new AdminPanel();
        // CALL THE LOAD DATA METHOD
        west.loaddata();
        //CALL THE PATIENT LOAD DATA METHOD
        west.paLoadFile();
        boolean start = true;
        //USR DO WHILE LOOP TO REPEAT THE MENU UNTIL PRESS THE "8"
        do {
            try{
                System.out.print("""
                      \n=======================================================================================
                      ||                                     MENU                                          ||
                      ||                    (SkinCare Management System AdminPanel )                       ||
                      =======================================================================================
                      ||                                                                                   ||
                      ||             [1] add a doctor                [5] read the file                     ||
                      ||             [2] delete a doctor             [6] load data into the system again   ||
                      ||             [3] print the list of doctor    [7] load the GUI                      ||
                      ||             [4] save all data in to a file  [8] Exit from system                  ||
                      ||                                                                                   ||
                      =======================================================================================
                     
                      What is your choice ?\s""");
                String input = scanner.next();
                //USED THE SWITCH AND CASE TO CHECK THE CONDITION
                switch (input) {
                    case "1" -> west.AddDoctor();
                    case "2" -> west.DeleteDoctor();
                    case "3" -> west.PrintListOfDoctors();
                    case "4" -> west.SaveFile();
                    case "5" -> west.ReadFile();
                    case "6" -> west.loaddata();
                    case "7" -> new Gui_main();
                    case "8" -> start = false;
                    default -> System.out.println("\nInvalid Input please Try Again Later\n");
                }
            }catch (Exception e){
//                e.printStackTrace();
                System.out.println("Invalid Input");
            }
        }while (start);
    }
    //CREATE A METHOD TO PRINT LIST OF DOCTOR;S DATA
    public String Alldetails(String all){
        // USED "printf" TO PRINT THE TABLE
        System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", "Name", "SurName", "Birthday","Mobile-NO","Licence","Specialisation");
        String run = "Stop";
        if(all.equals("sort")){
            //CREATED A ARRAYLIST TO SAVE DOCTOR'S SURNAMES
            ArrayList <String> surname = new ArrayList<>();
            //CREATED A ARRAYLIST TO SAVE SORTED SURNAME LIST
            ArrayList <String> sort = new ArrayList<>();
            //GET SURNAME FROM doctorArray LIST
            for (Doctor value : doctorArray) {
                surname.add(value.getSurname());
            }
            //REMOVING DUPLICATED SURNAMES USING CONTAINS
            for (String value : surname) {
                if (!sort.contains(value)) {
                    sort.add(value);
                }
            }

            // ANOTHER WAY TO SORT THE ARRAY IT CALLS THE BOBBLE SORT
//            for (int k = 0 ; k < sort.length ;k++) {
//                for(int l =k+1 ;l<sort.length; l++ ){
//                    if(sort[k].compareTo(sort[l])>0){
//                        String temp = sort[k];
//                        sort[k] = sort[l];
//                        sort[l]=temp;
//                    }
//                }
//            }

            //SORT THE"sort" ARRAY USING Collections.sort(); METHOD
            Collections.sort(sort);

            //USED THE ENHANCED  "for" LOOP TO GET SORTED SURNAMES FROM sort ARRAY
            for (String s : sort) {
                //USED THE ENHANCED  "FOR" LOOP TO GET STORE OBJECT IN THE DOCTORARRAY
                for (Doctor doctor : doctorArray) {
                    //CHECK EQUALITY OF SURNAME FROM SORT ARRAY AND SUR NAME FROM DOCTOR ARRAY USING "if" CONDITION.
                    if (s.equals(doctor.getSurname())) {
                        run = "start";
                        System.out.println("-----------------------------------------------------------------------------------------");
                        System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation());
                    }
                }
            }
        }else {
            //PRINT THE LIST OF DOCTOR USING "for" LOOP
            for (Doctor doctor : doctorArray) {
                run = "start";
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------\n");
        return run;
    }

    // CREATED THE METHOD TO LOAD PATIENT DETAILS FROM PATIENT.TXT
    public void paLoadFile() {
        //CREATED THE temparray TO TEMPORALLY SORE THE DATA WHAT GET FROM PATIRNT.TXT
        ArrayList <String> tempArray = new ArrayList<>();
        //SOME TIMES FILE READERS GENERATE THE IOException  ERRORS SO USED THE TRY AND CATCH TO CATCH THE ERRORS
        // READ THE FILE USING BUFFERED-READER
        try (BufferedReader readFile = new BufferedReader(new FileReader("patient.txt"))) {
            String temp;

            //USED WHILE LOOP TO RUN AGAIN AND AGAIN UNTIL FINISH LINES IN THE TXT FILE
            while (((temp = readFile.readLine()) != null)) {
                //CHECK FOR THE BLANK LINE AND IGNORE  THAT LINE
                if(temp.isEmpty()){
                    continue;
                }else {
                    //ADD THE DATA IN TO  temparry ARRAYLIST
                    tempArray.add(temp);
                }
            }
            //RUN THE WHILE LOOP UNTIL temparray SIZE "0"
            while ( 0 < (tempArray.size() / 13)) {
                //ADD DATA IN TO THE CONSULT OBJECT ARRAY LIST
                consult.add(new Consultation(Integer.parseInt(tempArray.get(0)), tempArray.get(1), tempArray.get(2), LocalDate.parse(tempArray.get(3)), tempArray.get(4), Integer.parseInt(tempArray.get(5)), tempArray.get(6), LocalTime.parse(tempArray.get(7)), LocalTime.parse(tempArray.get(8)), LocalDate.parse(tempArray.get(9)), tempArray.get(10),Double.parseDouble(tempArray.get(11)),tempArray.get(12)));
                //AFTER ADD THE DATA INTO THE ARRAY LIST , THAT DATA WILL DELETE USING CLEAR KEY WORD
                tempArray.subList(0, 13).clear();
            }
        }catch (Exception ignored){
        }
    }

    // CREATED A METHOD TO ADD DOCTORS TO SYSTEM
    @Override
    public void AddDoctor() {
        boolean virgin = true;
        // USED REGEX TO VALIDATE THR NAME , SURNAME AND PHONE NUMBER
        //YOU CAN SEE REGEX FORMAT. I USED
        String nameValidate = "[a-zA-Z]+";
        String numberValidate ="\\A[0-9]{10}\\z";
        Pattern num = Pattern.compile(numberValidate);
        Pattern word = Pattern.compile(nameValidate);
        //CHECKED THE DOCTOR ARRAY IS LESS THAN THE 10 USING "if" CONDITION.
        if(10>=doctorArray.size()) {
            System.out.println("------------------------ADD DOCTORS------------------------");
            System.out.print("enter your name : ");
            //GOT THE USER INPUTS USING SCANNER OBJECT AND USED THE trim() KEYWORD TO IGNORE TO SPACE
            String name = scanner.next().trim();
            //CHECKING NAME IS VALID OR NOT USING REGEX
            if(word.matcher(name).matches()) {
                System.out.print("enter your surname : ");
                //GOT THE USER INPUTS USING SCANNER OBJECT AND USED THE trim() KEYWORD TO IGNORE TO SPACE
                String surname = scanner.next().trim();
                //CHECKING SURNAME IS VALID OR NOT USING REGEX
                if(word.matcher(surname).matches()){
                    System.out.print("Enter date of birth(YYYY-MM-DD) : ");
                    //GOT THE USER INPUTS USING SCANNER OBJECT , USED THE trim() KEYWORD TO IGNORE TO SPACE AND USER INPUT COVERT TO LocalDate DATA TYPE
                    LocalDate dateOfBirth = LocalDate.parse(scanner.next());
                    //CHECKING WHETHER AGE ABOVE 22 AND BELOW 65 YEARS OF BIRTH IS ENTERED
                    if (dateOfBirth.isAfter(LocalDate.now().minusYears(65)) && dateOfBirth.isBefore(LocalDate.now().minusYears(20))) {
                        System.out.print("Enter mobile number: ");
                        //GOT THE USER INPUTS USING SCANNER OBJECT AND USED THE trim() KEYWORD TO IGNORE TO SPACE
                        String mobile = scanner.next().trim();
                        //CHECKING MOBILE NUMBER IS VALID OR NOT USING REGEX
                        if (num.matcher(mobile).matches()) {
                            System.out.print("Enter the licence : ");
                            //GOT THE USER INPUTS USING SCANNER OBJECT AND USED THE trim() KEYWORD TO IGNORE TO SPACE
                            String licence = scanner.next().trim();
                            //CHECKING ENTERED DOCTOR LICENCE IS ALREADY EXIST OR NOT
                            for (Doctor doctor : doctorArray) {
                                if (licence.equals(doctor.getMedicalLicence())) {
                                    System.out.println("\n-Medical Licence Already Exists-");
                                    virgin = false;
                                    break;
                                }
                            }
                            if (virgin) {
                                System.out.print("specialisation : ");
                                String spec = scanner.next().trim();
                                //CREATE A NEW OBJECT
                                doctorArray.add(new Doctor(name, surname, dateOfBirth, mobile, licence, spec));
                                System.out.println("\nDoctor Details Added Successfully!!");
                            }
                        } else {
                            System.out.println("Check entered phone number!!!");
                        }
                    } else {
                        System.out.println("Check your birthday!!");
                    }
                } else {
                    System.out.println("Check entered Surname !!!!");
                }
            }else {
                System.out.println("Check entered  First name !!!!");
            }
        }else{
            System.out.println("A maximum of 10 doctors can be added");
        }
    }

    // CREATED A DELETE-DOCTOR METHOD TO DELETE DOCTOR'S DETAILS
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
            //GOT THE USER INPUTS USING SCANNER OBJECT AND USED THE trim() KEYWORD TO IGNORE TO SPACE
            String dele = scanner.next().trim();
            for(int i = 0 ; i < doctorArray.size() ; i++){
                //CHECKING ENTERED LICENCE WITH EXIST LICENCE IN ARRAYLIST
                if (dele.equals(doctorArray.get(i).getMedicalLicence())){
                    System.out.print("""
                            -----------------------------------------------------------------------------------------
                                                            DELETED DOCTOR'S DETAILS
                            -----------------------------------------------------------------------------------------
                            """);
                    //CREATING A TABLE USING printf
                    System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n", "Name", "SurName", "Birthday","Mobile-NO","Licence","Specialisation");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    //GET DETAILS FROM ARRAY LIST WHOM WE GOING TO DELETE AND PRINT IN CONSOLE
                    System.out.printf("| %-10s | %-10s | %-10s | %-13s | %-13s | %-14s |%n",doctorArray.get(i).getName(),doctorArray.get(i).getSurname(),doctorArray.get(i).getDateOfBirth(),doctorArray.get(i).getMobileNo(),doctorArray.get(i).getMedicalLicence(),doctorArray.get(i).getSpecialisation());
                    System.out.println("-----------------------------------------------------------------------------------------\n");
                    //REMOVE DOCTOR DETAILS FORM ARRAYLIST
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
                                               Details of remaining\s""" +doctorArray.size()+" doctors can be seen below\n"+ """
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
                    //CHECKING ENTERED LICENCE NUMBER ISN'T EXIST IN  ARRAYLIST OR NOT. USING 'if' CONDITION.
                    if(i == doctorArray.size()-1){
                        System.out.println("Not exist any doctor using this licence ");
                    }
                }
            }
            //CHECK ARRAYLIST IS 0 OR NOT USING if CONDITION.
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
        //SAVE DATA INTO THE FILE USING BUFFER READER
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
            //CLOSING THE OPENED FILE
            writer.close();
            temp.close();
        }catch (IOException e){
            System.out.println("Something Wrong !!!!! ");
        }
    }
    //MADE A METHOD TO READ FILE AND PRINT DETAILS ON CONSOLE
    @Override
    public void ReadFile() {
        //READ FILE DATA USING BUFFER READER
        try {
            String line ;
            BufferedReader reader  = new BufferedReader(new FileReader("details.txt"));
            //USED WHILE LOOP TO RUN AGAIN AND AGAIN UNTIL FINISH LINES IN THE TXT FILE
            while ((line= reader.readLine()) != null){
                System.out.println(line);
            }
            //CLOSE THE READIED FILE
            reader.close();
        }catch (IOException e){
            System.out.println("Something Wrong !!!!! ");
        }
    }
    //CREATED A METHOD TO DOCTOR'S DETAILS FROM DETAILS FILE.
    @Override
    public void loaddata() {
            try {
                //CREATED A VARIABLE TO STORE INPUT ONE BY ONE
                String tempword;
                //AND CREATED A ARRAYLIST TO STORE WORD WHAT SAVE IN "tempword"
                ArrayList<String> tempArray = new ArrayList<>();
                ////READ FILE DATA USING BUFFER READER
                BufferedReader tempre = new BufferedReader(new FileReader("temp.txt"));
                while ((tempword = tempre.readLine()) != null) {
                    if (tempword.equals("")) {
                        continue;
                    } else {
                        //STORE DATA INTO THE ARRAY LIST
                        tempArray.add(tempword);
                    }
                }
                //CHECKS THE temparray ARRAYLIST IS EMPTY OR NOT
                if(tempArray.size()==0){
                    System.out.println("\nNO old data found!!!!! ");
                }else {
                    //RUN THE WHILE LOOP UNTIL temparray SIZE "0"
                    while (0 < (tempArray.size() / 6)) {
                        if (doctorArray.size() == 0) {
                            doctorArray.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                            tempArray.subList(0, 6).clear();
                        } else {
                            //CREATED A ARRAYLIST TO SAVE DOCTOR'S SURNAMES
                            ArrayList<String> doctorID = new ArrayList<>();
                            //GET SURNAME FROM doctorArray LIST
                            for (Doctor value : doctorArray) {
                                doctorID.add(value.getMedicalLicence());
                            }
                            if (doctorArray.size() <= 10) {
                                boolean virgin = true;

                                //REMOVING DUPLICATED SURNAMES USING CONTAINS
                                for (int i = 0 ; doctorID.size()>i;i++) {
                                    if (tempArray.size() > 0) {
                                        if (doctorID.contains(tempArray.get(4))) {
                                            virgin = false;
                                            tempArray.subList(0, 6).clear();
                                        } else {
                                            virgin = true;
                                            break;
                                        }
                                    } else {
                                        virgin = false;
                                        break;
                                    }
                                }
                                if (virgin) {
                                    //ADD DATA IN TO THE doctorArray OBJECT ARRAY LIST
                                    doctorArray.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                                    tempArray.subList(0, 6).clear();
                                }
                            }
                        }
                    }
                }
                tempre.close();
            } catch (Exception e) {
                System.out.println("Something Wrong!!!!! ");
            }
        }
    }

