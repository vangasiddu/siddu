package com.osi.form.model;

import java.util.Map;

public class FeatureFile {
public static final String PIPE = "|";
public static final String FORKWARD_SLASH = "/";
public static final String BACKWARD_SLASH = "\\";
public static final String NEW_LINE = "\n";
public static final String COLON = ":";
public static final String SANITY = "@sanity @scenario";
public static final String GIVEN = "Given ";
public static final String SCENARIO = "Scenario: ";
public static final String REQUEST_METHOD = "requestMethod ";
public static final String URI = "uri ";
public static final String PATH_PARAMETERS = "pathParameters ";
public static final String QUERY_PARAMETERS = "queryParameters ";
public static final String HEADER_PARAMETERS = "headerParameters ";
public static final String REQUEST_FILE = "requestFile ";
public static final String RESPONSE_FILE = "responseFile ";
public static final String GIVEN_OPEN = "@Given("+"\"" ;
public static final String GIVEN_CLOSE= "$"+"\""+")";
public static final String FOR_METHOD="public void ";
public static final String CLOSE_BRACE=")";
public static final String OPEN_BRACE="(";
public static final String CLOSE_PARNATHASESIS="}";
public static final String OPEN_PARNATHASESIS=" {";
public static final String FEATURE_NAME = "Feature: feature1"; 
public static final String SDF_NAME = "Step: Step1";
public static final String FEATURE_FILE_LOCATION = "/src/test/resources/features/";    
public static final String STEP_DEFS_LOCATION = "/src/test/java/stepdefs/";
public static final String TESTLAYER_LOCATION = "/src/test/java/test/";
public static final String FEATURE_FILE_EXTENSION = ".feature";
public static final String JAVA_FILE_EXTENSION = ".java";
public static final String JAVA_CLASS_NAME="public class ";
public static final String JAVA_METHOD_NAME="public void ";
public static final String PUBLIC="public";
public static final String THROWS_THROWABLE="  throws Throwable";
public static final String THROWS_EXCEPTION="  throws Exception";
public static final String DATA_TABLE="DataTable ";
public static final String TABLE="table";
public static final String CAP_SYMBOL="^";
public static final String DYNAMICMAP="Map<String, Map<String, String>> dynamicMap = new HashMap<String, Map<String, String>>();";
public static final String RESPONSEMAP="Map<String, Object> responseMap = restAssuredService.invokeService(table, dynamicMap);";
public static final String TRY="try";
public static final String CATCH="catch";
public static final String EXCEPTION="Exception e";
public static final String PRINTSTACKTRACE="printStackTrace";
public static final String TEST="Test";
public static final String PACKAGE="package ";
public static final String SEMICOLON=";";
public static final String RESTASSUREDDECLRATION="RestAssuredService restAssuredService;";
public static final String STEPDEF="StepDef";
public static final String PROPERTIES_FILE_NAME="/messages/propertiesfile.properties";
public static final String JSON_FILE_EXTENSION=".json";
public static final String SPACE="  ";
public static final String XML_FILE_EXTENSION=".xml";
public static final String TXT_FILE_EXTENSION=".txt";
public static final String CONSTRUCTOR_DECLRATION="restAssuredService = RestAssuredService.getInstance(TestProperties.loadProperties());";
public static final String STATIC="static";
public static final String EQUALSTO="=";
public static final String NEW="new";
public static final String DOT=".";



}