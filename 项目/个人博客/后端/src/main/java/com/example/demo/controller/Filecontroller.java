package com.example.demo.controller;
/*
 * Start 2020.4.1 by zhurui
 * @Param getformdata
 * @RequestBody getjsondata
 */



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.FileService;

@Controller
public class Filecontroller {

	@Autowired
	private FileService service;
	
	
	@RequestMapping("/read")
	@ResponseBody
	public String getArticle(@RequestParam("path") String filename)throws Exception
	{
		return service.read(filename);
	}
	
	@RequestMapping("/saveArticle")
	@ResponseBody
	public void saveArticle(@RequestParam("body") String body) throws Exception
	{
		service.saveArticle(body);
	}
	@RequestMapping("/saveImg")
	@ResponseBody
	public String saveImage(@RequestParam("file") MultipartFile file)throws Exception
	{
		String url;
		url=service.saveImg(file);
		return "../MyWebsite/"+url;
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAll(@RequestParam("countPage") int count)throws Exception
	{
		return service.readAll(0);
	}
	@RequestMapping("/deleteA")
	@ResponseBody
	public String delete(@RequestParam("path") String path,@RequestParam("date") String date) throws Exception
	{
		service.deleteA(path, date);
		return "";
	}
}
