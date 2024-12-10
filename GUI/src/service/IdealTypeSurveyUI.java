package service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IdealTypeSurveyUI extends JFrame {

    private JTextArea questionArea; // 질문 표시할 부분
    private JButton nextButton;     // 다음 버튼
    private JRadioButton option1, option2, option3, option4; // 선택지
    private ButtonGroup optionGroup; // 버튼 그룹

    private int currentQuestionIndex = 0;
    private int[] answers;          // 사용자의 답 저장

    // 질문 내용
    private String[] questions = {
            "이상형이 함께 데이트할 때 가장 좋아할 장소는?",
            "이상형의 성격은?",
            "이상형이 주말에 가장 좋아하는 활동은?",
            "이상형이 스트레스를 받았을 때 가장 자주 하는 일은?",
            "이상형이 좋아하는 취미는 무엇일까요?",
            "이상형은 어느 정도의 감정을 잘 표현하나요?",
            "이상형이 결정을 내릴 때 어떤 방식을 선호할까요?",
            "이상형의 이상적인 데이트는?",
            "이상형이 가장 중요하게 생각하는 가치관은?",
            "이상형이 가장 좋아하는 스타일의 의상은?"
    };

    // 각 질문에 대한 선택지
    private String[][] options = {
            {"활기찬 분위기의 카페나 거리에서 즐기는 데이트", "조용한 분위기의 레스토랑에서의 오붓한 시간", "자연 속에서 여유롭게 산책하는 데이트", "놀이공원이나 다양한 액티비티가 있는 장소"},
            {"활발하고 사람들과 소통을 잘하는 성격", "내성적이고 조용한 성격", "모험을 좋아하고, 새로운 것을 시도하는 성격", "신중하고 생각이 깊은 성격"},
            {"친구들과 외출하거나 활동적인 스포츠 즐기기", "집에서 편안히 영화나 책을 읽기", "여행이나 새로운 장소를 탐험하는 것", "조용한 시간을 보내며 자기개발 활동을 하는 것"},
            {"스트레스를 풀기 위해 친구들과 소통하거나 운동을 한다", "혼자 시간을 보내며 생각을 정리한다", "새로운 경험을 통해 스트레스를 해소한다", "스트레스를 쌓지 않으려고 계획적으로 행동한다"},
            {"사람들과 함께하는 활동적인 취미", "예술적인 창작 활동이나 취미", "액티브한 여행이나 모험", "책 읽기, 영화 보기 등 혼자 즐길 수 있는 취미"},
            {"감정을 솔직하게 표현하는 성격", "감정을 잘 표현하지 않지만, 속마음은 잘 드러난다", "감정 표현이 자유롭고 적극적이다", "감정을 잘 숨기고 내면을 중요시하는 성격"},
            {"직관적이고 감성적으로 결정을 내린다", "신중하게 계획을 세워 결정을 내린다", "다른 사람들의 의견을 반영하여 결정한다", "내가 생각한대로 고수하는 것을 좋아한다"},
            {"사람들과의 소셜한 모임에서 즐거운 시간을 보내는 것", "두 사람만의 시간을 보내며 서로의 이야기를 나누는 것", "새로운 경험이나 모험을 함께하는 것", "차분한 분위기에서 조용히 이야기를 나누는 것"},
            {"자유와 모험을 중시하는 가치관", "신뢰와 안정감을 중시하는 가치관", "창의성과 도전을 중요하게 여기는 가치관", "생각과 분석을 중요하게 생각하는 가치관"},
            {"편안하고 활동적인 스타일", "세련되고 깔끔한 스타일", "자유롭고 개성 있는 스타일", "고급스럽고 신중한 스타일"}
    };

    // 각 선택지의 점수 (A=1, B=2, C=3, D=4)
    private int[] scores = new int[10];

    public IdealTypeSurveyUI() {
        answers = new int[10]; // 10개의 질문에 대한 답변 저장

        setTitle("이상형 성격 검사");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // 아이콘 경로 확인 후 설정
        try {
            ImageIcon img = new ImageIcon(getClass().getResource("/resources/icon.png")); // 경로 확인
            setIconImage(img.getImage());
        } catch (Exception e) {
            System.out.println("아이콘 로드 실패: " + e.getMessage());
        }

        // GUI 설정
        questionArea = new JTextArea(5, 20);
        questionArea.setEditable(false);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        nextButton = new JButton("다음");

        // Layout 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.add(option1);
        optionPanel.add(option2);
        optionPanel.add(option3);
        optionPanel.add(option4);

        mainPanel.add(questionArea, BorderLayout.NORTH);
        mainPanel.add(optionPanel, BorderLayout.CENTER);
        mainPanel.add(nextButton, BorderLayout.SOUTH);

        add(mainPanel);

        // 첫 번째 질문 표시
        updateQuestion();

        // 다음 버튼 이벤트 리스너
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveAnswer()) {
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        updateQuestion(); // 다음 질문 표시
                    } else {
                        showResult(); // 결과 표시
                    }
                }
            }
        });
    }

    // 질문 업데이트
    private void updateQuestion() {
        questionArea.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);
        optionGroup.clearSelection(); // 선택 초기화
    }

    // 답 저장
    private boolean saveAnswer() {
        if (option1.isSelected()) {
            answers[currentQuestionIndex] = 1;
        } else if (option2.isSelected()) {
            answers[currentQuestionIndex] = 2;
        } else if (option3.isSelected()) {
            answers[currentQuestionIndex] = 3;
        } else if (option4.isSelected()) {
            answers[currentQuestionIndex] = 4;
        } else {
            JOptionPane.showMessageDialog(this, "답변을 선택해주세요.");
            return false;
        }
        return true;
    }

    // 결과값 계산
    private int calculateTotalScore() {
        int totalScore = 0;
        for (int answer : answers) {
            totalScore += answer;
        }
        return totalScore;
    }


    // 결과 메시지
    private void showResult() {
        int totalScore = calculateTotalScore();

        String resultMessage = getResultMessage(totalScore);
        JOptionPane.showMessageDialog(this, resultMessage); // 결과 메시지 보여주기
        dispose(); // 창 닫기
    }

    // 결과 메시지 텍스트 반환
    private String getResultMessage(int totalScore) {
        if (totalScore <= 20) {
            return "당신의 이상형은 내성적이고 차분한 성격의 사람입니다.\n"
                    + "이상형은 사람들과 큰 대화보다 혼자만의 시간을 소중히 여깁니다.\n"
                    + "자신의 감정을 잘 표현하지 않으며, 차분하고 안정적인 환경을 좋아합니다.\n"
                    + "두 사람만의 조용한 시간을 보내며, 깊은 대화를 나누는 것을 선호합니다.";
        } else if (totalScore <= 30) {
            return "당신의 이상형은 신중하고 지적인 성격의 사람입니다.\n"
                    + "이상형은 사고가 깊고, 자신의 의견을 잘 표현합니다.\n"
                    + "계획적이고 신중하게 행동하며, 어떤 일을 결정할 때 신중하게 시간을 투자합니다.\n"
                    + "조용한 환경에서 두 사람이 함께 시간을 보내며 서로의 의견을 나누는 것을 좋아합니다.";
        } else if (totalScore <= 40) {
            return "당신의 이상형은 활발하고 사교적인 성격의 사람입니다.\n"
                    + "이상형은 사람들과의 소셜 활동을 즐기며, 새로운 사람들과 쉽게 친해집니다.\n"
                    + "자신의 감정을 솔직하게 표현하며, 외향적이고 적극적인 성격입니다.\n"
                    + "활동적인 데이트나 모임에서 시간을 보내는 것을 좋아합니다.";
        } else {
            return "당신의 이상형은 모험적이고 개방적인 성격의 사람입니다.\n"
                    + "이상형은 새로운 경험과 모험을 좋아하며, 자유롭고 개방적인 성격입니다.\n"
                    + "자신의 감정을 잘 표현하고, 다양한 경험을 함께 할 사람을 원합니다.\n"
                    + "여행이나 모험적인 활동을 함께 즐기는 데이트를 선호합니다.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IdealTypeSurveyUI().setVisible(true));
    }
}