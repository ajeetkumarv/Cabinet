package com.cabinet.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class WriteFile {

	public static void writeDataMatrix(String absoluteFilePath, String data) throws IOException {
		Files.write(Paths.get(absoluteFilePath), data.getBytes());
	}
}
