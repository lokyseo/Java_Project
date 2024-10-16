package service;

public interface TestService {
    // 테스트 이름 반환
    String getTestName();

    // 질문 수 반환
    int getQuestionCount();

    // 특정 질문 반환
    String getQuestion(int index);

    // 결과 계산 (결과를 정수형으로 반환)
    int calculateResult(int[] answers);

    // 결과 메시지 반환 (정수형 결과를 받아 메시지 반환)
    String getResultMessage(int result);
}
