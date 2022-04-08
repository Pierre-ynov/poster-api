package com.webservices.posterapi.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.posterapi.utils.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("poster")
public class Poster {
	@Id
	private String        id;
	private String        contentId;
	private String        imageUrl;
	private MomentJournee timeOfDay;
	private Status        status;
	
	public Poster () {
	
	}
	
	public Poster (String contentId, String imageUrl, MomentJournee timeOfDay) {
		this.id = UUID.randomUUID().toString();
		this.contentId = contentId;
		this.imageUrl = imageUrl;
		this.timeOfDay = timeOfDay;
		this.status = Status.ACTIVE;
	}
	
	public Poster (ObjectNode jsonPoster) {
		id = UUID.randomUUID().toString();
		contentId = JsonNodeUtil.getJsonNodeAsText(jsonPoster, "contentId");
		imageUrl = JsonNodeUtil.getJsonNodeAsText(jsonPoster, "imageUrl");
		timeOfDay = JsonNodeUtil.getJsonNodeAsMomentJournee(jsonPoster, "timeOfDay");
	}
	
	public Poster updatePoster (ObjectNode json) {
		if (json.has("contentId")) {
			this.setContentId(JsonNodeUtil.getJsonNodeAsText(json, "contentId"));
		}
		if (json.has("imageUrl")) {
			this.setImageUrl(JsonNodeUtil.getJsonNodeAsText(json, "imageUrl"));
		}
		if (json.has("timeOfDay")) {
			this.setTimeOfDay(JsonNodeUtil.getJsonNodeAsMomentJournee(json, "timeOfDay"));
		}
		return this;
	}
	
	public Poster deletePoster () {
		this.setStatus(Status.INACTIVE);
		return this;
	}
}
