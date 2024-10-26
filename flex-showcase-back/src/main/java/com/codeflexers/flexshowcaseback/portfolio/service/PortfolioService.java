package com.codeflexers.flexshowcaseback.portfolio.service;

import com.codeflexers.flexshowcaseback.common.Tool;
import com.codeflexers.flexshowcaseback.portfolio.dto.PortfolioDTO;
import com.codeflexers.flexshowcaseback.portfolio.entity.Portfolio;
import com.codeflexers.flexshowcaseback.portfolio.repository.PortfolioRepository;
import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import com.codeflexers.flexshowcaseback.security.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final Tool tool;
    public PortfolioService(PortfolioRepository portfolioRepository, Tool tool) {
        this.portfolioRepository = portfolioRepository;
        this.tool = tool;
    }

    public List<PortfolioDTO> getPortfolio(Long userCode) {
        return portfolioRepository.getPortfolio(userCode);
    }

    @Transactional
    public Object createPortfolio(PortfolioDTO portfolioDTO, CustomUserDetails user) {
        try {
            if (portfolioRepository.existsByWebsiteUrl(portfolioDTO.getWebsiteUrl())) {
                return "이미 존재하는 웹사이트 URL입니다.";
            }
            Portfolio portfolio = new Portfolio(
                    new User(user.getUserCode()),
                    portfolioDTO.getWebsiteUrl(),
                    portfolioDTO.getProjectName(),
                    portfolioDTO.getDescriptionHTML(),
                    portfolioDTO.getThumbnailImage(),
                    portfolioDTO.getCategory(),
                    portfolioDTO.getGithubRepo(),
                    0
            );
            portfolioRepository.save(portfolio);    //dto를 보내줄지? 메세지를 보내줄지? 상의 후 결정
            return portfolio;
        } catch (Exception e) {
            tool.writeErrorLog(e.getMessage(),user.getUserCode());
            return "포트폴리오 생성에 실패했습니다. 관리자에게 문의해주세요.";
        }
    }

    @Transactional
    public String modifyPortfolio(PortfolioDTO portfolio, CustomUserDetails user) {
        try {
            Optional<Portfolio> portfolioEntity = portfolioRepository.findById(portfolio.getPortfolioCode());

            portfolioEntity.ifPresent(p -> {
                if (user.getUserCode().equals(p.getUser().getUserCode())) {
                    if (!Objects.equals(p.getWebsiteUrl(), portfolio.getWebsiteUrl())) {
                        p.setWebsiteUrl(portfolio.getWebsiteUrl());
                    }
                    if (!Objects.equals(p.getProjectName(), portfolio.getProjectName())) {
                        p.setProjectName(portfolio.getProjectName());
                    }
                    if (!Objects.equals(p.getDescriptionHTML(), portfolio.getDescriptionHTML())) {
                        p.setDescriptionHTML(portfolio.getDescriptionHTML());
                    }
                    if (!Objects.equals(p.getThumbnailImage(), portfolio.getThumbnailImage())) {
                        p.setThumbnailImage(portfolio.getThumbnailImage());
                    }
                    if (!Objects.equals(p.getCategory(), portfolio.getCategory())) {
                        p.setCategory(portfolio.getCategory());
                    }
                    if (!Objects.equals(p.getGithubRepo(), portfolio.getGithubRepo())) {
                        p.setGithubRepo(portfolio.getGithubRepo());
                    }
                    // 엔티티는 자동으로 Dirty Checking되어 업데이트
                }
            });

            return "포트폴리오 수정에 성공했습니다.";
        } catch (Exception e) {
            tool.writeErrorLog(e.getMessage(), user.getUserCode());
            return "포트폴리오 수정에 실패했습니다. 관리자에게 문의해주세요.";
        }
    }


    @Transactional
    public String deletePortfolio(Long portfolioCode) {
        try{ portfolioRepository.deleteById(portfolioCode); return "삭제 성공";}
        catch (Exception e) { tool.writeErrorLog(e.getMessage(),"??"); return "삭제에 실패했습니다. 관리자에게 문의하세요.";}
    }

    public void increaseViewCount(Long portfolioCode, String remoteAddr) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        String sessionKey = "viewedPortfolio_"+portfolioCode+"_ip_"+remoteAddr;
        Boolean hasViewed = (Boolean) session.getAttribute(sessionKey);
        if(hasViewed == null || !hasViewed){
            portfolioRepository.    incrementViewCount(portfolioCode);
            session.setAttribute(sessionKey,true);
        }
    }

}
