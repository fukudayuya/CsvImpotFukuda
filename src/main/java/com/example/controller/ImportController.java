package com.example.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/insert")
	public String insert(ImportForm form) throws IOException {
		System.out.println("test1");
		String filename = form.getCsv().getOriginalFilename(); //formから受け取ったcsvファイルのファイル名のみを変数にいれる.

		FileInputStream fileInput = new FileInputStream(filename);//ファイルを読み込むために使用するクラス
		InputStreamReader isr = new InputStreamReader(fileInput);//バイト列を文字列に変換
		BufferedReader br = new BufferedReader(isr);//文字列などをバッファリングしている。効率よく文字列を読み込むため

//		Path path = Paths.get("/Users/yamadeayaka/eclipse-workspace/CsvImport/" + filename); //受け取ったcsvファイルをプロジェクトの直下に保存.

		String str;//読み込む行の箱
		while((str = br.readLine()) != null){ //csvファイルを1行ずつ読み込む.
			String[] results = str.split(","); //.split()メソッドは引数で指定した文字で行の文字列を分解し、配列として戻す。
			Student student = new Student();

			student.setName(results[0]);
			student.setEmail(results[1]);

			//※パフォーマンスを考えるとリストか何かにまとめてインサートすることになる？
			studentService.insert(student); //DBへ1行ずつinsert
		}
//		try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
//			byte[] bytes = form.getCsv().getBytes();
//			os.write(bytes); //csvファイルをプロジェクトの直下に書き込む.
//		} catch (IOException ex) {
//			System.err.println(ex);
//		}

//		FileInputStream fIStream= new FileInputStream("/Users/yamadeayaka/eclipse-workspace/CsvImport/" + filename); //プロジェクトの直下に保存したcsvファイルを読み込む.

		 return "success";
	}

}
