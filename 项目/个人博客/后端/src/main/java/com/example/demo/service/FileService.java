package com.example.demo.service;




import org.springframework.web.multipart.MultipartFile;




public interface FileService {
	public String filepath="/home/zhurui/MyWeb/MyWebsite/";
	
	public String read(String filename)throws Exception;
	
	public void saveArticle(String body)throws Exception;
	
	public String saveImg(MultipartFile file)throws Exception;
	
	public String readAll(int count)throws Exception;
	
	public void deleteA(String path,String date) throws Exception;
	
}
