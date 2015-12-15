package kickstart.utilities;


import com.gtranslate.Translator;

public class RefugeeTranslator {
	public String translate(String toTranslate, String from, String to){
		Translator translate = Translator.getInstance();
		String text = translate.translate("Hello!", from, to);
		
		return text;
	}
}
