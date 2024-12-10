package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;

public class ImageSlider extends JFrame {
    private final String[] imagePaths;
    private final String[] linkUrls;
    private int currentImageIndex = 0;
    private JLabel imageLabel;
    private Timer timer;

    public ImageSlider(String[] imagePaths, String[] linkUrls) {
        this.imagePaths = imagePaths;
        this.linkUrls = linkUrls;

        // 기본 설정
        setTitle("이미지 슬라이더");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 이미지 레이블 생성
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadImage(imagePaths[currentImageIndex]);
        add(imageLabel, BorderLayout.CENTER);

        // 이미지 클릭 이벤트
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink(linkUrls[currentImageIndex]);
            }
        });

        // 이미지 변경 타이머 (3초마다 이미지 변경)
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeImage();
            }
        });
        timer.start();

        setLocationRelativeTo(null);  // 화면 중앙에 표시
        setVisible(true);
    }

    private void loadImage(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                imageLabel.setIcon(imageIcon); // 이미지 아이콘 설정
                repaint();  // 새 이미지로 다시 그리기
            } else {
                System.out.println("이미지 파일이 존재하지 않습니다: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeImage() {
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        loadImage(imagePaths[currentImageIndex]);
    }

    private void openLink(String linkUrl) {
        try {
            Desktop.getDesktop().browse(new URI(linkUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] imagePaths = {
                "resources/001.jpg",  // 첫 번째 이미지 경로
                "resources/002.jpg",  // 두 번째 이미지 경로
                "resources/003.jpg"   // 세 번째 이미지 경로
        };
        String[] linkUrls = {
                "https://www.example1.com",
                "https://www.example2.com",
                "https://www.example3.com"
        };
        new ImageSlider(imagePaths, linkUrls);
    }
}
