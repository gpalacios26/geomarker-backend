package com.gregpalacios.geomarker.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	public static String createFileFromBase64(FileDTO fileDTO) throws IOException {

		byte[] archivoBytes = Base64.getDecoder().decode(fileDTO.getFileContentBase64());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String fileNameUniq = "file-" + timestamp.getTime() + "." + fileDTO.getFileFormat();

		File file = new File(fileDTO.getFilePath() + fileNameUniq);
		OutputStream out = new FileOutputStream(file);
		out.write(archivoBytes);
		out.close();

		return fileNameUniq;
	}
}
