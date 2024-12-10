package service;


public class EnneagramService implements TestService {

    private final String[] questions = {
            "나는 자주 생각에 빠져 있다.",
            "나는 사회적 모임을 즐긴다.",
            "나는 이론보다는 실용성을 더 중요하게 여긴다.",
            "나는 다른 사람의 감정에 민감하다.",
            "나는 위험을 감수하는 것을 좋아한다.",
            "나는 즉흥적이기보다는 규칙적인 것을 선호한다.",
            "나는 자주 미래에 대해 걱정한다."
    };

    @Override
    public String getTestName() {
        return "에니어그램"; // 에니어그램 테스트 이름
    }

    @Override
    public int getQuestionCount() {
        return questions.length; // 질문 수 반환
    }

    @Override
    public String getQuestion(int index) {
        return questions[index]; // 특정 질문 반환
    }

    @Override
    public int calculateResult(int[] answers) {
        int score = 0;
        for (int answer : answers) {
            score += answer; // 답변을 통해 점수 계산
        }
        return score % 9 + 1; // 1~9 범위 내에서 성격 유형 결정
    }

    @Override
    public String getResultMessage(int result) {
        // 결과에 따른 성격 유형 메시지 반환
        return switch (result) {
            case 1 -> "유형 1: 개혁가";
            case 2 -> "유형 2: 조력자";
            case 3 -> "유형 3: 성취자";
            case 4 -> "유형 4: 개성 추구자";
            case 5 -> "유형 5: 탐구자";
            case 6 -> "유형 6: 충실한 사람";
            case 7 -> "유형 7: 열정가";
            case 8 -> "유형 8: 도전가";
            case 9 -> "유형 9: 평화주의자";
            default -> "알 수 없음"; // 알 수 없는 유형 처리
        };
    }
}
