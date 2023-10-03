package com.moretech.map.schemas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureCollection   implements Serializable {
    String type;
    String properties;
    List<Feature> features;

}
