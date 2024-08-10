package org.example;

import java.sql.*;
import java.util.Scanner;

public class student {
    public static Connection connention(){
        String url="jdbc:mysql://localhost:3306/sithajava";
        String username="root";
        String password="";
        try{
//            System.out.print("connet to database");
            return DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        student.connention();
        Scanner sc=new Scanner(System.in);
        int op,n;
        do{
            System.out.println("-------------- MENU -----------------");
            System.out.println("1. INSERT");
            System.out.println("2. SECTLECT");
            System.out.println("3. SEACH BY ID");
            System.out.println("4. SEACH BY NAME");
            System.out.println("5. UPDATE BY ID");
            System.out.println("6. DELECT BY ID");
            System.out.println("7. SORT BY ID DESC");
            System.out.println("8. SEACH BY MANE A->Z");
            System.out.println("1. Please choose option :");
            op=sc.nextInt();
            switch (op){
                case 1->{
                    String name,gender;
                    float score1,score2,score3,total,average;
                    char grade;
                    System.out.println("--------------------- Insert Data ------------------- ");
                    System.out.println(" Input number of student :");
                    n=sc .nextInt();
                    for(int i=0;i<n;i++){
                        System.out.println("Input Name :");sc.nextLine(); name=sc.nextLine();
                        System.out.println("Input Gender :");gender=sc.next();
                        System.out.println("Input Score1 :");score1=sc.nextFloat();
                        System.out.println("Input Score2 :");score2=sc.nextFloat();
                        System.out.println("Input Score3 :");score3=sc.nextFloat();
                        total=score1+score2+score3;
                        average=total/3;
                        grade =(average>=90)?'A': (average>=80)?'B': (average>=70)?'C': (average>=60)?'D':(average>=50)?'E':
                                'F';
                        String  sql="INSERT INTO tbt_student(name, gender, score1, score2, score3, total, average, grade) VALUES (?,?,?,?,?,?,?,?)";
                        try{
                            Connection data=connention();
                            PreparedStatement ps= data.prepareStatement(sql);
                            ps.setString(1,name);
                            ps.setString(2,gender);
                            ps.setFloat(3,score1);
                            ps.setFloat(4,score2);
                            ps.setFloat(5,score3);
                            ps.setFloat(6,total);
                            ps.setFloat(7,average);
                            ps.setString(8,grade+"");
                            ps.executeUpdate();


                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        System.out.println("Insert Successfully");

                    }
                }
                case 2-> {
                    System.out.println("-------------OUTPUT---------------- ");
                    try {
                        Connection data=connention();
//
                        String sql="SELECT * FROM tbt_student";
                        Statement st= data.createStatement();
                        ResultSet rs=st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID   ="+rs.getInt("id"));
                            System.out.println("Name ="+rs.getString("name"));
                            System.out.println("Gender ="+rs.getString("gender"));
                            System.out.println("score1 ="+rs.getFloat("score1"));
                            System.out.println("Score 2="+rs.getFloat("score2"));
                            System.out.println("Score 3="+rs.getFloat("score3"));
                            System.out.println("Total ="+rs.getFloat("total"));
                            System.out.println("Average ="+rs.getFloat("average"));
                            System.out.println("grade");

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("Insert Successfully");
                }
            }

        }while(true);
    }
}