package com.example.demo.serviceimpl;


import java.io.File;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mytools.FileIO;
import com.example.demo.mytools.JsonTool;
import com.example.demo.mytools.MarkTool;
import com.example.demo.service.FileService;
@Service
public class FileServiceImpl implements FileService {
	

	@Override
	public String read(String filename) throws Exception {
		// TODO Auto-generated method stub
		String body;
		body=FileIO.readArticle(FileService.filepath+filename);
		return body;
	}

	@Override
	public void saveArticle(String body)throws Exception {
		// TODO Auto-generated method stub
		int count;
		LocalDate time=LocalDate.now();
		//判断文章统计文件和日期文件夹是否存在
		File f=new File(FileService.filepath+time);
		if(!f.exists())
		{
			f.mkdir();
		}
		f=new File(FileService.filepath+time+"/articles");
		if(!f.exists())
		{
			f.mkdir();
		}
		f=new File(FileService.filepath+time+"/articleCount");
		if(!f.exists())
		{
			f.createNewFile();
			FileIO.write(FileService.filepath+time+"/articleCount", "0");
		}
		//判断文章统计文件是否存在
		//判断摘要文件夹
		f=new File(FileService.filepath+time+"/idea");
		{
			if(!f.exists())
			{
				f.mkdir();
			}
		}
		//删除总的摘要文件，让其刷新
		f=new File(FileService.filepath+"/All");
		if(f.exists())
		{
			f.delete();
		}
		//判断摘要文件夹
		try {
			//读取文章统计文件并+1写入
			count=Integer.valueOf(FileIO.read(FileService.filepath+time+"/articleCount"));
			count++;
			FileIO.write(FileService.filepath+time+"/articleCount", String.valueOf(count));
			//读取文章统计文件并+1写入
			//写入文件
			f=new File(FileService.filepath+time+"/articles/"+count);
			f.createNewFile();
			FileIO.write(FileService.filepath+time+"/articles/"+count,body);
			//写入文件
			//写入摘要文件
			String idea;
			if(body.length()<153)
			{
				idea=body.substring(0,body.length()-1);
			}else
			{
				idea=body.substring(0,153);
			}
			idea=MarkTool.analyse(idea);
			//第一行写标题，第二行为大意
			f=new File(FileService.filepath+time+"/idea/"+count);
			f.createNewFile();
			FileIO.write(FileService.filepath+time+"/idea/"+count, idea);
			//写入摘要文件
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public String saveImg(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		int count;
		
		LocalDate time=LocalDate.now();
		File f=new File(FileService.filepath+time);
		//新建一个日期的文件夹
		if(!f.exists())
		{
			f.mkdir();
		}
		//新建images文件夹
		f=new File(FileService.filepath+time+"/images");
		if(!f.exists())
		{
			f.mkdir();
		}
		//创建image累计文件
		f=new File(FileService.filepath+time+"/imgCount");
		if(!f.exists())
		{
			f.createNewFile();
			FileIO.write(FileService.filepath+time+"/imgCount", "0");
		}
		try {
			//读取图片累计数量再加+1后写入
			count=Integer.valueOf(FileIO.read(filepath+time+"/imgCount"));
			count++;
			FileIO.write(filepath+time+"/imgCount", String.valueOf(count));
			//读取图片累计数量再加+1后写入
			//写入jpg文件
			f=new File(filepath+time+"/images/"+count+".jpg");
			f.createNewFile();
			FileIO.write(filepath+time+"/images/"+count+".jpg",file);
			//写入jpg文件
			return time+"/images/"+count+".jpg";
		}catch(Exception e){}
		return null;
	}

	@Override
	public String readAll(int count) throws Exception {
		// TODO Auto-generated method stub
		File f=new File(FileService.filepath+"/All");
		String json;
		if(f.exists())//如果存在这个文件
		{
			json=FileIO.read(FileService.filepath+"/All");
			return json;
		}else//否则遍历所有的文件,转成json字符串并写入All文件
		{
			f=new File(FileService.filepath);
			json=JsonTool.toJsonstring(FileIO.readAll(f.listFiles()));
			if(json!=null)
			{
			FileIO.write(FileService.filepath+"/All", json);
			}
			return json;
		}
	}

	@Override
	public void deleteA(String path,String date) throws Exception {
		// TODO Auto-generated method stub
		String name;
		File f=new File(FileService.filepath+path);
		name=f.getName();
		if(f.exists())
		{
			f.delete();
		}
		f=new File(FileService.filepath+date+"/idea/"+name);
		if(f.exists())
		{
			f.delete();
		}
		f=new File(FileService.filepath+"/All");
		if(f.exists())
		{
			f.delete();
		}
	}
}
