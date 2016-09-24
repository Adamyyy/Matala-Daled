package presenter;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.Serializable;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 555913593004844041L;

	private String algorithm;
	private String cellchooser;
	private int numberOfThreads;
	private String searchAlgorithm;
	private String viewtype;
	
	public Properties(int numberOfThreads , String algorithm , String searchAlgorithm , String CellChooser,String insertviewtype) {
		super();
		this.numberOfThreads = numberOfThreads;
		this.algorithm = algorithm;
		this.cellchooser = CellChooser;
		this.searchAlgorithm = searchAlgorithm;
		this.viewtype = insertviewtype;
	}
	
	public Properties(Properties p) {
		algorithm = p.algorithm;
		cellchooser= p.cellchooser;
		numberOfThreads = p.numberOfThreads;
		searchAlgorithm = p.searchAlgorithm;
		viewtype = p.viewtype;
	}

	public Properties(String filename)
	{
		try{
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			XMLDecoder xmlDecoder = new XMLDecoder(bis);
			Properties p = (Properties) xmlDecoder.readObject();
			algorithm = p.algorithm;
			cellchooser= p.cellchooser;
			numberOfThreads = p.numberOfThreads;
			searchAlgorithm = p.searchAlgorithm;
			viewtype=p.viewtype;
			
			}
			catch (Exception e) {
				// TODO: handle exception
			}
					
		}
		
	
	
	
	
	public Properties() {
		super();
	}



	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getCellchooser() {
		return cellchooser;
	}

	public void setCellchooser(String cellchooser) {
		this.cellchooser = cellchooser;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public String getSearchAlgorithm() {
		return searchAlgorithm;
	}

	public void setSearchAlgorithm(String searchAlgorithm) {
		this.searchAlgorithm = searchAlgorithm;
	}

	
}