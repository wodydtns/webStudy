package kr.or.ddit.web05_model2;

import java.io.File;

import javax.servlet.ServletContext;

// 상속 받은 File 객체 + Wrapper 내 정의 된 기능
public class FileWrapper extends File {
	private String contextRootRP;

	public FileWrapper(File file,ServletContext application) {
		super(file.getAbsolutePath());
		this.contextRootRP = application.getRealPath("");
		contextRootRP = contextRootRP.substring(0, contextRootRP.length()-1);
	}
	public String getClassName() {
		
		return isDirectory()? "dir":"file";
	}
	
	public String getId() {
		String id = getAbsolutePath().substring(contextRootRP.length());
		id = id.replace(File.separatorChar, '/');
		return id;
	}
	
}
