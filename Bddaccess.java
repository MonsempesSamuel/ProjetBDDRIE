import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Bddaccess{

  public String[] select(String table){
    Connection conn = null;
    Statement stmt = null;
    String[] result = null;
    try {
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      ResultSet rs = stmt.executeQuery("Select * From " + table);
      int size = rs.getMetaData().getColumnCount();
      ArrayList<String> auxresult = new ArrayList<String>();
      while(rs.next()){
        for (int i = 1; i <= size; i++){
          auxresult.add(rs.getString(i));
        }
      }
      result = auxresult.toArray(new String[0]);
      rs.close();
      //System.out.println("Table "+table+" selected");
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
    return result;
  }

  public void drop(String table){
    Connection conn = null;
    Statement stmt = null;
    try {
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      stmt.execute("DROP TABLE "+table+" CASCADE CONSTRAINTS");
      //System.out.println("Table "+table+" deleted");
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
  }

  public String[] print_column(String table){
    Connection conn = null;
    Statement stmt = null;
    String[] result = null;
    try {
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      ResultSet rs = stmt.executeQuery("Select COLUMN_NAME  From all_tab_columns WHERE table_name like '" + table.toUpperCase()+"'");
      int size = rs.getMetaData().getColumnCount();
      ArrayList<String> auxresult = new ArrayList<String>();
      while(rs.next()){
        for (int i = 1; i <= size; i++){
          auxresult.add(rs.getString(i));
        }
      }
      result = auxresult.toArray(new String[0]);
      rs.close();
      //System.out.println("Table "+table+" selected");
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
    return result;
  }

  public void insert(String table, String[] option){
    Connection conn = null;
    Statement stmt = null;
    try{
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      String column = "";
      String value = "";
      int i = 0;
      for (i = 0; i < option.length/2; i++){
        column += option[i];
        if (i != (option.length/2)-1) column += ", ";
      }
      for (i = i; i < option.length; i++){
        value += option[i];
        if (i != (option.length-1)) value += ", ";
      }
      stmt.executeUpdate("INSERT INTO "+table+" ("+column+") VALUES ("+value+")");
      //System.out.println("Insert line in table "+table);
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
  }

  public void update(String table, String[] option){
    Connection conn = null;
    Statement stmt = null;
    try {
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      String inSet = "";
      int i = 0;
      for (i = 0; i < option.length; i+=2){
        inSet += option[i] +" = " + option[i+1] ;
        if (i != (option.length-2)) inSet += ", ";
      }
      stmt.executeUpdate("UPDATE "+table+" SET "+inSet+" WHERE "+option[0]+" = "+option[1]);
      //System.out.println("Insert line in table "+table);
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
  }

  public void delete(String table, String primarykey){
    Connection conn = null;
    Statement stmt = null;
    try {
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      stmt.executeUpdate("DELETE FROM "+table+" WHERE "+primarykey);
      //System.out.println("Delete line into table "+table);
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
  }

  public void create(String table, String option){
    Connection conn = null;
    Statement stmt = null;
    try {
			conn = DriverManager.getConnection(
                	"jdbc:oracle:thin:@localhost:1521:xe","system","djamel.2a");
			//System.out.println("Connection etablie......");

			stmt = conn.createStatement() ;
			//System.out.println("Create  Statement......");
      stmt.execute("CREATE TABLE "+table+"( "+option+")");
      //System.out.println("create table "+table);
    }catch(SQLException se){
      //System.out.println(se.getMessage());
    }
    finally{
      try{
        if(stmt!=null)
        conn.close();
      }catch(SQLException se){
      }
    }
  }
}
