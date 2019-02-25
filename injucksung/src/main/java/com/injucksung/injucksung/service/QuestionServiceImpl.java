package com.injucksung.injucksung.service;

import com.injucksung.injucksung.domain.ContentFile;
import com.injucksung.injucksung.domain.ExplanationFile;
import com.injucksung.injucksung.domain.Question;
import com.injucksung.injucksung.domain.enums.PageSize;
import com.injucksung.injucksung.dto.QuestionForm;
import com.injucksung.injucksung.dto.SelectedBookContentForQuizForm;
import com.injucksung.injucksung.repository.BookContentRepository;
import com.injucksung.injucksung.repository.QuestionCategoryRepository;
import com.injucksung.injucksung.repository.QuestionRepository;
import com.injucksung.injucksung.util.FileStorageUtil;
import com.injucksung.injucksung.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionCategoryRepository questionCategoryRepository;
    private final BookContentRepository bookContentRepository;
    private final FileStorageUtil fileStorageUtil;
    private final S3Uploader s3Uploader;
    private final Environment env;

    @Override
    @Transactional
    public Question addQuestion(QuestionForm questionForm) throws IOException {

        Question question = new Question();
        BeanUtils.copyProperties(questionForm, question, "contentFile", "explanationFile");

//        ContentFile contentFile = fileStorageUtil.uploadContentFile(env.getProperty("file.upload-dir"), questionForm.getContentFile());
//        ExplanationFile explanationFile = fileStorageUtil.uploadExplanationFile(env.getProperty("file.upload-dir"), questionForm.getExplanationFile());

        MultipartFile contentFileForm = questionForm.getContentFile();
        MultipartFile explanationFileForm = questionForm.getExplanationFile();

        String contentFileUrl = s3Uploader.putS3(contentFileForm, "static");
        String explanationFileUrl = s3Uploader.putS3(explanationFileForm, "static");

        ContentFile contentFile = ContentFile.builder()
                .originName(contentFileForm.getOriginalFilename())
                .savedName(contentFileUrl)
                .path(contentFileUrl)
                .length(contentFileForm.getSize())
                .type(contentFileForm.getContentType())
                .regDate(LocalDateTime.now())
                .build();

        ExplanationFile explanationFile = ExplanationFile.builder()
                .originName(explanationFileForm.getOriginalFilename())
                .savedName(explanationFileUrl)
                .path(explanationFileUrl)
                .length(explanationFileForm.getSize())
                .type(explanationFileForm.getContentType())
                .regDate(LocalDateTime.now())
                .build();


        question.setBookContent(bookContentRepository.findBookContentById(questionForm.getBookContentId()));
        question.setQuestionCategory(questionCategoryRepository.findQuestionCategoryById(questionForm.getQuestionCategoryId()));
        question.setContentFile(contentFile);
        question.setExplanationFile(explanationFile);

        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {

    }

    @Override
    @Transactional
    public Question modifyQuestion(QuestionForm questionForm) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> getQuestionList(Long bookContentId) {
        return questionRepository.findQuestionByBookContentId(bookContentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> getQuestionList(SelectedBookContentForQuizForm selectedBookContentForQuizForm) {
        return questionRepository.findQuestionByBookContentId
                (selectedBookContentForQuizForm.getBookContentIds());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> getQuestionList(int start) {
        Pageable pageable = PageRequest.of(start, PageSize.QUESTION.getLimit());
        return questionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Question getQuestionById(Long questionId) {
        return questionRepository.findQuestionById(questionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> getQuestionByBookContentId(Long bookContentId) {
        return questionRepository.findQuestionByBookContentId(bookContentId);
    }
}
