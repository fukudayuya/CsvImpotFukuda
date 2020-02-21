package com.example.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Student;
import com.example.form.ImportForm;
import com.example.service.StudentService;

@Controller
@RequestMapping("/import")
public class ImportController {
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * csvファイルのインポート初期画面.
	 * @return
	 */
	@RequestMapping("")
	public String index() {
		return "import";
	}
	
	@RequestMapping("/insert")
	public String insert(ImportForm form) throws IOException {
		String filename = form.getCsv().getOriginalFilename(); //formから受け取ったcsvファイルのファイル名のみを変数にいれる.
		Path path = Paths.get("/Users/yamadeayaka/eclipse-workspace/CsvImport/" + filename); //受け取ったcsvファイルをプロジェクトの直下に保存.

		try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
			byte[] bytes = form.getCsv().getBytes();
			os.write(bytes); //csvファイルをプロジェクトの直下に書き込む.
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		FileInputStream fIStream= new FileInputStream("/Users/yamadeayaka/eclipse-workspace/CsvImport/" + filename); //プロジェクトの直下に保存したcsvファイルを読み込む.
		InputStreamReader isr = new InputStreamReader(fIStream);
		BufferedReader br = new BufferedReader(isr);
		String str;
//		List<Student> studentList = new ArrayList<>(); //空のリストを作る.
		 while((str = br.readLine()) != null){ //csvファイルを1行ずつ読み込む.
			 String[] results = str.split(","); //csvファイル1行をカンマごとに分割して配列にいれる
			 
			 Student student = new Student();
			 student.setName(results[0]);
			 student.setEmail(results[1]);
//			 studentList.add(student);
			 studentService.insert(student); //DBへ1行ずつinsert
			  }
		 return "success";
	}

}
