package com.sanskar.job.dto.response;

import com.sanskar.job.domain.SocialPlatform;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocialLinkResponse {
    private SocialPlatform platform;
    private String url;
}
