package com.load.doldari.dto;

public record InputAnalysisResult(
        String intent, // 사용자의 의도 : CODE_GENERATION, DEBUGGING 등
        int cognitiveLoad, // 인지 부하 0 ~ 10
        String[] keywords, // 핵심 키워드
        String recommendedMode, // 추천 모드 (SCANNER, SCHOLAR, SPRINTER, ARCHITECT
        String reasoning // 판단 근거
) {
}
