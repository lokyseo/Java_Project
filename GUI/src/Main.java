import gui.PsychologyTestUI;
import service.EnneagramService;
import service.MBTIService;
import service.TestService;

public class Main {
    public static void main(String[] args) {
        // MBTI 테스트를 위한 서비스 객체 생성
        TestService mbtiService = new MBTIService();
        PsychologyTestUI mbtiTestUI = new PsychologyTestUI(mbtiService);
        mbtiTestUI.setVisible(true); // MBTI 테스트 UI를 표시

        // MBTI 테스트 완료 후 에니어그램 테스트 진행

            // 에니어그램 테스트를 위한 서비스 객체 생성
        TestService enneagramService = new EnneagramService();
        PsychologyTestUI enneagramTestUI = new PsychologyTestUI(enneagramService);
        enneagramTestUI.setVisible(true); // 에니어그램 테스트 UI를 표시
    }
}
