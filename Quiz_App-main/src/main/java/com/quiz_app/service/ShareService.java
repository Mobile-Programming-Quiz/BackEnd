package com.quiz_app.service;

import org.springframework.stereotype.Service;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@Service
public class ShareService {

    // 퀴즈 결과 이미지를 생성하고 파일로 저장한 후 파일 경로를 반환
    public String createShareableImage(String username, int score, double accuracy) throws IOException {
        // 이미지 크기 및 배경 설정
        int width = 400;
        int height = 300;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); // 배경색
        graphics.fillRect(0, 0, width, height);

        // 텍스트 설정
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("Quiz Result", 150, 50);

        graphics.setFont(new Font("Arial", Font.PLAIN, 16));
        graphics.drawString("Username: " + username, 50, 100);
        graphics.drawString("Score: " + score, 50, 140);
        graphics.drawString("Accuracy: " + accuracy + "%", 50, 180);

        // 이미지 저장
        String filePath = "share_images/" + username + "_quiz_result.png";
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // 디렉토리 생성
        ImageIO.write(image, "png", file);

        graphics.dispose();

        return filePath; // 생성된 이미지 파일 경로 반환
    }

    // TODO: S3 또는 다른 클라우드 스토리지에 이미지를 업로드하고 URL을 반환하는 메서드 작성
    public String uploadImageToS3(String localFilePath) {
        // S3에 업로드 로직 작성 필요
        // 예: S3Client.upload(localFilePath) -> URL 반환
        return "https://s3.amazonaws.com/bucket-name/" + localFilePath;
    }
}
