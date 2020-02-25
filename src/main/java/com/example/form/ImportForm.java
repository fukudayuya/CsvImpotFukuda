package com.example.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class ImportForm {

	/** 生徒の情報が入ったcsvファイル */
	//MultipartFileオブジェクト:アップロードされたファイルの中身とメタデータ(ファイル名など)をcontrollerに返却
	private MultipartFile csv;

//	@Override
//	public String toString() {
//		return "ImportForm [csv=" + csv + "]";
//	}



}
