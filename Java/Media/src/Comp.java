

public class Comp
{
	private String text;
	private double factor;
	public Comp(String text, double factor) {
		this.text = text;
		this.factor = factor;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the factor
	 */
	public double getFactor() {
		return factor;
	}
	/**
	 * @param factor the factor to set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public static int occur(String text,char c)
	{
		int count=0;
		for(int i=0;i<text.length();i++)
			if(text.charAt(i)==c)
				count++;
		return count;
	}
}
