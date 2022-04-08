package com.webservices.posterapi.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeUtil {
	public static String getJsonNodeAsText (ObjectNode json, String filter) {
		String   res  = null;
		JsonNode node = json.get(filter);
		if (node != null) {
			res = node.asText();
		}
		return res;
	}
	
	public static Boolean getJsonNodeAsBoolean (ObjectNode json, String filter) {
		Boolean  res  = null;
		JsonNode node = json.get(filter);
		if (node != null) {
			res = node.asBoolean();
		}
		return res;
	}
	
	public static MomentJournee getJsonNodeAsMomentJournee (ObjectNode json, String filter) {
		String tmp = getJsonNodeAsText(json, filter);
		try {
			return MomentJournee.valueOf(tmp);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
