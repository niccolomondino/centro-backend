package com.tecnositaf.centrobackend.response;

import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tecnositaf.centrobackend.model.Area;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"count",
"filters",
"areas"
})
public class GetAreasResponse extends Response{
	@JsonProperty("count")
	private int count;
	@JsonProperty("filters")
	private Object filters;
	@JsonProperty("areas")
	private List<Area> areas;
	
	
	public GetAreasResponse() {
		super(2,"Get Areas Response Successful", ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
	}

	
	@JsonProperty("areas")
	public List<Area> getAreas() {
		return areas;
	}
	@JsonProperty("areas")
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	@JsonProperty("filters")
	public Object getFilter() {
	return filters;
	}

	@JsonProperty("filters")
	public void setFilter(Object filter) {
	this.filters = filter;
	}
	@JsonProperty("count")
	public int getCount() {
		return count;
	}
	@JsonProperty("count")
	public void setCount(int count) {
		this.count = count;
	}
	
}