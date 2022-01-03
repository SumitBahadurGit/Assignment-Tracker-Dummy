package com.nepCafe.asmnttracker.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nepCafe.asmnttracker.models.AttachmentResponse;

@Service
public class RemoteDocService extends HttpService {

		
	@Value("${service.remote.url.doc-services}")
	private String url;
	private String slash = "/";
	private String get = "/find";
	private String save = "/save";
	private String checkDocs = "/checkDocs";
	
	RemoteDocService() {
		
	}

	public Boolean isDocsUploaded(Long id) {
		return (Boolean) get(url + slash + id + slash + checkDocs , Boolean.class);
	}

	public AttachmentResponse findAttachemnts(Long id, String type) {
		return (AttachmentResponse) get(url + slash + id + slash + type + slash + get , AttachmentResponse.class);						
	}

}
