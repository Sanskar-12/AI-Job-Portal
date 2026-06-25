package com.sanskar.job.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobTagResponse {

    private Long id;
    private String name;
    private String slug;
}
