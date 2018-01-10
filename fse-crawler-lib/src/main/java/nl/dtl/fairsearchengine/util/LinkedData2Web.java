package nl.dtl.fairsearchengine.util;

import java.net.URI;

public class LinkedData2Web {
	
	private String linkedDataUri = null;
	private String webUri = null;
	
	
	public LinkedData2Web(String linkedDataUri, String webUri) {
		this.linkedDataUri = linkedDataUri;
		this.webUri = webUri;
	}
	
	public String getLinkedDataUri() {
		return linkedDataUri;
	}
	public void setLinkedDataUri(String uri1) {
		this.linkedDataUri = uri1;
	}
	public String getWebUri() {
		return webUri;
	}
	public void setWebUri(String webUri) {
		this.webUri = webUri;
	}
	

}
