import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.platform.domain.Question;


public class FileTest {

	@Test
	public void checkXls(){
		String xlsPath = "/home/cheney/driver/db/car.xlsx";
		String picPath = "/home/cheney/driver/image/0";
		importQuestion(xlsPath,picPath);
	}
	
	@Test
	public void toLowerCase() throws Exception{
		try{
			String path = "/home/cheney/driver/image/4";
			File file = new File(path);
			File[] files = file.listFiles();
			System.out.println(files.length);
			for(int i=0;i<files.length;i++){
				File f = files[i];
				String name = f.getName();
				if(name.equals(name.toLowerCase())){
					System.out.println(i+"-start:"+name+" 无需转换");
					continue;
				}
				FileInputStream in = new FileInputStream(f);
				File d = new File(path+"/"+name.toLowerCase());
				FileOutputStream out = new FileOutputStream(d);
				byte[] buff =  new byte[1024];
				int length = 0;
				while((length=in.read(buff))!=-1){
					out.write(buff, 0, length);
				}
				out.flush();
				System.out.println(name+"--->"+d.getName());
				f.delete();
				out.close();
				in.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void importQuestion(String path,String picPath){
		if(path.endsWith("xls")){
			importXls(path,picPath);
		}else if(path.endsWith("xlsx")){
			importXlsx(path,picPath);
		}
	}
	
	public void importXls(String path,String picPath){
		try{
			//如果是xls
			FileInputStream f = new FileInputStream(path);
			HSSFWorkbook wb = new HSSFWorkbook(f);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			//如果没有错误,则进行导入
				for(int i=1;i<=rowNum;i++){
					HSSFRow row = sheet.getRow(i);
					HSSFCell code = row.getCell((short)0);
					HSSFCell question = row.getCell((short)1);
					HSSFCell a = row.getCell((short)2);
					HSSFCell b = row.getCell((short)3);
					HSSFCell c = row.getCell((short)4);
					HSSFCell d = row.getCell((short)5);
					HSSFCell answer = row.getCell((short)6);
					HSSFCell image = row.getCell((short)7);
					HSSFCell category = row.getCell((short)8);
					
					Question q = new Question();
					q.setCode(code.getStringCellValue());
					q.setQuestion(question.getStringCellValue());
					q.setAnswer_a(a.getStringCellValue());
					q.setAnswer_b(b.getStringCellValue());
					q.setAnswer_c(c==null? "" : c.getStringCellValue());
					q.setAnswer_d(d==null? "" :d.getStringCellValue());
					q.setAnswer(answer.getStringCellValue());
					q.setCategory(category==null? "":category.getStringCellValue());
					q.setImage(image==null?"":image.getStringCellValue());
					if(image!=null){
					File pic = new File(picPath+"/"+image);
					if(!pic.exists()){
						System.out.println(q.getCode()+" 不存在: "+image);
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	
	public void importXlsx(String path,String picPath){
		try{
			File f1 = new File(path);
			System.out.print(f1.getAbsolutePath());
			FileInputStream f = new FileInputStream(path);
			XSSFWorkbook hssfWorkbook = new XSSFWorkbook(f);
			XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			int rowNum = hssfSheet.getLastRowNum();
			for(int i=1;i<=rowNum;i++){
				XSSFRow row = hssfSheet.getRow(i);
				XSSFCell code = row.getCell(0);
				XSSFCell question = row.getCell((short)1);
				XSSFCell a = row.getCell((short)2);
				XSSFCell b = row.getCell((short)3);
				XSSFCell c = row.getCell((short)4);
				XSSFCell d = row.getCell((short)5);
				XSSFCell answer = row.getCell((short)6);
				XSSFCell image = row.getCell((short)7);
				XSSFCell category = row.getCell((short)8);
				
				Question q = new Question();
				q.setCode(code.getStringCellValue().trim());
				q.setQuestion(question.getStringCellValue().trim());
				q.setAnswer_a(a.getStringCellValue().trim());
				q.setAnswer_b(b.getStringCellValue().trim());
				q.setAnswer_c(c==null? "" : c.getStringCellValue().trim());
				q.setAnswer_d(d==null? "" :d.getStringCellValue().trim());
				q.setAnswer(answer.getStringCellValue().trim());
				q.setCategory(category==null? "":category.getStringCellValue().trim());
				if(image!=null){
					String img = image.getStringCellValue();
					if(img.endsWith("flv")){
						q.setVideo(img);
					}else{
						q.setImage(img);
					}
					File pic = new File(picPath+"/"+img);
					if(!pic.exists()){
						System.out.println(q.getCode()+" 不存在: "+img);
					}
				}
//				q.setImage(image==null?"":image.getStringCellValue().trim());
				//导入
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
