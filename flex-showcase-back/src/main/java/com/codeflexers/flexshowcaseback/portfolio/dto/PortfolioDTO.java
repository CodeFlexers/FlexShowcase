package com.codeflexers.flexshowcaseback.portfolio.dto;

import com.codeflexers.flexshowcaseback.security.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class PortfolioDTO {
    private Long portfolioCode;
    private String userName;
    private String websiteUrl;
    private String projectName;
    private String descriptionHTML;
    private String thumbnailImage;
    private String category;
    private String githubRepo;
    private int viewCount;
}
