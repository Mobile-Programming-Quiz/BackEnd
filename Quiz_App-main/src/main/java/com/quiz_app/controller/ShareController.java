// /share/result 같은 엔드포인트를 통해 공유할 데이터를 생성하고,
// 이미지 URL을 반환하는 API를 작성
//ShareController:
//
// /share/result 엔드포인트를 통해 퀴즈 결과 이미지를 생성하고 공유 URL을 반환합니다.
//shareResult 메서드는 요청 파라미터로 username, score, accuracy를 받아 ShareService의 createShareableImage 메서드를 호출하여 이미지를 생성합니다.
//uploadImageToS3 메서드를 호출하여 이미지를 S3에 업로드하고, 업로드된 이미지 URL을 클라이언트에 응답으로 반환합니다.

package com.quiz_app.controller;

import com.quiz_app.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    // 사용자의 퀴즈 결과를 기반으로 공유 가능한 이미지를 생성하고 URL을 반환하는 엔드포인트
    @GetMapping("/result")
    public ResponseEntity<String> shareResult(
            @RequestParam String username,
            @RequestParam int score,
            @RequestParam double accuracy) {
        try {
            // 로컬에 이미지 생성
            String localFilePath = shareService.createShareableImage(username, score, accuracy);

            // S3 또는 클라우드에 업로드하고 URL 가져오기
            String imageUrl = shareService.uploadImageToS3(localFilePath);

            return ResponseEntity.ok("Shareable image created: " + imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error creating shareable image");
        }
    }
}

