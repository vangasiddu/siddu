package com.osi.form.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.osi.form.model.Feature;
import com.osi.form.model.FeatureFile;

import org.springframework.ui.ModelMap;

@Controller
public class FeatureFileController {
	public static final String PIPE = "|";
	public static int scenarioNumber = 0;
	Properties prop = new Properties();

	@RequestMapping(value = "/feature", method = RequestMethod.GET)
	public ModelAndView feature() {
		return new ModelAndView("feature", "command", new Feature());
	}

	@RequestMapping(value = "/addFeature", method = RequestMethod.POST)
	public String addFeature(@ModelAttribute("SpringWeb") Feature feature, ModelMap model) throws IOException {
		System.out.println("Feature Object:::::: " + feature);

		writeFile(feature);
		model = getFeatureModel(model, feature);
		return "featureResult";
	}

	@RequestMapping(value = "/findFileName", method = RequestMethod.GET)
	public @ResponseBody() String getTime(@RequestParam("filename") String searchFileName,
			@RequestParam("fieldname") String searchField) {
		List response = getExistingFileNames(searchFileName, searchField);
		String searchList = new Gson().toJson(response);
			return searchList;

	}

	public FeatureFileController() {
		try {
			String resourceName = FeatureFile.PROPERTIES_FILE_NAME;
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream input = loader.getResourceAsStream(resourceName);
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static FeatureFileController INSTANCE;

	public static FeatureFileController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FeatureFileController();
		}
		return INSTANCE;
	}

	private ModelMap getFeatureModel(ModelMap model, Feature feature) {

		model.addAttribute("projectLocation", feature.getProjectLocation());
		model.addAttribute("scenario", feature.getScenario());
		model.addAttribute("requestMethod", feature.getRequestMethod());
		model.addAttribute("uri", feature.getUri());
		model.addAttribute("queryParameters", feature.getQueryParameters());
		model.addAttribute("pathParameters", feature.getPathParameters());
		model.addAttribute("headerParameters", feature.getHeaderParameters());
		model.addAttribute("requestFile", feature.getRequestFile());
		model.addAttribute("responseFile", feature.getResponseFile());
		model.addAttribute("featureFile", feature.getFeatureFile());
		model.addAttribute("stepDefFile", feature.getStepDefFile());
		return model;
	}

	private void writeFile(Feature feature) throws IOException {

		System.out.println("Feature File Writing starting*************");
		String stepDefFile = feature.getStepDefFile();
		generateFeatureFile(feature, prop.getProperty("FEATURE_FILE_LOCATION"));
		generateStepdefinitionFile(feature, prop.getProperty("STEP_DEFS_LOCATION"));
		generateTestLayerFile(feature, prop.getProperty("TESTLAYER_LOCATION"));
		generateRequestResponseFile(feature, prop.getProperty("REQUEST_RESPONSE_LOCATION"));

	}

	private void generateFeatureFile(Feature feature, String featureFileLocation) throws IOException {

		String keyString = "";
		String valueString = "";
		String scenarioDetailString = "";
		String projectLocation = feature.getProjectLocation();
		String requestMethod = feature.getRequestMethod();
		String scenario = feature.getScenario();
		String uri = feature.getUri();
		String queryParameters = feature.getQueryParameters();
		String pathParameters = feature.getPathParameters();
		String headerParameters = feature.getHeaderParameters();
		String requestFile = feature.getRequestFile();
		String responseFile = feature.getResponseFile();
		String featureFileName = feature.getFeatureFile();
		String finalString = "";
		scenarioNumber = scenarioNumber + 1;
		if (StringUtils.isNotBlank(scenario)) {
			scenarioDetailString = scenarioDetailString + FeatureFile.SANITY + scenarioNumber + FeatureFile.NEW_LINE;
			scenarioDetailString = scenarioDetailString + FeatureFile.SCENARIO + scenario + FeatureFile.NEW_LINE;
			scenarioDetailString = scenarioDetailString + FeatureFile.GIVEN + scenario + FeatureFile.NEW_LINE;
		}
		System.out.println("Scenario Details String::::\n " + scenarioDetailString);

		keyString = keyString + FeatureFile.PIPE;
		valueString = valueString + FeatureFile.PIPE;
		if (StringUtils.isNotBlank(requestMethod)) {
			keyString = keyString + FeatureFile.REQUEST_METHOD + FeatureFile.PIPE;
			valueString = valueString + requestMethod + FeatureFile.PIPE;
		}

		if (StringUtils.isNotBlank(uri)) {
			keyString = keyString + FeatureFile.URI + FeatureFile.PIPE;
			valueString = valueString + uri + FeatureFile.PIPE;
		}
		if (StringUtils.isNotBlank(pathParameters)) {
			keyString = keyString + FeatureFile.PATH_PARAMETERS + FeatureFile.PIPE;
			valueString = valueString + pathParameters + FeatureFile.PIPE;
		}
		if (StringUtils.isNotBlank(queryParameters)) {
			keyString = keyString + FeatureFile.QUERY_PARAMETERS + FeatureFile.PIPE;
			valueString = valueString + queryParameters + FeatureFile.PIPE;
		}
		if (StringUtils.isNotBlank(headerParameters)) {
			keyString = keyString + FeatureFile.HEADER_PARAMETERS + FeatureFile.PIPE;
			valueString = valueString + headerParameters + FeatureFile.PIPE;
		}
		if (StringUtils.isNotBlank(requestFile)) {
			keyString = keyString + FeatureFile.REQUEST_FILE + FeatureFile.PIPE;
			valueString = valueString + requestFile + FeatureFile.PIPE;
		}

		if (StringUtils.isNotBlank(responseFile)) {
			keyString = keyString + FeatureFile.RESPONSE_FILE + FeatureFile.PIPE;
			valueString = valueString + responseFile + FeatureFile.PIPE;
		}
		finalString = scenarioDetailString + keyString + FeatureFile.NEW_LINE + valueString;

		System.out.println("projectLocation path::: " + projectLocation);
		if (StringUtils.isNotBlank(featureFileName)) {
			featureFileName = featureFileName + FeatureFile.FEATURE_FILE_EXTENSION;
			writeDataToFile(projectLocation, featureFileLocation, featureFileName, finalString, null);
			System.out.println("Feature File Writing ending finalString*************" + finalString);
		}

	}

