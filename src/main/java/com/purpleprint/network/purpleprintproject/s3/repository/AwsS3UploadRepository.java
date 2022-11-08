package com.purpleprint.network.purpleprintproject.s3.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * Class : AwsS3UploadRepository
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
@Repository
public class AwsS3UploadRepository {

    private final AmazonS3 amazonS3;

    @Autowired
    public AwsS3UploadRepository(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Async
    public void awsUpload(String s3Location, String conversionFileName, MultipartFile multipartFile) {
        //파일명에 따른 ContentType 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        try (final InputStream uploadFileInputStream = multipartFile.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(s3Location, conversionFileName, uploadFileInputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("upload 완료");
    }
}
