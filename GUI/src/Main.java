import gui.IdealTypeSurveyUI;
import gui.PsychologyTestUI;
import service.EnneagramService;
import service.MBTIService;
import service.TestService;
import service.CheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
        // 메인 프레임 설정
        setTitle("설문지 메인 페이지");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setResizable(false); // 최대화 버튼 제거

        // 패널 설정
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2)); // 3행 2열 그리드 레이아웃

        // MBTI 심리검사 버튼
        JButton btnPsychologySurvey = createImageButton("img/mbti.png", "MBTI 검사", 500, 300);
        btnPsychologySurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestService mbtiService = new MBTIService();
                PsychologyTestUI mbtiTestUI = new PsychologyTestUI(mbtiService);
                mbtiTestUI.setVisible(true);
            }
        });
        panel.add(btnPsychologySurvey);

        // 에니어그램 검사 버튼
        JButton btnPsychologySurvey2 = createImageButton("img/ani.png", "에니어그램 검사", 500, 300);
        btnPsychologySurvey2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestService enneagramService = new EnneagramService();
                PsychologyTestUI enneagramTestUI = new PsychologyTestUI(enneagramService);
                enneagramTestUI.setVisible(true);
            }
        });
        panel.add(btnPsychologySurvey2);

        // 음식 설문조사 버튼
        JButton btnFoodSurvey = createImageButton("img/food.png", "음식 관련 설문지", 500, 300);
        btnFoodSurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new CheckBox().setVisible(true));
            }
        });
        panel.add(btnFoodSurvey);

        // 이상형 설문지 버튼
        JButton btnIdealSurvey = createImageButton("img/heart.png", "이상형 성격 검사", 500, 300); // 이미지 변경 가능
        btnIdealSurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new IdealTypeSurveyUI().setVisible(true)); // 이상형 설문지 UI 호출
            }
        });
        panel.add(btnIdealSurvey);

        // 패널을 프레임에 추가
        add(panel);
    }

    // 이미지 버튼 생성 함수
    private JButton createImageButton(String imagePath, String tooltip, int buttonWidth, int buttonHeight) {
        try {
            // 이미지 로드
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));

            // 버튼 크기에 맞게 이미지 크기 조정
            Image scaledImage = icon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);

            // 버튼 생성
            JButton button = new JButton(new ImageIcon(scaledImage));
            button.setToolTipText(tooltip); // 툴팁 추가
            button.setFocusPainted(false); // 포커스 테두리 제거
            button.setContentAreaFilled(false); // 버튼 배경 제거
            button.setBorderPainted(false); // 버튼 테두리 제거
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight)); // 버튼 크기 설정

            return button;
        } catch (Exception e) {
            e.printStackTrace();
            return new JButton("이미지 로드 실패"); // 이미지 로드 실패 시 기본 버튼 반환
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
