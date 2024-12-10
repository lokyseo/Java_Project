import gui.PsychologyTestUI;

import service.EnneagramService;
import service.MBTIService;
import service.TestService;
import service.CheckBox;
import service.IdealTypeSurveyUI;
import service.SemesterProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main extends JFrame {
    private JLabel timeLabel;

    public Main() {
        // 메인 프레임 설정
        setTitle("설문지 메인 페이지");
        setSize(1300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setResizable(false); // 크기 고정
        setLayout(new BorderLayout());
        // 아이콘 경로 확인 후 설정
        try {
            ImageIcon img = new ImageIcon(getClass().getResource("/resources/icon.png")); // 경로 확인
            setIconImage(img.getImage());
        } catch (Exception e) {
            System.out.println("아이콘 로드 실패: " + e.getMessage());
        }

        // 패널 추가
        add(createButtonPanel(), BorderLayout.WEST); // 버튼 패널
        add(createLeftPanel(), BorderLayout.EAST);  // 왼쪽 패널
    }

    /**
     * 버튼 패널 생성
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setPreferredSize(new Dimension(1000, 600));

        buttonPanel.add(createSurveyButton(
                "GUI/src/resources/mbti.png",
                "MBTI 검사",
                "MBTI검사로 이동합니다.",
                () -> {
            TestService mbtiService = new MBTIService();
            new PsychologyTestUI(mbtiService).setVisible(true);
        }));

        buttonPanel.add(createSurveyButton(
                "GUI/src/resources/ani.png",
                "에니어그램 검사",
                "애니어그램 검사로 이동합니다.",
                () -> {
            TestService enneagramService = new EnneagramService();
            new PsychologyTestUI(enneagramService).setVisible(true);
        }));

        buttonPanel.add(createSurveyButton(
                "GUI/src/resources/food.png",
                "음식추천",
                "식사추천으로 이동합니다.",
                () -> {
            SwingUtilities.invokeLater(() -> new CheckBox().setVisible(true));
        }));

        buttonPanel.add(createSurveyButton(
                "GUI/src/resources/love.png",
                "이상형 성격 검사",
                "이상형검사로 이동합니다.",
                () -> {
            SwingUtilities.invokeLater(() -> new IdealTypeSurveyUI().setVisible(true));
        }));

        return buttonPanel;
    }

    /**
     * 왼쪽 패널 생성
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
//        leftPanel.setOpaque(false); // 패널을 투명하게 설정
        leftPanel.setPreferredSize(new Dimension(300, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.decode("#c9e4ca"));
        //-----------------------------------------------------------------------------------------------
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50))); // 세로 방향으로 20px 갭

        // 현재 날짜와 시간을 가져옵니다.
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 날짜와 시간 포맷 설정
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // 날짜와 시간을 각각 포맷
        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);

        // 날짜와 시간을 표시할 레이블 생성 (각각 날짜와 시간을 따로 표시)
        JLabel dateLabel = new JLabel(formattedDate);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        dateLabel.setForeground(Color.decode("#364958"));
        leftPanel.add(dateLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 세로 방향으로 20px 갭


        // 시간 레이블 추가
        timeLabel = new JLabel(formattedTime);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        timeLabel.setForeground(Color.decode("#364958"));
        leftPanel.add(timeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 세로 방향으로 20px 갭

        // 시간 갱신 타이머 설정
        Timer timer = new Timer(1000, e -> updateTime()); // 1초 간격으로 시간 업데이트
        timer.start();

        leftPanel.add(timeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 세로 방향으로 20px 갭
        //-----------------------------------------------------------------------------------------------
        // 빈 공간을 채우는 컴포넌트 추가 (상단-중앙의 빈 공간)
        // 빈 공간을 추가 (조절 가능한 크기의 빈 박스)
        leftPanel.add(Box.createRigidArea(new Dimension(0, 290))); // 세로 방향으로 20px 갭

        //-----------------------------------------------------------------------------------------------


        SemesterProgressBar progressBar = new SemesterProgressBar();

        leftPanel.add(progressBar);



        return leftPanel;
    }
    /**
     * 시간 업데이트 메서드
     */
    private void updateTime() {
        // 현재 시간을 가져와서 포맷 설정
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentDateTime.format(timeFormatter);

        // 레이블에 새로운 시간 설정
        timeLabel.setText(formattedTime);
    }



    /**
     * 설문 버튼 생성
     */
    private JButton createSurveyButton(String imagePath, String tooltip,String message, Runnable action) {
        JButton button = new JButton();
        try {
            // 이미지 로드
            File imageFile = new File(imagePath);
            FileInputStream fis = new FileInputStream(imageFile);
            ImageIcon icon = new ImageIcon(fis.readAllBytes());
            fis.close();

            // 버튼에 이미지 설정
            button.setIcon(icon);
            button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

        } catch (IOException e) {
            // 이미지 로드 실패 시 기본 버튼 설정
            button.setText("이미지 로드 실패");
            button.setPreferredSize(new Dimension(150, 50));
            e.printStackTrace();
        }

        // 툴팁 및 액션 추가
        button.setToolTipText(tooltip);
        button.addActionListener((ActionEvent e) -> {
            if (message != null && !message.isEmpty()) {
                JOptionPane.showMessageDialog(null, message);
            }
            action.run();
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
