import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileTest {

	public static void main(String args[]) throws Exception{
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
	
	
}
