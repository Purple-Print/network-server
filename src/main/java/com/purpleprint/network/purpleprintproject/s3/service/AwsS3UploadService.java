package com.purpleprint.network.purpleprintproject.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.purpleprint.network.purpleprintproject.s3.dto.FileDTO;
import com.purpleprint.network.purpleprintproject.s3.exception.FileEmptyException;
import com.purpleprint.network.purpleprintproject.s3.repository.AwsS3UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * <pre>
 * Class : AwsS3UploadService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class AwsS3UploadService {

    private final AwsS3UploadRepository awsS3UploadRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public AwsS3UploadService(AwsS3UploadRepository awsS3UploadRepository) {
        this.awsS3UploadRepository = awsS3UploadRepository;
    }

    public void validateFileExists(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            System.out.println("파일이 존재하지 않습니다.");
            throw new FileEmptyException("파일이 존재하지 않습니다.");
        }
    }

    public FileDTO upload(String folderName, MultipartFile multipartFile) {

        validateFileExists(multipartFile); //파일 존재 여부 확인

        String originalFileName = multipartFile.getOriginalFilename(); //파일명 + 확장자

        int pos = originalFileName.lastIndexOf("."); // .위치

        String extension = originalFileName.substring(pos + 1); //확장자
        String fileName = originalFileName.substring(0, pos); //파일명

        String conversionFileName = fileName + UUID.randomUUID() + "." + extension; //변환된 파일명

        String s3Location = bucket + "/" + folderName;      //저장소

        String s3Key = folderName + "/" + conversionFileName;

       awsS3UploadRepository.awsUpload(s3Location, conversionFileName, multipartFile);

        String url = "https://purpleprint-bucket.s3.ap-northeast-2.amazonaws.com/" + folderName + "/" + conversionFileName;

        return new FileDTO(originalFileName, conversionFileName, url, s3Key);
    }
}
