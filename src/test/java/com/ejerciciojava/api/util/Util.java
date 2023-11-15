package com.ejerciciojava.api.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public static <T> T serializarArchivoAObjeto(String jsonFile, Class<?> clase) {
		Gson gson = new Gson();

		T response = null;

		String bit;
		String json = "";

		File file = new File("src/test/resources/json/" + jsonFile);

		try (BufferedReader input = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));) {
			while ((bit = input.readLine()) != null) {
				json += bit;
			}
			InputStream stream = new ByteArrayInputStream(json.getBytes());
			response = (T) gson.fromJson(new InputStreamReader(stream), clase);
		} catch (Exception e) {
			logger.debug("{}", e);
		}

		return response;
	}
}
