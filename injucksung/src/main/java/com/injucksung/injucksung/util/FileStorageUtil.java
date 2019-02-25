package com.injucksung.injucksung.util;

import com.injucksung.injucksung.domain.ContentFile;
import com.injucksung.injucksung.domain.ExplanationFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Component
public class FileStorageUtil {
    public ContentFile uploadContentFile(String uploadPath, MultipartFile multipartFile) throws IOException {
        String savedName = getSaveName(multipartFile);

        //파일을 저장할 폴더 생성(년 월 일 기준)
        String savedPath = calcPath(uploadPath);

        saveFile(multipartFile, savedName, savedPath);

        //uploadedFileName는 썸네일명으로 화면에 전달된다.
        return ContentFile.builder()
                .originName(multipartFile.getOriginalFilename())
                .savedName(savedName)
                .path(savedPath)
                .length(multipartFile.getSize())
                .type(multipartFile.getContentType())
                .regDate(LocalDateTime.now())
                .build();
    }

    private void saveFile(MultipartFile multipartFile, String savedName, String savedPath) throws IOException {
        //저장할 파일준비
        File target = new File(savedPath, savedName);

        //파일을 저장
        FileCopyUtils.copy(multipartFile.getBytes(), target);
    }

    private String getSaveName(MultipartFile multipartFile) {
        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        UUID uid = UUID.randomUUID();

        //원본파일 이름과 UUID 결합
        return uid.toString() + "_" + multipartFile.getOriginalFilename();
    }

    public ExplanationFile uploadExplanationFile(String uploadPath, MultipartFile multipartFile) throws IOException {

        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        String savedName = getSaveName(multipartFile);

        //파일을 저장할 폴더 생성(년 월 일 기준)
        String savedPath = calcPath(uploadPath);

        saveFile(multipartFile, savedName, savedPath);

        //uploadedFileName는 썸네일명으로 화면에 전달된다.
        return ExplanationFile.builder()
                .originName(multipartFile.getOriginalFilename())
                .savedName(savedName)
                .path(savedPath)
                .length(multipartFile.getSize())
                .type(multipartFile.getContentType())
                .regDate(LocalDateTime.now())
                .build();
    }

    //폴더 생성 함수
    @SuppressWarnings("unused")
    private String calcPath(String uploadPath) {

        LocalDateTime ldt = LocalDateTime.now();
        String yearPath = File.separator + ldt.getYear();
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(ldt.getMonthValue());
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(ldt.getDayOfMonth());
        makeDir(uploadPath, yearPath, monthPath, datePath);

        return uploadPath + datePath;
    }//calcPath

    //폴더 생성 함수
    private void makeDir(String uploadPath, String... paths) {

        if (new File(uploadPath + paths[paths.length - 1]).exists()) {
            return;
        }//if

        Arrays.stream(paths)
                .map(path -> new File(uploadPath + path))
                .filter(dirPath -> !dirPath.exists())
                .forEach(dirPath -> dirPath.mkdir());
    }
}
