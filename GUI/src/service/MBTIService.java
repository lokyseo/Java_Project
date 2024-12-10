package service;


public class MBTIService implements TestService {

    private final String[] questions = {
            "나는 외부 세계에 집중하는 것을 선호한다.",
            "혼자보다 그룹에서 일하는 것을 더 즐긴다.",
            "즉흥적으로 행동하기보다는 정해진 계획을 따르는 것을 선호한다.",
            "나는 꿈꾸는 사람보다는 현실주의자이다.",
            "나는 사고보다는 감정을 더 중요하게 여긴다.",
            "일을 미리 끝내는 것을 선호한다.",
            "나는 개인적인 감정보다 논리에 따라 결정을 내리는 경향이 있다.",
            "새롭고 흥미로운 경험을 즐긴다."
    };

    @Override
    public String getTestName() {
        return "MBTI"; // MBTI 테스트 이름
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
        int extroversion = 0; // 외향성 점수
        int introversion = 0; // 내향성 점수
        int sensing = 0;      // 감각 점수
        int intuition = 0;    // 직관 점수
        int thinking = 0;     // 사고 점수
        int feeling = 0;      // 감정 점수
        int judging = 0;      // 판단 점수
        int perceiving = 0;   // 인식 점수

        for (int i = 0; i < answers.length; i++) {
            // 각 질문에 대한 답변에 따라 점수 계산
            switch (i) {
                case 0: // 외향성/내향성
                case 1:
                    if (answers[i] == 1) extroversion++; // 외향성
                    else if (answers[i] == 2) introversion++; // 내향성
                    break;
                case 2: // 감각/직관
                case 3:
                    if (answers[i] == 1) sensing++; // 감각
                    else if (answers[i] == 2) intuition++; // 직관
                    break;
                case 4: // 사고/감정
                case 5:
                    if (answers[i] == 1) thinking++; // 사고
                    else if (answers[i] == 2) feeling++; // 감정
                    break;
                case 6: // 판단/인식
                case 7:
                    if (answers[i] == 1) judging++; // 판단
                    else if (answers[i] == 2) perceiving++; // 인식
                    break;
            }
        }

        // MBTI 유형 결정
        String personality = (extroversion > introversion ? "E" : "I") +
                (sensing > intuition ? "S" : "N") +
                (thinking > feeling ? "T" : "F") +
                (judging > perceiving ? "J" : "P");


        return personalityToIndex(personality); // MBTI 유형을 인덱스로 반환
    }

    // MBTI 문자열을 정수로 변환하는 메서드
    private int personalityToIndex(String personality) {
        return switch (personality) {
            case "ESTJ" -> 1;
            case "ISTJ" -> 2;
            case "ESFJ" -> 3;
            case "ISFJ" -> 4;
            case "ENFP" -> 5;
            case "INFP" -> 6;
            case "ENTP" -> 7;
            case "INTP" -> 8;
            case "ESFP" -> 9;
            case "ISFP" -> 10;
            case "ENFJ" -> 11;
            case "INFJ" -> 12;
            case "ESTP" -> 13;
            case "ISTP" -> 14;
            case "ENTJ" -> 15;
            case "INTJ" -> 16;
            default -> 0; // 알 수 없는 유형 처리
        };
    }

    @Override
    public String getResultMessage(int result) {
        // MBTI 유형에 따른 메시지 반환
        return switch (result) {
            case 1 -> "ESTJ: 집행자";
            case 2 -> "ISTJ: 물류 관리자";
            case 3 -> "ESFJ: 상담가";
            case 4 -> "ISFJ: 수호자";
            case 5 -> "ENFP: 캠페이너";
            case 6 -> "INFP: 중재자";
            case 7 -> "ENTP: 논쟁자";
            case 8 -> "INTP: 논리학자";
            case 9 -> "ESFP: 연예인";
            case 10 -> "ISFP: 모험가";
            case 11 -> "ENFJ: 주인공";
            case 12 -> "INFJ: 옹호자";
            case 13 -> "ESTP: 기업가";
            case 14 -> "ISTP: virtuoso";
            case 15 -> "ENTJ: 지휘관";
            case 16 -> "INTJ: 건축가";
            default -> "알 수 없는 유형"; // 알 수 없는 유형 처리
        };
    }

}
