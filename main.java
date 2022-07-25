import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class main {
    public void connect() throws Exception
    {
        try {
            Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc";
            String Uname = "root";
            String Pass = "system";
            con = DriverManager.getConnection(url,Uname,Pass);
            con.close();

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void menu() throws Exception
    {
        try {
            String U_name;
            String Password;
            String password;
            String Uname;

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("*** Welcome Admin *****");
            System.out.println("-------------------------");
            System.out.println("Set your UserName :");
            U_name = sc.next();
            System.out.println("Set your Password");
            Password = sc.next();
            System.out.println("\t---------------------------------");
            System.out.println("\t    COLLEGE ENROLLMENT SYSTEM    ");
            System.out.println("\t---------------------------------");
            System.out.println("\t1.Admin Login");
            System.out.println("\t2.Student Login");
            System.out.println("\t3.Exit");
            System.out.print("\tCHOOSE:");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter USERNAME :");
                    Uname = sc.next();
                    System.out.println("Enter PASSWORD :");
                    password = sc.next();
                    if (U_name.equals(Uname) && Password.equals(password)) {
                        System.out.println("Login Successfully....");
                        int choice = 0 ;
                        while (choice != 0) {

                            System.out.println("\tPress 1.Add New Student ");
                            System.out.println("\tPress 2.Delete Student Record");
                            System.out.println("\tPress 3.Search Student Record ");
                            System.out.println("\tPress 4.Update Student Record ");
                            System.out.println("\tPress 5.List ALl Student Record ");
                            System.out.println("\tPress 0.Exit ");
                            System.out.println("\tCHOOSE: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    Class.forName("com.mysql.jdbc.Driver");
                                    String url = "jdbc:mysql://localhost:3306/jdbc";
                                    String User_name = "root";
                                    String Pass = "system";
                                    Connection con = DriverManager.getConnection(url, User_name, Pass);

                                    String insert = "insert into student(ID,enroll_no,stud_name,stud_cast,stud_cast_cat,stud_fees,Branch) values (?,?,?,?,?,?,?) ";

                                    PreparedStatement pstm = con.prepareStatement(insert);

                                    int ID = 0;
                                    System.out.println("Enter Student Name:");
                                    String s_name = sc1.nextLine();
                                    System.out.println("Enter Student Enrollment No:");
                                    String enroll_no = sc.next();
                                    System.out.println("Enter Student Branch:");
                                    String branch = sc.next();
                                    System.out.println("Enter Student Cast :");
                                    String cast = sc2.nextLine();
                                    System.out.println("Enter Student Cast Category:");
                                    String cast_cat = sc.next();
                                    System.out.println("Enter Student College Fees:");
                                    int fees = sc.nextInt();

                                    pstm.setInt(1, ID);
                                    pstm.setString(2, enroll_no);
                                    pstm.setString(3, s_name);
                                    pstm.setString(4, cast);
                                    pstm.setString(5, cast_cat);
                                    pstm.setInt(6, fees);
                                    pstm.setString(7, branch);

                                    pstm.executeUpdate();

                                    System.out.println("Record saved...");

                                    con.close();

                                    break;
                                case 2:

                                    Class.forName("com.mysql.jdbc.Driver");
                                    String url1 = "jdbc:mysql://localhost:3306/jdbc";
                                    String User_name1 = "root";
                                    String Pass1 = "system";
                                    Connection conn = DriverManager.getConnection(url1, User_name1, Pass1);


                                    String delete = "delete from student where id = ?";

                                    PreparedStatement pstmt = conn.prepareStatement(delete);

                                    System.out.println("Enter Student ID:");
                                    int id = sc.nextInt();

                                    pstmt.setInt(1, id);

                                    int row = pstmt.executeUpdate();
                                    if (row > 0) {
                                        System.out.println("Record Deleted Successfully..");
                                    } else {
                                        System.out.println("Record Does not exist!!");
                                    }
                                    conn.close();

                                    break;
                                case 3:

                                    Class.forName("com.mysql.jdbc.Driver");
                                    String url2 = "jdbc:mysql://localhost:3306/jdbc";
                                    String User_name2 = "root";
                                    String Pass2 = "system";
                                    Connection conn1 = DriverManager.getConnection(url2, User_name2, Pass2);

                                    System.out.println("\tPress 1.Search by ID");
                                    System.out.println("\tPress 2.Search by Enrollment No:");
                                    System.out.print("\tCHOOSE:");
                                    int value = sc.nextInt();
                                    if (value == 1) {

                                        System.out.println("Enter Student ID:");
                                        int stud_id = sc.nextInt();

                                        String search = "select * from student where ID = '" + stud_id + "'";
                                        PreparedStatement prstmt = conn1.prepareStatement(search);

                                        ResultSet res = prstmt.executeQuery(search);
                                        while (res.next()) {
                                            int student_ID = res.getInt(1);
                                            String enroll = res.getString(2);
                                            String name = res.getString(3);
                                            String stud_cast = res.getString(4);
                                            String stud_cast_cat = res.getString(5);
                                            int stud_fees = res.getInt(6);
                                            String stud_branch = res.getString(7);

                                            System.out.println("--------------------------------------");
                                            System.out.println("ID           :" + student_ID);
                                            System.out.println("NAME           :" + name);
                                            System.out.println("BRANCH         :" + stud_branch);
                                            System.out.println("ENROLLMENT NO  :" + enroll);
                                            System.out.println("CAST           :" + stud_cast);
                                            System.out.println("CAST CATEGORY  :" + stud_cast_cat);
                                            System.out.println("FEES           :" + stud_fees);
                                            System.out.println("--------------------------------------");
                                        }
                                    } else if (value == 2) {
                                        System.out.println("Enter Student Enrollment No:");
                                        String enrollment = sc1.next();

                                        String search = "select * from student where enroll_no = '" + enrollment + "'";
                                        PreparedStatement prstmt = conn1.prepareStatement(search);

                                        ResultSet res = prstmt.executeQuery(search);

                                        while (res.next()) {
                                            int student_ID = res.getInt(1);
                                            String enroll = res.getString(2);
                                            String name = res.getString(3);
                                            String stud_cast = res.getString(4);
                                            String stud_cast_cat = res.getString(5);
                                            int stud_fees = res.getInt(6);
                                            String stud_branch = res.getString(7);

                                            System.out.println("--------------------------------------");
                                            System.out.println("ID           :" + student_ID);
                                            System.out.println("NAME           :" + name);
                                            System.out.println("BRANCH         :" + stud_branch);
                                            System.out.println("ENROLLMENT NO  :" + enroll);
                                            System.out.println("CAST           :" + stud_cast);
                                            System.out.println("CAST CATEGORY  :" + stud_cast_cat);
                                            System.out.println("FEES           :" + stud_fees);
                                            System.out.println("--------------------------------------");
                                            ;
                                        }
                                    } else {
                                        System.out.println("Enter Valid choice!!");
                                    }
                                    conn1.close();
                                    break;
                                case 4:

                                    Class.forName("com.mysql.jdbc.Driver");
                                    String url3 = "jdbc:mysql://localhost:3306/jdbc";
                                    String User_name3 = "root";
                                    String Pass3 = "system";
                                    Connection conn2 = DriverManager.getConnection(url3, User_name3, Pass3);
                                    System.out.println("Enter Student ID:");
                                    int stud_id = sc.nextInt();

                                    String search = "select * from student where ID = '" + stud_id + "'";
                                    PreparedStatement prstmt = conn2.prepareStatement(search);

                                    ResultSet res = prstmt.executeQuery(search);

                                    while (res.next()) {
                                        int student_ID = res.getInt(1);
                                        String enroll = res.getString(2);
                                        String name = res.getString(3);
                                        String stud_cast = res.getString(4);
                                        String stud_cast_cat = res.getString(5);
                                        int stud_fees = res.getInt(6);
                                        String stud_branch = res.getString(7);


                                        System.out.println("\t--------------------------------------");
                                        System.out.println("\t Previous Data...");
                                        System.out.println("\t--------------------------------------");
                                        System.out.println("\tID           :" + student_ID);
                                        System.out.println("\tNAME           :" + name);
                                        System.out.println("\tBRANCH         :" + stud_branch);
                                        System.out.println("\tENROLLMENT NO  :" + enroll);
                                        System.out.println("\tCAST           :" + stud_cast);
                                        System.out.println("\tCAST CATEGORY  :" + stud_cast_cat);
                                        System.out.println("\tFEES           :" + stud_fees);
                                        System.out.println("\t--------------------------------------");
                                    }
                                    PreparedStatement ps;
                                    System.out.println("Which you wants to update:");
                                    System.out.println("1.Name:");
                                    System.out.println("2.Enrollment No:");
                                    System.out.println("3.Branch");
                                    System.out.println("4.Cast");
                                    System.out.println("5.Cast Category");
                                    System.out.println("6.Fees");
                                    System.out.println("CHOOSE:");
                                    int get = sc.nextInt();
                                    if (get == 1) {
                                        System.out.println("Enter Updated Criminal Name:");
                                        String name = br.readLine();
                                        String sql = "update student set stud_name='" + name + "' where ID = " + stud_id;

                                        ps = conn2.prepareStatement(sql);

                                        int rows = ps.executeUpdate();
                                        if (rows > 0) {
                                            System.out.println("Updated Successfully...");
                                        } else {
                                            System.out.println("Something went wrong..");
                                        }
                                    } else if (get == 2) {
                                        System.out.println("Enter Updated Enrollment NO:");
                                        String enroll = br.readLine();
                                        String sql = "update student set enroll_no='" + enroll + "' where ID = " + stud_id;

                                        ps = conn2.prepareStatement(sql);

                                        int rows = ps.executeUpdate();
                                        if (rows > 0) {
                                            System.out.println("Updated Successfully...");
                                        } else {
                                            System.out.println("Something went wrong..");
                                        }
                                    } else if (get == 3) {
                                        System.out.println("Enter Updated Branch :");
                                        String Branch = br.readLine();
                                        String sql = "update student set Branch='" + Branch + "' where ID = " + stud_id;

                                        ps = conn2.prepareStatement(sql);

                                        int rows = ps.executeUpdate();
                                        if (rows > 0) {
                                            System.out.println("Updated Successfully...");
                                        } else {
                                            System.out.println("Something went wrong..");
                                        }
                                    } else if (get == 4) {
                                        System.out.println("Enter Updated Cast:");
                                        String Cast = br.readLine();
                                        String sql = "update student set stud_cast='" + Cast + "' where ID = " + stud_id;

                                        ps = conn2.prepareStatement(sql);

                                        int rows = ps.executeUpdate();
                                        if (rows > 0) {
                                            System.out.println("Updated Successfully...");
                                        } else {
                                            System.out.println("Something went wrong..");
                                        }
                                    } else if (get == 5) {
                                        System.out.println("Enter Updated Cast Category :");
                                        String Category = br.readLine();
                                        String sql = "update student set stud_cast_cat ='" + Category + "' where ID = " + stud_id;

                                        ps = conn2.prepareStatement(sql);

                                        int rows = ps.executeUpdate();
                                        if (rows > 0) {
                                            System.out.println("Updated Successfully...");
                                        } else {
                                            System.out.println("Something went wrong..");
                                        }
                                    } else if (get == 6) {
                                        System.out.println("Enter Updated Fees :");
                                        String Fees = br.readLine();
                                        String sql = "update student set stud_fees='" + Fees + "' where ID = " + stud_id;

                                        ps = conn2.prepareStatement(sql);

                                        int rows = ps.executeUpdate();
                                        if (rows > 0) {
                                            System.out.println("Updated Successfully...");
                                        } else {
                                            System.out.println("Something went wrong..");
                                        }
                                    }
                                    conn2.close();
                                    break;
                                case 5:
                                    Class.forName("com.mysql.jdbc.Driver");
                                    String url4 = "jdbc:mysql://localhost:3306/jdbc";
                                    String User_name4 = "root";
                                    String Pass4 = "system";
                                    Connection conn3 = DriverManager.getConnection(url4, User_name4, Pass4);
                                    Statement statement = conn3.createStatement();
                                    String p = "Select * from student";
                                    ResultSet rs = statement.executeQuery(p);
                                    System.out.println("+---------+---------------------+--------------------------+----------------+----------------+-----------+----------------+");
                                    System.out.printf("|    %-5s| %-20s| %-25s| %-15s| %-15s| %-10s| %-15s|\n", "ID", "ENROLL", "NAME", "CAST", "CAST CAT", "FEES", "BRANCH");
                                    System.out.println("+---------+---------------------+--------------------------+----------------+----------------+-----------+----------------+");
                                    while (rs.next()) {
                                        System.out.printf("|    %-5d| %-20s| %-25s| %-15s| %-15s| %-10s| %-15s|\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
                                    }
                                    System.out.println("+---------+---------------------+--------------------------+----------------+----------------+-----------+----------------+");

                                    break;
                                case 0:
                                    System.out.println("Thanks..");
                                    break;
                                default:
                                    System.out.println("Enter Valid Option!!!");
                            }
                        }
                    } else {
                        System.out.println("ERROR!!!");
                    }

                    break;
                case 2:
                    System.out.println("\t--------------------------------");
                    System.out.println("\t    *** Welcome Student ***     ");
                    System.out.println("\t--------------------------------");
                    System.out.println("\t1.About Student Info");
                    System.out.println("\t2.See List of student");
                    System.out.println("\t3.Exit...");
                    System.out.println("\tCHOOSE:");
                    int choice = sc.nextInt();
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/jdbc";
                    String User_name = "root";
                    String Pass = "system";
                    Connection con = DriverManager.getConnection(url, User_name, Pass);
                    switch (choice) {
                        case 1:

                            System.out.println("Hey Student!");
                            System.out.println("Enter your Enrollment Number :");
                            int enroll = Integer.parseInt(br.readLine());

                            String sql = "Select * from student where enroll_no = '" + enroll + "'";
                            PreparedStatement ps = con.prepareStatement(sql);

                            ResultSet res = ps.executeQuery();
                            while (res.next()) {
                                int student_ID = res.getInt(1);
                                String enroll_no = res.getString(2);
                                String name = res.getString(3);
                                String stud_cast = res.getString(4);
                                String stud_cast_cat = res.getString(5);
                                int stud_fees = res.getInt(6);
                                String stud_branch = res.getString(7);

                                System.out.println("\t\t+-------------------|-----------------");
                                System.out.println("\t\t|\t  " + name);
                                System.out.println("\t\t+-------------------|----------------");
                                System.out.println("\t\t|\tID              | " + student_ID);
                                System.out.println("\t\t|\tEnrollment No   | " + enroll_no);
                                System.out.println("\t\t|\tCast            | " + stud_cast);
                                System.out.println("\t\t|\tCast Category   | " + stud_cast_cat);
                                System.out.println("\t\t|\tFees            | " + stud_fees);
                                System.out.println("\t\t|\tBranch          | " + stud_branch);
                                System.out.println("\t\t+-------------------|----------------");
                            }
                            con.close();
                            break;
                        case 2:
                            String select = "select * from student";
                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery(select);
                            System.out.println("+---------+---------------------+--------------------------+----------------+");
                            System.out.printf("|    %-5s| %-20s| %-25s| %-15s|\n", "ID", "ENROLL", "NAME", "BRANCH");
                            System.out.println("+---------+---------------------+--------------------------+----------------+");
                            while (rs.next()) {
                                System.out.printf("|    %-5s| %-20s| %-25s| %-15s|\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(7));
                            }
                            System.out.println("+---------+---------------------+--------------------------+----------------+");
                            con.close();
                            break;
                        case 3:
                            System.out.println("Thanks...");
                            break;
                        default:
                            System.out.println("Enter Valid option");
                    }
                case 3:
                    System.out.println("thanks..");
                    break;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws Exception{
        main m1=new main();
        try {
            m1.connect();
            m1.menu();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
