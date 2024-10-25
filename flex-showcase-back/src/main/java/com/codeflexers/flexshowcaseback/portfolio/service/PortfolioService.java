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
            portfolioRepository.save(portfolio);
            return portfolio;
        } catch (Exception e) {
            tool.writeErrorLog(e.getMessage(),user.getUserCode());
            return "생성 실패!";
        }
    }

    @Transactional
    public String modifyPortfolio(PortfolioDTO portfolio, CustomUserDetails user) {
        Optional<Portfolio> portfolioEntity = portfolioRepository.findById(portfolio.getPortfolioCode());
        try {
            portfolioEntity.ifPresent(p -> {
                if (user.getUserCode().equals(p.getUser().getUserCode())) {
                    Portfolio modifyData = new Portfolio(
                            p.getPortfolioCode(),
                            p.getUser(),
                            portfolio.getWebsiteUrl(),
                            portfolio.getProjectName(),
                            portfolio.getDescriptionHTML(),
                            portfolio.getThumbnailImage(),
                            portfolio.getCategory(),
                            portfolio.getGithubRepo(),
                            p.getViewCount()
                    );
                    portfolioRepository.save(modifyData);
                }
            });
            return "포트폴리오 수정에 성공했습니다.";
        } catch (Exception e){
            tool.writeErrorLog(e.getMessage(),user.getUserCode());
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
            portfolioRepository.incrementViewCount(portfolioCode);
            session.setAttribute(sessionKey,true);
        }
    }

}