	private String getRequestBody(File file) throws IOException {
		String body = "";
		System.out.println("*********getRequestBody starting**************");
		body = FileUtils.readFileToString(file);
		return body;

	}

	private void writeDataToFile(String stepDefFile, String resourcesPath, String fileName, String featureString,
			String location) throws IOException {

		String fileLocation = /*
								 * getDirectoryPath(projectDirectoryPath) +
								 */resourcesPath + fileName;
		boolean available = false;
		System.out.println("File Location::::: " + fileLocation);
		System.out.println("FileName ::: " + fileName);
		File file = new File(fileLocation);
		if (file.exists()) {
			if (fileName.endsWith(FeatureFile.FEATURE_FILE_EXTENSION)) {
				System.out.println("File Already Exists::::::");
				featureString = getRequestBody(file) + FeatureFile.NEW_LINE + featureString;
				FileUtils.writeStringToFile(file, featureString);
			} else if (fileName.endsWith(FeatureFile.JAVA_FILE_EXTENSION)) {
				int offset = 1;
				String existingBody = getRequestBody(file);
				String[] featureArrays = featureString.split("\n");
				String[] arrOfStr = existingBody.split("\n");
				if (fileName.contains("Test")) {
					offset = 0;

				}
				for (String a : arrOfStr) {
					int position = 0;
					int featureArrayPostion = 0;
					if (a.contains(FeatureFile.FOR_METHOD)) {
						position = a.trim().indexOf("(");
						featureArrayPostion = featureArrays[offset].trim().indexOf("(");
						if (featureArrays[offset].trim().substring(0, featureArrayPostion)
								.equals(a.trim().substring(0, position))) {
							available = true;
							break;
						}

					}
				}
				if (!available && existingBody != null) {
					String updatedBody = existingBody.substring(0, existingBody.length() - 1);
					featureString = updatedBody + FeatureFile.NEW_LINE + featureString + FeatureFile.NEW_LINE
							+ FeatureFile.CLOSE_PARNATHASESIS;
					System.out.println("File Already Exists::::::");
					FileUtils.writeStringToFile(file, featureString);
				}
			} else if (fileName.endsWith(FeatureFile.JSON_FILE_EXTENSION)) {
				FileUtils.writeStringToFile(file, featureString);
			} else if (fileName.endsWith(FeatureFile.XML_FILE_EXTENSION)) {
				FileUtils.writeStringToFile(file, featureString);
			} else {
				FileUtils.writeStringToFile(file, featureString);
			}

		} else {
			System.out.println("File newly created::::::");
			file.createNewFile();
			if (fileName.endsWith(FeatureFile.FEATURE_FILE_EXTENSION)) {
				featureString = FeatureFile.FEATURE_NAME + FeatureFile.NEW_LINE + featureString;

			} else if (fileName.endsWith(FeatureFile.JAVA_FILE_EXTENSION)) {
				String[] packageStructure = location.split("/");
				String[] subpackageStrucutre = new String[packageStructure.length];
				subpackageStrucutre = packageStructure.clone();
				int length = 0;
				for (String structure : packageStructure) {
					length++;
					if ((structure.trim().equals("java"))) {
						break;
					}
				}
				String packagedecl = FeatureFile.PACKAGE;
				for (int i = length; i < subpackageStrucutre.length; i++) {
					packagedecl = packagedecl + packageStructure[i] + ".";
				}
				packagedecl = packagedecl.substring(0, packagedecl.length() - 1);

				String newString = packagedecl + FeatureFile.SEMICOLON + FeatureFile.NEW_LINE
						+ FeatureFile.JAVA_CLASS_NAME +fileName.toUpperCase().charAt(0)
						+fileName.substring(1, fileName.length() - 5)
						+ FeatureFile.NEW_LINE + FeatureFile.OPEN_PARNATHASESIS;

				if (fileName.contains(FeatureFile.TEST)) {
					newString = newString + FeatureFile.NEW_LINE + FeatureFile.RESTASSUREDDECLRATION
							+ FeatureFile.NEW_LINE + FeatureFile.PUBLIC + FeatureFile.SPACE
							+fileName.toUpperCase().charAt(0)
							+fileName.substring(1, fileName.length() - 5) + FeatureFile.OPEN_BRACE
							+ FeatureFile.CLOSE_BRACE + FeatureFile.NEW_LINE + FeatureFile.OPEN_PARNATHASESIS
							+ FeatureFile.CONSTRUCTOR_DECLRATION + FeatureFile.NEW_LINE
							+ FeatureFile.CLOSE_PARNATHASESIS;
				} else {
					newString = newString + FeatureFile.NEW_LINE + FeatureFile.STATIC + FeatureFile.SPACE + stepDefFile.toUpperCase().charAt(0)+
							 stepDefFile.substring(1)+FeatureFile.TEST + FeatureFile.SPACE + stepDefFile.toLowerCase().substring(0)
							+ FeatureFile.TEST + FeatureFile.SPACE + FeatureFile.EQUALSTO + FeatureFile.SPACE
							+ FeatureFile.NEW + FeatureFile.SPACE + stepDefFile.toUpperCase().charAt(0)+
							 stepDefFile.substring(1) + FeatureFile.TEST
							+ FeatureFile.OPEN_BRACE + FeatureFile.CLOSE_BRACE + FeatureFile.SEMICOLON
							+ FeatureFile.NEW_LINE;
				}
				newString = newString + FeatureFile.NEW_LINE + FeatureFile.NEW_LINE + featureString
						+ FeatureFile.NEW_LINE + FeatureFile.CLOSE_PARNATHASESIS;
				featureString = newString;
			}

			System.out.println("featureString___________ " + featureString);
			FileUtils.writeStringToFile(file, featureString);
		}

	}

