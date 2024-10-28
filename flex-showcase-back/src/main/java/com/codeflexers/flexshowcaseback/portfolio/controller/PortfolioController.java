package com.codeflexers.flexshowcaseback.portfolio.controller;

import com.codeflexers.flexshowcaseback.portfolio.dto.PortfolioDTO;
import com.codeflexers.flexshowcaseback.portfolio.service.PortfolioService;
import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping()
    public ResponseEntity<List<PortfolioDTO>> getPortfolio(@RequestParam Long userCode){
        return ResponseEntity.ok().body(portfolioService.getPortfolio(userCode));
    }
    @PutMapping()
    public ResponseEntity<String> createPortfolio(@RequestBody PortfolioDTO portfolioDTO, @AuthenticationPrincipal CustomUserDetails user){
        try{
            return ResponseEntity.ok().body(portfolioService.createPortfolio(portfolioDTO, user));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping()
    public ResponseEntity<?> modifyPortfolio(@RequestBody PortfolioDTO portfolio, @AuthenticationPrincipal CustomUserDetails user){
        return ResponseEntity.ok().body(portfolioService.modifyPortfolio(portfolio,user));
    }
    @DeleteMapping()
    public ResponseEntity<?> deletePortfolio(@RequestParam Long portfolioCode){
        return ResponseEntity.ok().body(portfolioService.deletePortfolio(portfolioCode));
    }
    @PostMapping("/view-count")
    public void increaseViewCount(Long portfolioCode, HttpServletRequest request){
        portfolioService.increaseViewCount(portfolioCode,request.getRemoteAddr());
    }
}
