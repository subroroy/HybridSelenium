package util.readexcel;

public class TestStepClass {
	private String keyword;
	private String locator;
	private String data;
	private String colour;

	public void setTupleValues(String keyword, String locator, String data, String colour) {
		this.keyword = keyword;
		this.locator = locator;
		this.data = data;
		this.colour = colour;
	}

	public String toString() {
		return "Keyword: " + this.keyword + "; Locator: " + this.locator + "; Data: " + this.data + "; Colour: "
				+ this.colour;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the locator
	 */
	public String getLocator() {
		return locator;
	}

	/**
	 * @param locator
	 *            the locator to set
	 */
	public void setLocator(String locator) {
		this.locator = locator;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour
	 *            the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	public void displayValues() {
		System.out.println("Keyword : " + keyword + "; Locator : " + locator + "; Data : " + data);
	}
}
