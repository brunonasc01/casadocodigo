package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.Part;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class FileSaver {

	@Inject AmazonS3 s3;

	private static final String CONTENT_DISPOSITION = "content-disposition";

	public String write(String baseFolder, Part multipartFile) {

		String fileName = extractFilename(multipartFile.getHeader(CONTENT_DISPOSITION));

		File file = new File(fileName);
		String path = file.getName();
		String nameOnly = path.substring(0,path.lastIndexOf("."));
		String extension = path.substring(path.lastIndexOf("."));

		Random rand = new Random();
		fileName = nameOnly+"_"+rand.nextInt(100000)+extension;

		try {
			s3.putObject("brnasc-web", fileName, multipartFile.getInputStream(),new ObjectMetadata());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return "https://s3-sa-east-1.amazonaws.com/brnasc-web/"+fileName;
	}

	private String extractFilename(String contentDisposition) {
		if (contentDisposition == null) {
			return null;
		}
		String fileNameKey = "filename=";
		int startIndex = contentDisposition.indexOf(fileNameKey);
		if (startIndex == -1) {
			return null;
		}
		String filename = contentDisposition.substring(startIndex
				+ fileNameKey.length());
		if (filename.startsWith("\"")) {
			int endIndex = filename.indexOf("\"", 1);
			if (endIndex != -1) {
				return filename.substring(1, endIndex);
			}
		} else {
			int endIndex = filename.indexOf(";");
			if (endIndex != -1) {
				return filename.substring(0, endIndex);
			}
		}
		return filename;
	}

}
