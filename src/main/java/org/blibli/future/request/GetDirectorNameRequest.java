package org.blibli.future.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetDirectorNameRequest {
    private String filmName;
}
