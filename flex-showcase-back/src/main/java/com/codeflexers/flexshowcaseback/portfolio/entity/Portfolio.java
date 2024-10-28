package com.codeflexers.flexshowcaseback.portfolio.entity;

import com.codeflexers.flexshowcaseback.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolio")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Portfolio {
    public Portfolio(String websiteUrl, String projectName, String descriptionHTML, String thumbnailImage, String category, String githubRepo) {
        this.websiteUrl = websiteUrl;
        this.projectName = projectName;
        this.descriptionHTML = descriptionHTML;
        this.thumbnailImage = thumbnailImage;
        this.category = category;
        this.githubRepo = githubRepo;
    }

    public Portfolio(User user, String websiteUrl, String projectName, String descriptionHTML, String thumbnailImage, String category, String githubRepo, int viewCount) {
        this.user = user;
        this.websiteUrl = websiteUrl;
        this.projectName = projectName;
        this.descriptionHTML = descriptionHTML;
        this.thumbnailImage = thumbnailImage;
        this.category = category;
        this.githubRepo = githubRepo;
        this.viewCount = viewCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_code")
    private Long portfolioCode;
    @JoinColumn(name = "user_code")
    @ManyToOne
    private User user;
    @Column(name = "website_url")
    private String websiteUrl;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "description_html")
    private String descriptionHTML;
    @Column(name = "thumbnail_image")
    private String thumbnailImage;
    @Column(name = "category")
    private String category;
    @Column(name = "github_repo")
    private String githubRepo;
    @Column(name = "view_count")
    private int viewCount;
}
