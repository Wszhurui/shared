package com.example.demo.mytools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Idea;
import com.example.demo.service.FileService;

public class FileIO {
	//写入普通文本
	public static void write(String filepath,String string)
	{
		FileOutputStream fos=null;
		BufferedOutputStream out=null;
		try {
			fos=new FileOutputStream(filepath);
			out=new BufferedOutputStream(fos);
			out.write(string.getBytes());
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			if(fos!=null)
		try {
				out.flush();
				fos.close();
				}catch(Exception e) {}
		}
	}
	//写入文件
	public static void write(String filepath,MultipartFile file)
	{
		FileOutputStream fos=null;
		BufferedOutputStream out=null;
		try {
			fos=new FileOutputStream(filepath);
			out=new BufferedOutputStream(fos);
			out.write(file.getBytes());
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			if(fos!=null)
		try {
				out.flush();
				fos.close();
				}catch(Exception e) {}
		}
	}
	
	//读普通文件
	public static String read(String path)
	{
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		String line;
		try {
			fis=new FileInputStream(path);
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			while((line=br.readLine())!=null)
			{
				sb.append(line);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			if(fis!=null)
			{
				try {
					fis.close();
				}catch(Exception e) {}
			}
		}
		return sb.toString();
	}
	//读格式文件
	public static String readArticle(String path)
	{
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		String line;
		try {
			fis=new FileInputStream(path);
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			while((line=br.readLine())!=null)
			{
				sb.append(line);
				sb.append("\n");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			if(fis!=null)
			{
				try {
					fis.close();
				}catch(Exception e) {}
			}
		}
		return sb.toString();
	}
	//特殊用途
	public static List<Idea> readAll(File[] dirs)
	{
		List<Idea> ideas=new ArrayList<Idea>();
		File f;
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		String line;
		
		for(File i: dirs)
		{
			f=new File(i.getPath()+"/idea");
			File[] files=f.listFiles();
			if(files==null)
				return null;
			for(File j: files)
			{
				Idea a=new Idea();
				a.setDate(i.getName());
				a.setPath(a.getDate()+"/articles/"+j.getName());
				try {
					fis=new FileInputStream(FileService.filepath+a.getDate()+"/idea/"+j.getName());
					isr=new InputStreamReader(fis);
					br=new BufferedReader(isr);
					a.setTitle(br.readLine());//第一行是标题
					while((line=br.readLine())!=null)//剩下的是摘要
					{
						sb.append(line);
					}
					a.setIdea(sb.toString());
					ideas.add(a);
					sb.delete(0, sb.length());
				}catch(Exception e)
				{
					e.printStackTrace();
				}finally {
					if(fis!=null)
					{
						try {
							fis.close();
						}catch(Exception e) {}
					}
				}
			}
		}
		return ideas;
	}
}
