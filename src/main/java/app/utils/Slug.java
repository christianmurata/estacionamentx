package app.utils;

import java.util.Locale;
import java.util.regex.Pattern;

import java.text.Normalizer;  
import java.text.Normalizer.Form;  

public class Slug {
	
	private String text;
	
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");  
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");  
  
	public Slug(String text) {
		this.text = text;
	}
	
	public String make() {  
		String nowhitespace = WHITESPACE.matcher(text).replaceAll("-");  
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);  
		String slug = NONLATIN.matcher(normalized).replaceAll("");  
		
		return slug.toLowerCase(Locale.ENGLISH) + "-" + new RandomString(5).nextString();  
	}  
}
