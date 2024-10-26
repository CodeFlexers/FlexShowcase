package com.codeflexers.flexshowcaseback.portfolio.repository;

import com.codeflexers.flexshowcaseback.portfolio.dto.PortfolioDTO;
import com.codeflexers.flexshowcaseback.portfolio.entity.Portfolio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    @Query("SELECT new com.codeflexers.flexshowcaseback.portfolio.dto.PortfolioDTO(p.portfolioCode, p.user.userNickname,p.websiteUrl,p.projectName,p.descriptionHTML,p.thumbnailImage,p.category,p.githubRepo,p.viewCount) FROM Portfolio p WHERE p.user.userCode = :userCode")
    List<PortfolioDTO> getPortfolio(Long userCode);

    @Modifying
    @Transactional
    @Query("UPDATE Portfolio p SET p.viewCount = p.viewCount + 1 WHERE p.portfolioCode = :portfolioCode")
    void incrementViewCount(Long portfolioCode);

    boolean existsByWebsiteUrl(String websiteUrl);
}
