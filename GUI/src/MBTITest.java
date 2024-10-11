import javax.swing.*; // Swing 패키지 임포트
import java.awt.*; // AWT 패키지 임포트
import java.awt.event.ActionEvent; // ActionEvent 클래스 임포트
import java.awt.event.ActionListener; // ActionListener 인터페이스 임포트

public class MBTITest extends JFrame { // JFrame을 상속한 MBTITest 클래스 정의
    // MBTI 점수를 저장할 변수 초기화
    private int extroversionScore = 0;
    private int introversionScore = 0;
    private int sensingScore = 0;
    private int intuitionScore = 0;
    private int thinkingScore = 0;
    private int feelingScore = 0;
    private int judgingScore = 0;
    private int perceivingScore = 0;

    private JTextArea questionArea; // 질문을 표시할 텍스트 영역
    private JButton answerAButton; // A 선택 버튼
    private JButton answerBButton; // B 선택 버튼
    private JButton submitButton; // 제출 버튼
    private int questionIndex = 0; // 현재 질문 인덱스

    // 질문 배열 선언
    private String[] questions = {
            "1. 대인 관계에서 당신은?\nA: 사람들과 함께하는 것을 좋아한다 (Extroversion)\nB: 혼자 있는 것을 선호한다 (Introversion)",
            "2. 일을 할 때 당신은?\nA: 세부 사항에 주의한다 (Sensing)\nB: 전체적인 그림을 본다 (Intuition)",
            "3. 의사결정 시 당신은?\nA: 논리적으로 접근한다 (Thinking)\nB: 감정을 고려한다 (Feeling)",
            "4. 계획에 대해 당신은?\nA: 계획을 세우고 따르는 것을 좋아한다 (Judging)\nB: 즉흥적으로 행동하는 것을 좋아한다 (Perceiving)"
    };

    public MBTITest() { // 생성자
        setTitle("MBTI Test"); // 프레임 제목 설정
        setLayout(new BorderLayout()); // 레이아웃을 BorderLayout으로 설정
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 종료
        setSize(400, 300); // 프레임 크기 설정
        setLocationRelativeTo(null); // 화면 중앙에 배치

        questionArea = new JTextArea(); // 질문을 표시할 텍스트 영역 생성
        questionArea.setEditable(false); // 텍스트 영역을 편집 불가능으로 설정
        questionArea.setLineWrap(true); // 자동 줄바꿈 활성화
        questionArea.setWrapStyleWord(true); // 단어 단위로 줄바꿈
        add(new JScrollPane(questionArea), BorderLayout.CENTER); // 텍스트 영역을 스크롤 가능하게 추가

        JPanel buttonPanel = new JPanel(); // 버튼 패널 생성
        answerAButton = new JButton("A"); // A 버튼 생성
        answerBButton = new JButton("B"); // B 버튼 생성
        submitButton = new JButton("Submit"); // 제출 버튼 생성

        // 버튼 패널에 버튼 추가
        buttonPanel.add(answerAButton);
        buttonPanel.add(answerBButton);
        add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널을 하단에 추가

        // A 버튼 클릭 시 이벤트 처리
        answerAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer("A"); // A 답변 처리
            }
        });

        // B 버튼 클릭 시 이벤트 처리
        answerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer("B"); // B 답변 처리
            }
        });

        // 제출 버튼 클릭 시 이벤트 처리
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult(); // 결과 표시
            }
        });

        add(submitButton, BorderLayout.NORTH); // 제출 버튼을 상단에 추가

        displayQuestion(); // 첫 번째 질문 표시
    }

    private void handleAnswer(String answer) { // 답변 처리 메소드
        switch (questionIndex) { // 현재 질문 인덱스에 따라
            case 0: // 첫 번째 질문
                if (answer.equals("A")) extroversionScore++; // A 선택 시 외향 점수 증가
                else introversionScore++; // B 선택 시 내향 점수 증가
                break;
            case 1: // 두 번째 질문
                if (answer.equals("A")) sensingScore++; // A 선택 시 감각 점수 증가
                else intuitionScore++; // B 선택 시 직관 점수 증가
                break;
            case 2: // 세 번째 질문
                if (answer.equals("A")) thinkingScore++; // A 선택 시 사고 점수 증가
                else feelingScore++; // B 선택 시 감정 점수 증가
                break;
            case 3: // 네 번째 질문
                if (answer.equals("A")) judgingScore++; // A 선택 시 판단 점수 증가
                else perceivingScore++; // B 선택 시 인식 점수 증가
                break;
        }
        questionIndex++; // 질문 인덱스 증가
        if (questionIndex < questions.length) { // 아직 질문이 남아있다면
            displayQuestion(); // 다음 질문 표시
        } else { // 모든 질문이 끝났다면
            questionArea.setText("모든 질문이 완료되었습니다. 결과를 제출하세요."); // 완료 메시지
            answerAButton.setEnabled(false); // A 버튼 비활성화
            answerBButton.setEnabled(false); // B 버튼 비활성화
        }
    }

    private void displayQuestion() { // 질문 표시 메소드
        questionArea.setText(questions[questionIndex]); // 현재 질문 텍스트 설정
    }

    private void showResult() { // 결과 표시 메소드
        String mbti = ""; // MBTI 결과를 저장할 문자열
        // 점수를 기반으로 MBTI 유형 결정
        mbti += (extroversionScore > introversionScore) ? "E" : "I"; // 외향/내향
        mbti += (sensingScore > intuitionScore) ? "S" : "N"; // 감각/직관
        mbti += (thinkingScore > feelingScore) ? "T" : "F"; // 사고/감정
        mbti += (judgingScore > perceivingScore) ? "J" : "P"; // 판단/인식

        // 결과 대화상자 표시
        JOptionPane.showMessageDialog(this, "당신의 MBTI 유형은: " + mbti);
        System.exit(0); // 결과를 확인한 후 종료
    }

    public static void main(String[] args) { // 메인 메소드
        SwingUtilities.invokeLater(() -> { // 이벤트 스레드에서 GUI 생성
            MBTITest mbtiTest = new MBTITest(); // MBTITest 인스턴스 생성
            mbtiTest.setVisible(true); // 프레임을 화면에 표시
        });
    }
}
