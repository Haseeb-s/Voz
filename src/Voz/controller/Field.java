package Voz.controller;

public class Field {
	private String key, value;

	public Field() {
		key = "n/a";
		value = "n/a";
	}

	public Field(String key, String val) {
		this.key = key;
		this.value = val;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
