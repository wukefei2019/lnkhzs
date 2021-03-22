package com.ultrapower.eoms.common.org.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ultrapower.omcs.base.web.BaseAction;

public class DownloadAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3386911481997812353L;
	
	
	public String downloadFlash() {
	
		String realPath = this.getRequest().getSession()
				.getServletContext().getRealPath("WEB-INF/file/");
		String fileName = "flashplayer28ax_va_install.exe";
		File file = new File(realPath, fileName);
		 if(file.exists())
		 {
			 this.getResponse().setContentType("application/force-download");// 设置强制下载不打开
			 this.getResponse().addHeader("Content-Disposition","attachment;fileName=" + fileName);// 设置文件名
			 byte[] buffer = new byte[1024];
			 FileInputStream fis = null;
			 BufferedInputStream bis = null;
			 try
			 {
				 fis = new FileInputStream(file);
				 bis = new BufferedInputStream(fis);
				 OutputStream os = this.getResponse().getOutputStream();
				 int i = bis.read(buffer);
				 while (i != -1) {
					 os.write(buffer, 0, i);
					 i = bis.read(buffer);
				 } 
			 }catch(Exception e)
			 {
				 // TODO: handle exception
				 e.printStackTrace();
			 }finally
			 {
				 if (bis != null) {
					try {
					 bis.close();
					} catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					}
				}
				 
				 if (fis != null) {
					try {
					 fis.close();
					} catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 }
				}
			 }
		 }
		return null;
	}

	
	

}
