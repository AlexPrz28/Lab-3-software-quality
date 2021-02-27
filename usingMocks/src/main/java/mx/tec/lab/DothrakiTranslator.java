package mx.tec.lab;

import java.util.HashMap;
import java.util.Map;

public class DothrakiTranslator {
	
	Map<String, String> translationMap;

	public DothrakiTranslator() {
		translationMap = new HashMap<>();
	}

	public void addTranslation(final String word, final String translation) {
		this.translationMap.put(word, translation);
	}

	public String getTranslation(final String word) {
		return this.translationMap.get(word);
	}


}
