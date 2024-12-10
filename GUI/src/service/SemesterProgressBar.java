package service;

// SemesterProgressBar.java
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SemesterProgressBar extends JPanel {

    // 개학일과 종강일 설정 (예시)
    private LocalDate startDate = LocalDate.of(2024, 9, 2);  // 개학일
    private LocalDate endDate = LocalDate.of(2024, 12, 20);  // 종강일

    public SemesterProgressBar() {
        // 패널 크기 설정
        setPreferredSize(new Dimension(100, 60));  // 패널 높이를 60으로 설정하여 여유 공간을 추가
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 현재 날짜
        LocalDate currentDate = LocalDate.now();

        // 개학일부터 종강일까지의 총 일수 계산
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);

        // 현재 날짜부터 종강일까지 남은 일수 계산
        long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);

        // 현재 날짜부터 개학일까지 경과된 일수 계산
        long elapsedDays = ChronoUnit.DAYS.between(startDate, currentDate);

        // 진행된 비율 계산 (0~1 사이의 값)
        double progressRatio = (double) elapsedDays / totalDays;

        // 그래프를 그릴 넓이 계산
        int barWidth = (int) (getWidth() * progressRatio);

        // 배경색 설정 (전체 기간)
        g.setColor(Color.decode("#3b6064"));
        g.fillRect(0, 0, getWidth(), 60);

        // 진행된 기간을 바 형태로 그리기
        g.setColor(Color.decode("#87bba2"));
        g.fillRect(0, 0, barWidth, 60);

        // 퍼센트로 진행 상황 표시
        int progressPercentage = (int) (progressRatio * 100);

        // 현재 날짜와 종강일까지 남은 날짜 텍스트 출력
        g.setFont(new Font("Malgun Gothic", Font.BOLD, 20));  // 폰트, 굵게, 크기 20
        g.setColor(Color.decode("#364958"));
        String text = "종강까지 "+remainingDays+"일 (" + progressPercentage + "%)";
        g.drawString(text, getWidth() / 2 - g.getFontMetrics().stringWidth(text) / 2, 35);
    }
}
