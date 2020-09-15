package com.tataaig.utilities;

public class SeleniumUtils {
	public static String XPATH = "xpath";
	public static String ID = "id";
	public static String NAME = "name";
	public static String LINKTEXT = "linkText";
	public static String CLASSNAME = "className";

	public enum ELEMENT_LOCATE_BY {
		XPATH("xpath"), ID("id"), NAME("name"), LINKTEXT("linkText"), CLASSNAME("className");

		public String text;

		ELEMENT_LOCATE_BY(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

}
