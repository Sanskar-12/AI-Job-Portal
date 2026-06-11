package com.sanskar.job.dto.response;

import com.sanskar.job.domain.SocialPlatform;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SocialLinkResponse {
    private SocialPlatform platform;
    private String url;
}
