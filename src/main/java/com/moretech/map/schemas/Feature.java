package com.moretech.map.schemas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature  implements Serializable {
    String type;
    Geometry geometry;
    String properties;

}
