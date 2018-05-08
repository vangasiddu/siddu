package com.osi.form.model;

public class Feature {

	String projectLocation;
	String scenario;
	String requestMethod;
	String uri;
	String queryParameters;
	String pathParameters;
	String headerParameters;
	String requestFile;
	String requestFileData;
	String responseFileData;
	String responseFile;
	String featureFile;
	String stepDefFile;
	String createORappendFF;
	String createORappendSD;
	String createORappendRQF;
	String createORappendRF;

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String getFeatureFile() {
		return featureFile;
	}

	public void setFeatureFile(String featureFile) {
		this.featureFile = featureFile;
	}

	public String getStepDefFile() {
		return stepDefFile;
	}

	public void setStepDefFile(String stepDefFile) {
		this.stepDefFile = stepDefFile;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getQueryParameters() {
		return queryParameters;
	}

	public void setQueryParameters(String queryParameters) {
		this.queryParameters = queryParameters;
	}

	public String getPathParameters() {
		return pathParameters;
	}

	public void setPathParameters(String pathParameters) {
		this.pathParameters = pathParameters;
	}

	public String getHeaderParameters() {
		return headerParameters;
	}

	public void setHeaderParameters(String headerParameters) {
		this.headerParameters = headerParameters;
	}

	public String getRequestFile() {
		return requestFile;
	}

	public void setRequestFile(String requestFile) {
		this.requestFile = requestFile;
	}

	public String getResponseFile() {
		return responseFile;
	}

	public void setResponseFile(String responseFile) {
		this.responseFile = responseFile;
	}
	


	public String getRequestFileData() {
		return requestFileData;
	}

	public void setRequestFileData(String requestFileData) {
		this.requestFileData = requestFileData;
	}

	public String getResponseFileData() {
		return responseFileData;
	}

	public void setResponseFileData(String responseFileData) {
		this.responseFileData = responseFileData;
	}

	public String getCreateORappendFF() {
		return createORappendFF;
	}

	public void setCreateORappendFF(String createORappendFF) {
		this.createORappendFF = createORappendFF;
	}

	public String getCreateORappendSD() {
		return createORappendSD;
	}

	public void setCreateORappendSD(String createORappendSD) {
		this.createORappendSD = createORappendSD;
	}

	public String getCreateORappendRQF() {
		return createORappendRQF;
	}

	public void setCreateORappendRQF(String createORappendRQF) {
		this.createORappendRQF = createORappendRQF;
	}

	public String getCreateORappendRF() {
		return createORappendRF;
	}

	public void setCreateORappendRF(String createORappendRF) {
		this.createORappendRF = createORappendRF;
	}

	@Override
	public String toString() {
		return "Feature [projectLocation=" + projectLocation + ", scenario=" + scenario + ", requestMethod="
				+ requestMethod + ", uri=" + uri + ", queryParameters=" + queryParameters + ", pathParameters="
				+ pathParameters + ", headerParameters=" + headerParameters + ", requestFile=" + requestFile
				+ ", responseFile=" + responseFile + ", featureFile=" + featureFile + ", stepDefFile=" + stepDefFile + ", requestFileData=" + requestFileData + ", responseFileData=" + responseFileData
				+ "]";
	}

}
