import gui.PsychologyTestUI;
import service.EnneagramService;
import service.MBTIService;
import service.TestService;
import service.CheckBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;




public class Main extends JFrame {
    public Main() {
        // 메인 프레임 설정
        setTitle("설문지 메인 페이지");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 패널 설정
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10)); // 3행 1열 그리드 레이아웃, 간격 10px

        // 심리검사 버튼
        JButton btnPsychologySurvey = new JButton("MBTI 검사");
        btnPsychologySurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 심리검사 설문지 열기
                JOptionPane.showMessageDialog(null, "MBTI 심리검사 설문지로 이동합니다.");
                // 여기에 심리검사 설문지로 이동하는 코드를 추가하세요
                // MBTI 테스트를 위한 서비스 객체 생성
                TestService mbtiService = new MBTIService();
                PsychologyTestUI mbtiTestUI = new PsychologyTestUI(mbtiService);
                mbtiTestUI.setVisible(true); // MBTI 테스트 UI를 표시
            }
        });
        panel.add(btnPsychologySurvey);

        // 심리검사 버튼
        JButton btnPsychologySurvey2 = new JButton("에니어그램 조사");
        btnPsychologySurvey2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 심리검사 설문지 열기
                JOptionPane.showMessageDialog(null, "에니어그램 심리검사 설문지로 이동합니다.");
                // 여기에 심리검사 설문지로 이동하는 코드를 추가하세요
                // 에니어그램 테스트를 위한 서비스 객체 생성
                TestService enneagramService = new EnneagramService();
                PsychologyTestUI enneagramTestUI = new PsychologyTestUI(enneagramService);
                enneagramTestUI.setVisible(true); // 에니어그램 테스트 UI를 표시
            }
        });
        panel.add(btnPsychologySurvey2);

        // 음식 관련 설문 버튼
        JButton btnFoodSurvey = new JButton("음식 관련 설문지");
        btnFoodSurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 음식 관련 설문지 열기
                JOptionPane.showMessageDialog(null, "음식 관련 설문지로 이동합니다.");
                // 여기에 음식 관련 설문지로 이동하는 코드를 추가하세요
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        new CheckBox().setVisible(true);
                    }
                });

            }
        });
        panel.add(btnFoodSurvey);

        // 종료 버튼
        JButton btnExit = new JButton("종료");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 프로그램 종료
            }
        });
        panel.add(btnExit);

        // 패널을 프레임에 추가
        add(panel);
    }

    public static void main(String[] args) {

        //메인패이지 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
