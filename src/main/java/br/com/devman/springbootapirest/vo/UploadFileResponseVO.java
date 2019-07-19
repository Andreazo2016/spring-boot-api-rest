package br.com.devman.springbootapirest.vo;

import java.io.Serializable;

public class UploadFileResponseVO  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String fileNameDownloadUri;
	private String fileType;
	private long size;

	public UploadFileResponseVO(String fileName, String fileNameDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileNameDownloadUri = fileNameDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNameDownloadUri() {
		return fileNameDownloadUri;
	}
	public void setFileNameDownloadUri(String fileNameDownloadUri) {
		this.fileNameDownloadUri = fileNameDownloadUri;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	
	
	
	
	
	

}
