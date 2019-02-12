-- 책, 책목차
INSERT INTO book (name, publication_date, author, ISBN, publisher)
VALUES ('위포트 인적성 마스터 25대기업 인적성검사 통합 기본서', '2017-12-11', '위포트 연구소', '9791161980140', '위포트');
INSERT INTO book (name, publication_date, author, ISBN, publisher)
VALUES ('2019 인적성검사 통합 기본서', '2018-11-30', '하하하 연구소', '123461980140', '하하');
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, 'PART 1 언어능력', null, 0, 0, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, 'PART 2 수리능력', null, 1, 0, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, 'PART 3 추리능력', null, 2, 0, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, 'CHAPTER 01 어휘', 1, 0, 1, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, 'CHAPTER 02 독해', 1, 1, 1, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, '유형1 유의어', 4, 0, 2, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, '유형2 다의어', 4, 1, 2, false, null);
INSERT INTO book_content (book_id, name, super_book_content_id, sequence, depth, is_mock_test, recommend_time)
VALUES (1, '유형3 단어관계', 4, 2, 2, false, null);
-- 책, 책목차 끝

-- 문제 유형 (카테고리)
INSERT INTO question_category (name, parent_id, type)
VALUES ('언어능력', null, 'apt');
INSERT INTO question_category (name, parent_id, type)
VALUES ('수리능력', null, 'apt');
INSERT INTO question_category (name, parent_id, type)
VALUES ('추리능력', null, 'apt');
INSERT INTO question_category (name, parent_id, type)
VALUES ('어휘', 1, 'apt');
INSERT INTO question_category (name, parent_id, type)
VALUES ('독해', 1, 'apt');
-- 문제 유형 (카테고리) 끝

-- 유저 권한
INSERT INTO role (name)
VALUES ('ADMIN');
INSERT INTO role (name)
VALUES ('USER');
-- 유저 권한 끝

-- 유저
INSERT INTO user (email, password, nickname, phone)
VALUES ('admin@naver.com', '$2a$10$lPU6ud6tbGz45MTYfgWlruzOTrKyr4gmaKRmPObyMLHxO2BYEQUzm', 'admin', '01077777777');
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role (user_id, role_id)
VALUES (1, 2);

INSERT INTO user (email, password, nickname, phone)
VALUES ('user1234@naver.com', '$2a$10$XGr4fst0cJCFoi2vl2zDAO0F9RyLJUmYiNO8afhoEYkzAkQFjreLK', '김유저', '01012345678');
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2);
-- 유저 끝

-- 문제 이미파일 업로드, 문제 정보입력
INSERT INTO content_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 유의어 1번문제.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '100', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO explanation_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 유의어 1번문제 해설.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '120', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO question (question_category_id, book_content_id, book_number, content_file_id, explanation_file_id, correct,
                      choice_count)
VALUES (4, 6, 1, 1, 1, 3, 5);

INSERT INTO content_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 유의어 2번문제.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '100', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO explanation_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 유의어 2번문제 해설.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '120', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO question (question_category_id, book_content_id, book_number, content_file_id, explanation_file_id, correct,
                      choice_count)
VALUES (4, 6, 2, 2, 2, 5, 5);

INSERT INTO content_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 다의어 1번문제.pdf', '34dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '100', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO explanation_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 다의어 1번문제 해설.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '120', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO question (question_category_id, book_content_id, book_number, content_file_id, explanation_file_id, correct,
                      choice_count)
VALUES (4, 7, 1, 3, 3, 3, 5);

INSERT INTO content_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 다의어 2번문제.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '100', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO explanation_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 다의어 2번문제 해설.pdf', '4dcce9cb-8ffe-4f90-bc16-bf6f449c783b_ff1.png', 'image/png', '120', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO question (question_category_id, book_content_id, book_number, content_file_id, explanation_file_id, correct,
                      choice_count)
VALUES (4, 7, 2, 4, 4, 4, 5);

INSERT INTO content_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 단어관계 1번문제.pdf', '3554-5678-1234-5678', 'image/png', '100', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO explanation_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 단어관계 1번문제 해설.pdf', '1236-6678-3456-6678', 'image/png', '120', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO question (question_category_id, book_content_id, book_number, content_file_id, explanation_file_id, correct,
                      choice_count)
VALUES (4, 8, 1, 5, 5, 3, 5);

INSERT INTO content_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 단어관계 2번문제.pdf', '3554-5678-1234-5678', 'image/png', '100', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO explanation_file (origin_name, saved_name, type, length, reg_date, path)
VALUES ('위프트 언어 단어관계 2번문제 해설.pdf', '1236-6678-3456-6678', 'image/png', '120', now(), '/home/siyoon/tmp/pat/2019/02/11');
INSERT INTO question (question_category_id, book_content_id, book_number, content_file_id, explanation_file_id, correct,
                      choice_count)
VALUES (4, 8, 2, 6, 6, 4, 5);
-- 문제 이미지파일 업로드, 문제 정보입력

-- 시험기록 QuizRecord
INSERT INTO quiz_record (user_id, date, correct_count, total_count, is_done, title)
VALUES (2, now(), 2, 3, 1, '위포트 언어 유의어 영역');
-- 시험기록 QuizRecord 끝

-- result
INSERT INTO result(quiz_record_id, question_id, is_correct, checked_choice, sequence)
VALUES (1, 1, true, 3, 0);
INSERT INTO result(quiz_record_id, question_id, is_correct, checked_choice, sequence)
VALUES (1, 2, true, 5, 1);
INSERT INTO result(quiz_record_id, question_id, is_correct, checked_choice, sequence)
VALUES (1, 3, false, 4, 2);
-- result 끝


