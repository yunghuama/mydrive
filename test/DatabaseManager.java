import com.platform.domain.Question;
import com.platform.util.UUIDGenerator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created with IntelliJ IDEA.
 * User: cheney
 * Date: 13-6-24
 * Time: 下午8:56
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseManager {

    public enum  QUESTION_TYPE {
        CAR,BUS,TRUCK,MOTO,Q3
    }

    /**
     * 导入用户
     * @throws Exception
     */
      @Test
      public void testImportUsersFromXls() throws Exception{
           String path  = "insert into studentcard(number,password,createtime) values(?,?,?)";
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver","root","colon");
           String sql = "";
           PreparedStatement ps =  conn.prepareStatement(sql);
          try{
              //如果是xls
              FileInputStream f = new FileInputStream(path);
              HSSFWorkbook wb = new HSSFWorkbook(f);
              HSSFSheet sheet = wb.getSheetAt(0);
              int rowNum = sheet.getLastRowNum();
              //如果没有错误,则进行导入
              for(int i=1;i<=rowNum;i++){
                  HSSFRow row = sheet.getRow(i);
                  HSSFCell number = row.getCell((short)0);
                  HSSFCell password = row.getCell((short)1);



              }
              System.out.println("导入结束");
          }catch(Exception e){
              e.printStackTrace();
          }
      }

    /**
     * 导入用户 From XLSX
     * @throws Exception
     */
       @Test
       public void testImportUsersFromXlsx() throws Exception{
        String path  = "/Users/cheney/Downloads/student.xlsx";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver","root","colon");
        String sql = "insert into studentcard(id,number,password,schoolId,begindate,remindtimes,reminddays,createtime) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps =  conn.prepareStatement(sql);
        System.out.println("开始导入...");
        try{
            FileInputStream f = new FileInputStream(path);
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(f);
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            int rowNum = hssfSheet.getLastRowNum();
            for(int i=1;i<=rowNum;i++){
                XSSFRow row = hssfSheet.getRow(i);
                XSSFCell number = row.getCell(0);
                XSSFCell password = row.getCell((short)1);

             System.out.println("import:"+number.getStringCellValue() + "-" +password.getStringCellValue().toLowerCase());
                ps.setString(1, UUIDGenerator.generate());
                ps.setString(2, number.getStringCellValue());
                ps.setString(3,password.getStringCellValue().toLowerCase());
                java.util.Date dd = new java.util.Date();
                ps.setString(4,"4028813518f35feb0118f392eee50046");
                ps.setDate(5,new Date(dd.getTime()));
                ps.setInt(6,5);
                ps.setInt(7,3);
                ps.setDate(8,new Date(dd.getTime()));
                ps.execute();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
           ps.close();
           conn.close();
    }

        /**
         * 导入问题
         * @throws Exception
         */
      @Test
      public void testImportQuestion() throws Exception{
          String path  = "/Users/cheney/Downloads/driver/car.xls";
          Class.forName("com.mysql.jdbc.Driver");
          QUESTION_TYPE qt = QUESTION_TYPE.CAR;
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver","root","colon");
          String sql = "";

          switch (qt){
              case CAR: {

              }
              break;
              case BUS : {

              }
              break;
              case TRUCK: {

              }
              break;
              case MOTO: {

              }
              break;
              case Q3: {

              }
              break;
          }
          sql = "insert into questions_car(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,tips,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";


          PreparedStatement ps =  conn.prepareStatement(sql);
          System.out.println("开始导入...");
          FileInputStream f = new FileInputStream(path);
          HSSFWorkbook wb = new HSSFWorkbook(f);
          HSSFSheet sheet = wb.getSheetAt(0);
          int rowNum = sheet.getLastRowNum();
          //如果没有错误,则进行导入
          for(int i=1;i<=rowNum;i++){
              HSSFRow row = sheet.getRow(i);
              HSSFCell code1 = row.getCell((short)0);
              HSSFCell question = row.getCell((short)1);
              HSSFCell a = row.getCell((short)2);
              HSSFCell b = row.getCell((short)3);
              HSSFCell c = row.getCell((short)4);
              HSSFCell d = row.getCell((short)5);
              HSSFCell answer = row.getCell((short)6);
              HSSFCell image = row.getCell((short)7);
              HSSFCell category = row.getCell((short)8);

              Question q = new Question();
              q.setCode(code1.getStringCellValue());
              q.setQuestion(question.getStringCellValue());
              q.setAnswer_a(a.getStringCellValue());
              q.setAnswer_b(b.getStringCellValue());
              q.setAnswer_c(c==null? "" : c.getStringCellValue());
              q.setAnswer_d(d==null? "" :d.getStringCellValue());
              q.setAnswer(answer.getStringCellValue());
              q.setCategory(category==null? "":category.getStringCellValue());
              q.setImage(image==null?"":image.getStringCellValue());




              System.out.println(i+"---"+image);
            }

          System.out.println("导入结束");

      }



}
