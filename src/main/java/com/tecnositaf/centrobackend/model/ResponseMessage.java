package com.tecnositaf.centrobackend.model;

public class ResponseMessage {
	
	 private String content;
	 private String serverEndpoint;

	  public ResponseMessage() {
	  }

	  public ResponseMessage(String endpoint, String content) {
		this.setServerEndpoint(endpoint);
	    this.content = content;
	  }

	  public String getContent() {
	    return content;
	  }

	public String getServerEndpoint() {
		return serverEndpoint;
	}

	public void setServerEndpoint(String serverEndpoint) {
		this.serverEndpoint = serverEndpoint;
	}

}