	private String getDirectoryPath(String projectDirectoryPath) {
		return projectDirectoryPath.replace(FeatureFile.BACKWARD_SLASH, FeatureFile.FORKWARD_SLASH);
	}

	private void generateStepdefinitionFile(Feature feature, String stepDefinitonLocation) throws IOException {
		String scenario = feature.getScenario();
		String given = FeatureFile.GIVEN_OPEN + FeatureFile.CAP_SYMBOL + scenario + FeatureFile.GIVEN_CLOSE
				+ FeatureFile.NEW_LINE;
		scenario = scenario.replaceAll("\\s", "_");
		String method = FeatureFile.FOR_METHOD + scenario.substring(0, 1).toLowerCase()
				+ scenario.substring(1, scenario.length()) + FeatureFile.OPEN_BRACE + FeatureFile.DATA_TABLE
				+ FeatureFile.TABLE + FeatureFile.CLOSE_BRACE + FeatureFile.THROWS_THROWABLE + FeatureFile.NEW_LINE;
		method = method + FeatureFile.OPEN_PARNATHASESIS + FeatureFile.NEW_LINE
				+ feature.getStepDefFile().toLowerCase().substring(0) + FeatureFile.TEST + FeatureFile.DOT
				+ scenario.replaceAll("\\s", "_") + FeatureFile.OPEN_BRACE + FeatureFile.TABLE + FeatureFile.CLOSE_BRACE
				+ FeatureFile.SEMICOLON + FeatureFile.NEW_LINE + FeatureFile.CLOSE_PARNATHASESIS + FeatureFile.NEW_LINE;
		String stepdefstring = given + method;
		writeDataToFile(feature.getStepDefFile(), stepDefinitonLocation,
				feature.getStepDefFile().toUpperCase().charAt(0) +feature.getStepDefFile().substring(1)+ FeatureFile.STEPDEF + FeatureFile.JAVA_FILE_EXTENSION, stepdefstring,
				stepDefinitonLocation);
	}

