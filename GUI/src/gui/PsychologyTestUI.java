package gui;
import service.TestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PsychologyTestUI extends JFrame {

    private JTextArea questionArea; // 질문 표시할 부분
    private JButton nextButton;     // 다음 버튼
    private JRadioButton option1, option2, option3, option4; // 선택지
    private ButtonGroup optionGroup; // 버튼 그룹

    private int currentQuestionIndex = 0;
    private int[] answers;          // 사용자의 답 저장

    private TestService service;    // 로직을 담당할 서비스 객체

    // 생성자: 선택한 심리검사 서비스 객체를 전달받음
    public PsychologyTestUI(TestService service) {
        this.service = service; // 심리검사 서비스 연결
        answers = new int[service.getQuestionCount()]; // 질문 수만큼 배열 생성

        setTitle(service.getTestName() + " 검사"); // 윈도우 제목에 검사 이름 표시
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // 중앙에 배치

        // GUI 컴포넌트 설정
        questionArea = new JTextArea(5, 20);
        questionArea.setEditable(false);

        option1 = new JRadioButton("매우 동의하지 않음");
        option2 = new JRadioButton("동의하지 않음");
        option3 = new JRadioButton("동의함");
        option4 = new JRadioButton("매우 동의함");

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

        // 버튼 이벤트 리스너
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveAnswer()) {
                    currentQuestionIndex++;
                    if (currentQuestionIndex < service.getQuestionCount()) {
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
        String question = service.getQuestion(currentQuestionIndex);
        questionArea.setText((currentQuestionIndex + 1) + ". " + question);
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

    // 결과 표시
    private void showResult() {
        int result = service.calculateResult(answers); // 결과 계산
        String resultMessage = service.getResultMessage(result); // 결과 메시지

        JOptionPane.showMessageDialog(this, resultMessage); // 결과 보여주기

        // JFrame 창 닫기
        dispose(); // 현재 JFrame 창 닫기

        }
    }