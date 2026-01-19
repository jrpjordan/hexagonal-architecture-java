package com.joser.framework.adapters.output.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum SwitchTypeJson {
    LAYER2,
    LAYER3;
}
