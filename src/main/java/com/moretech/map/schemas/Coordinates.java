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
public class Coordinates  implements Serializable {
    String latitude;
    String longitude;

}
