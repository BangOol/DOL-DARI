package com.load.doldari.service;

import com.load.doldari.dto.InputAnalysisResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class InputAnalystServiceTest {

    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private InputAnalystService inputAnalystService;

    // 요구 사항
    // 1. InputAnalystService가 ChatClient를 올바르게 호출하는가?
    // 2. LLM이 예상된 응답을 주었을 때, 서비스가 올바른 DTO를 반환하는가?
    @Test
    @DisplayName("사용자의 입력이 주어지면, 분석 결과를 반환한다.")
    void analyze_returns_result_on_valid_input() {
        // given
        String userInput = "로그인 기능 구현해 줘";
        InputAnalysisResult expectedResult = new
                InputAnalysisResult(
                "CODE_GENERATION",
                5,
                new String[]{"login", "auth"},
                "SPRINTER",
                "코드 생성 요청"
        );

        // API 요청 및 처리 과정
        // 1. prompt -> RequestSpec
        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        given(chatClient.prompt()).willReturn(requestSpec);

        // 2. user -> RequestSpec (자기 자신 반환)
        given(requestSpec.user(anyString())).willReturn(requestSpec);

        // 3. call() -> CallResponseSpec
        ChatClient.CallResponseSpec responseSpec = mock(ChatClient.CallResponseSpec.class);
        given(requestSpec.call()).willReturn(responseSpec);

        // 4. entity () -> Result Object
        given(responseSpec.entity(InputAnalysisResult.class)).willReturn(expectedResult);


        // when
        InputAnalysisResult actualResult = inputAnalystService.analyze(userInput);

        // then
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.intent()).isEqualTo("CODE_GENERATION");
        assertThat(actualResult.recommendedMode()).isEqualTo("SPRINTER");

    }

}