	private void generateTestLayerFile(Feature feature, String testLayerLocation) throws IOException {
		String scenario = feature.getScenario();
		scenario = scenario.replaceAll("\\s", "_");
		String method = FeatureFile.FOR_METHOD + scenario.substring(0, 1).toLowerCase()
				+ scenario.substring(1, scenario.length());
		String DynamicMap = FeatureFile.DYNAMICMAP;
		method = method + FeatureFile.OPEN_BRACE + FeatureFile.DATA_TABLE + FeatureFile.TABLE + FeatureFile.CLOSE_BRACE
				+ FeatureFile.THROWS_EXCEPTION + FeatureFile.OPEN_PARNATHASESIS + FeatureFile.NEW_LINE + DynamicMap
				+ FeatureFile.NEW_LINE;
		method = method + FeatureFile.TRY + FeatureFile.OPEN_PARNATHASESIS + FeatureFile.NEW_LINE;
		method = method + FeatureFile.RESPONSEMAP + FeatureFile.NEW_LINE + FeatureFile.CLOSE_PARNATHASESIS;
		method = method + FeatureFile.CATCH + FeatureFile.OPEN_BRACE + FeatureFile.EXCEPTION + FeatureFile.CLOSE_BRACE
				+ FeatureFile.OPEN_PARNATHASESIS + FeatureFile.NEW_LINE + FeatureFile.CLOSE_PARNATHASESIS
				+ FeatureFile.NEW_LINE;
		String testLayer = method + FeatureFile.CLOSE_PARNATHASESIS + FeatureFile.NEW_LINE;
		writeDataToFile(feature.getProjectLocation(), testLayerLocation,
				feature.getStepDefFile().toUpperCase().charAt(0) +feature.getStepDefFile().substring(1) + FeatureFile.TEST + FeatureFile.JAVA_FILE_EXTENSION, testLayer,
				testLayerLocation);

	}

	private void generateRequestResponseFile(Feature feature, String testLayerLocation) throws IOException {

		String requestFile = feature.getRequestFile();
		String responseFile = feature.getResponseFile();
		String finalFileRequest = fileExtention(requestFile);
		String finalFileResponse = fileExtention(responseFile);
		String requestFileData = feature.getRequestFileData();
		String responseFileData = feature.getResponseFileData();
		writeDataToFile(feature.getProjectLocation(), testLayerLocation, finalFileRequest, requestFileData,
				testLayerLocation);
		writeDataToFile(feature.getProjectLocation(), testLayerLocation, finalFileResponse, responseFileData,
				testLayerLocation);
	}

	private String fileExtention(String generatedfileName) {
		String finalFile = null;
		if (generatedfileName.contains(".")) {
			String fileName = generatedfileName.substring(0, generatedfileName.lastIndexOf("."));
			String extension = generatedfileName.substring(generatedfileName.lastIndexOf(".") + 1);
		}
		if (generatedfileName.endsWith(FeatureFile.JSON_FILE_EXTENSION)) {
			finalFile = generatedfileName;
		} else if (generatedfileName.endsWith(FeatureFile.XML_FILE_EXTENSION)) {
			finalFile = generatedfileName;
		} else {
			finalFile = generatedfileName + ".txt";
		}
		return finalFile;
	}

	public List<String> getExistingFileNames(String searchFileName, String searchField) {
		List fileList = new ArrayList<>();
		List responseList = new ArrayList<>();
		String searchFileLocation = null;
		if (searchField.equalsIgnoreCase("featureFile")) {
			searchFileLocation = prop.getProperty("FEATURE_FILE_LOCATION");

		} else if (searchField.equalsIgnoreCase("stepDefFile")) {
			searchFileLocation = prop.getProperty("STEP_DEFS_LOCATION");
		}else if (searchField.equalsIgnoreCase("requestFile")) {
			searchFileLocation = prop.getProperty("REQUEST_RESPONSE_LOCATION");
		}else if (searchField.equalsIgnoreCase("responseFile")) {
			searchFileLocation = prop.getProperty("REQUEST_RESPONSE_LOCATION");
		}
		File file = new File(searchFileLocation);
		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println("file name ===== "+f.getName());
			if (!(searchField.equalsIgnoreCase("responseFile") || searchField.equalsIgnoreCase("requestFile")) ) {
				
			fileList.add((f.getName()).split("\\.", 2)[0]);
		} else {
			
			fileList.add((f.getName()));	
		}
		}
		String matchWord1 = searchFileName.toLowerCase();
		
		for (int i = 0; i < fileList.size(); i++) {
			String matchword = (String) fileList.get(i);
			
			if ((matchword.toLowerCase()).contains(matchWord1)) {
				System.out.println("matchGroup ::: " + matchword);
				responseList.add(matchword);
			}
		}
		

		return responseList;
	}
}