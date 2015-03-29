package z.mobile.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import z.mobile.model.MobileUser;
import z.mobile.service.MobileUserService;
import z.mobile.service.impl.MobileUserServiceImpl;

public class MobileUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MobileUserService userService;
	
	public void register() {
		System.out.println("register.action");
		userService = new MobileUserServiceImpl();
		Gson gson = new Gson();
		String email = request().getParameter("email").toString();
		String password = request().getParameter("password").toString();
		String name = request().getParameter("name").toString();
		MobileUser mobileUser = new MobileUser(email, password, name);
		boolean result = userService.addUser(mobileUser);
		if(result) {
			mobileUser = userService.getUserByEmail(mobileUser.getEmail());
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<MobileUser>() {  
	        }.getType();  
			String mobileUserToJson = gson.toJson(mobileUser, type);
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            System.out.println(mobileUserToJson);
	            this.response().getWriter().write(mobileUserToJson);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		} else {
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"error\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		}
	}

	public void login() {
		System.out.println("login.action");
		userService = new MobileUserServiceImpl();
		Gson gson = new Gson();
		String email = request().getParameter("email").toString();
		String password = request().getParameter("password").toString();
		boolean result = userService.checkAccount(email, password);
		if(result) {   
			MobileUser mobileUser = userService.getUserByEmail(email);
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<MobileUser>() {  
	        }.getType();  
			String mobileUserToJson = gson.toJson(mobileUser, type);
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            System.out.println(mobileUserToJson);
	            this.response().getWriter().write(mobileUserToJson);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        } 
		} else {
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"error\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		}
	}
	
	public void updateByName() {
		System.out.println("updateByName.action");
		userService = new MobileUserServiceImpl();
		int id = Integer.parseInt(request().getParameter("id").toString());
		String name = request().getParameter("name").toString();
		MobileUser mobileUser = userService.getUserById(id);
		boolean result = false;
		if(mobileUser != null) {
			mobileUser.setName(name);
			result = userService.updateUser(mobileUser);
		}
		if(result) {   
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"success\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		} else {
			try {    
	            response().setContentType("text/xml;charset=utf-8");  
	            String jsonResult = "{\"result\":\"error\"}";
	            System.out.println(jsonResult);
	            this.response().getWriter().write(jsonResult);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }
		}
	}
	
	public void uploadImage() {
		System.out.println("uploadImage.action");
		
//		System.out.println("contentType:"+request().getContentType());
//		System.out.println("body:"+request().getParameter("body"));
//		System.out.println("uid:"+request().getParameter("userId"));
//		System.out.println("file:"+request().getParameter("file"));
//		System.out.println("imageData:"+request().getParameter("imageData"));
//		String imageData = request().getParameter("imageData");
		
		try {
			String path = "/Users/aecdvkl/Desktop/test.png";
			BufferedImage bImageFromConvert = ImageIO.read(request().getInputStream());
			ImageIO.write(bImageFromConvert, "png", new File(path));
			System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failure");
			e.printStackTrace();
		}
		
//		//Set Response Type
//		response().setContentType("text/html;charset=UTF-8");
//		//Use out to send content to the user's browser
//		PrintWriter out = null;
//		try {
//			out = response().getWriter();
//		 //Get the name of the file from the URL string
////		 String name = (String)request().getParameter("filename");
////		 if (name == null || name.length() < 3) {
////		 out.println("Failure: No filename given");
////		 System.out.println("failure: no filename given");
////		 } else {
//		 
//			//Create an input stream from our request.
//			//This input stream contains the image itself.
//			DataInputStream din = new DataInputStream(request().getInputStream());
//			byte[] data = new byte[0];
//			byte[] buffer = new byte[512];
//			int bytesRead;
//			while ((bytesRead = din.read(buffer)) > 0) {
//				System.out.print("Y");
//				// construct an array large enough to hold the data we currently have
//				byte[] newData = new byte[data.length + bytesRead];
//				// copy data that was previously read into newData
//				System.arraycopy(data, 0, newData, 0, data.length);
//				// append new data from buffer into newData
//				System.arraycopy(buffer, 0, newData, data.length, bytesRead);
//				// set data equal to newData in prep for next block of data
//				data = newData;
//			} 
//			//define the path to save the file using the file name from the URL.
//			String path = "/Users/aecdvkl/Desktop/test.png";
//		 
//			InputStream in = new ByteArrayInputStream(data);
//			BufferedImage bImageFromConvert = ImageIO.read(in);
//		 
//			ImageIO.write(bImageFromConvert, "png", new File(path));
//			out.println("Success");
//			System.out.println("success");
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 System.out.println("failure");
//		 out.println("Failure");
//		 } finally {
//		 out.close();
//		 }
		
//		String inputStream = "";
//		
//		try {
//			int length = request().getContentLength();
//			System.out.println("length:"+length);
//			byte[] buffer = new byte[length];
//			ServletInputStream in = request().getInputStream();
//			for(int i = 0; i<length; i++) {
//				buffer[i] = (byte) in.read();
////				System.out.println(buffer[i]);
//			}
//			FileOutputStream output = new FileOutputStream("/Users/aecdvkl/Desktop/test.doc");  
//			
//	        output.write(buffer); 
//	        output.close();
//	        System.out.println("upload over");
		 
		 
////			System.out.println("inStream:"+IOUtils.toString(in));
//			
//			String str = readLine(in);// 这里是前台发起的所有参数的值，包括二进制形式的文件和其它形式的文件
//			System.out.println(str);
//			 int size = request().getContentLength(); // 取HTTP请求流长度
//		        byte[] buffer = new byte[size]; // 用于缓存每次读取的数据 
//		        byte[] in_b = new byte[size]; // 用于存放结果的数组
//		        int count = 0;
//		        int rbyte = 0;
//		        // 循环读取 
//		        System.out.println("size:"+size);
//		        while (count < size)
//		        { 
//		            rbyte = in.read(buffer); // 每次实际读取长度存于rbyte中 sflj
//		            for (int i = 0; i < rbyte; i++)
//		            {
//		            	System.out.print(buffer[i]);
//		                in_b[count + i] = buffer[i];
//		            }
//		            count += rbyte;
//		        }
//		        System.out.println("");
////		        String result = "";
////		        System.out.println("in_b.length:"+in_b.length);
//		        String result = new String(in_b, "US-ASCII");
////		        for(int i = 0; i<in_b.length; i++) {
////		        	result += in_b[i];
////		        }
//		        System.out.println("result:" + result);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		System.out.println("inputStream:"+inputStream);
////		request().get
	}
	
	private String readLine(ServletInputStream in) throws IOException {
		  byte[] buf = new byte[8 * 1024];
		  StringBuffer sbuf = new StringBuffer();
		  int result;
		  // String line;

		  do {
		   result = in.readLine(buf, 0, buf.length); // does +=
		   if (result != -1) {
		    sbuf.append(new String(buf, 0, result, "UTF-8"));
		   }
		  } while (result == buf.length); // loop only if the buffer was filled

		  if (sbuf.length() == 0) {
		   return null; // nothing read, must be at the end of stream
		  }

		  // Cut off the trailing \n or \r\n
		  // It should always be \r\n but IE5 sometimes does just \n
		  int len = sbuf.length();
		  if (sbuf.charAt(len - 2) == '\r') {
		   sbuf.setLength(len - 2); // cut \r\n
		  } else {
		   sbuf.setLength(len - 1); // cut \n
		  }
		  return sbuf.toString();
		 }
}
