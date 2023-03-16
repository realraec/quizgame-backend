--
-- PostgreSQL database dump
--


DELETE FROM public.records;
DELETE FROM public.progresses;
DELETE FROM public.answers;
DELETE FROM public.questions;
DELETE FROM public.quizzes_interns;
DELETE FROM public.quizzes;
DELETE FROM public.interns;
DELETE FROM public.admins;


--
-- TOC entry 3003 (class 0 OID 140548)
-- Dependencies: 202
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.admins (pk_person, email, firstname, lastname, password, role, username) VALUES (1, 'emilie.lucas@gmail.com', 'Emilie', 'LUCAS', 'P@ssW0rd1', 'ADMIN', 'admin1');
INSERT INTO public.admins (pk_person, email, firstname, lastname, password, role, username) VALUES (2, 'thomas.saligny@gmail.com', 'Thomas', 'SALIGNY', 'P@ssW0rd2', 'ADMIN', 'admin2');
INSERT INTO public.admins (pk_person, email, firstname, lastname, password, role, username) VALUES (3, 'louis.bernard@gmail.com', 'Louis', 'BERNARD', 'P@ssW0rd3', 'ADMIN', 'admin3');


--
-- TOC entry 3007 (class 0 OID 140561)
-- Dependencies: 206
-- Data for Name: interns; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (1, 'timothée.lompech@gmail.com', 'Thimothée', 'LOMPECH', 'P@ssW0rd1', 'INTERN', 'intern1', 'Amazon');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (2, 'mathias.guillaume@gmail.com', 'Mathias', 'GUILLAUME', 'P@ssW0rd2', 'INTERN', 'intern2', 'Apple');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (3, 'camille.royer@gmail.com', 'Camille', 'ROYER', 'P@ssW0rd3', 'INTERN', 'intern3', 'Alphabet Inc.');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (4, 'leandre.perrin@gmail.com', 'Léandre', 'PERRIN', 'P@ssW0rd4', 'INTERN', 'intern4', 'Microsoft');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (5, 'marie.coolen@gmail.com', 'Marie', 'COOLEN', 'P@ssW0rd5', 'INTERN', 'intern5', 'Samsung Group');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (6, 'theodore.leclerc@gmail.com', 'Théodore', 'LECLERC', 'P@ssW0rd6', 'INTERN', 'intern6', 'Tencent Holdings');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (7, 'celestine.meunier@gmail.com', 'Célestine', 'MEUNIER', 'P@ssW0rd7', 'INTERN', 'intern7', 'Meta Platforms');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (8, 'chloe.carpentier@gmail.com', 'Chloé', 'CARPENTIER', 'P@ssW0rd8', 'INTERN', 'intern8', 'Cisco Systems');
INSERT INTO public.interns (pk_person, email, firstname, lastname, password, role, username, company) VALUES (9, 'diane.caudron@gmail.com', 'Diane', 'CAUDRON', 'P@ssW0rd9', 'INTERN', 'intern9', 'Oracle Corporation');


--
-- TOC entry 3016 (class 0 OID 140586)
-- Dependencies: 215
-- Data for Name: quizzes; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (1, 'This quiz is about the Java programming language', 'Java');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (2, 'This quiz is about the SQL programming language', 'SQL');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (3, 'This quiz is about the HTML markup language', 'HTML');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (4, 'This quiz is about the CSS stylesheet language', 'CSS');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (5, 'This quiz is about the C programming language', 'C');


--
-- TOC entry 3013 (class 0 OID 150548)
-- Dependencies: 214
-- Data for Name: quizzes_interns; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (1, 1);
INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (1, 3);
INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (2, 1);
INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (2, 2);
INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (2, 4);
INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (3, 4);
INSERT INTO public.quizzes_interns (pk_quiz, pk_intern) VALUES (4, 2);


--
-- TOC entry 3014 (class 0 OID 140581)
-- Dependencies: 213
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (1, 15, 'What is the symbol used at the end of a command in Java?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (2, 15, 'What does OOP stand for?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (3, 15, 'What are all the possible values that a boolean [as a primitive variable] can take?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (4, 30, 'What are some IDEs that can be used to write Java code?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (5, 30, 'What are some of the keywords that can be used in SQL queries?', 2);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (6, 30, 'What command is used to create a new table in SQL?', 2);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (7, 30, 'What does the following statement in SQL do? ```DROP TABLE customers;```', 2);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (8, 30, 'What kind of language is HTML?', 3);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (9, 15, 'What is the main HTML tag that is opened at the beginning and closed at the end of every HTML file?', 3);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (10, 15, 'Is CSS a stylesheet language?', 4);


--
-- TOC entry 3005 (class 0 OID 140556)
-- Dependencies: 204
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (1, true, ';', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (2, false, '$', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (3, false, '*', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (4, false, '#', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (5, true, 'Object Oriented Programming', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (6, false, 'Obviously Over-the-top Proposition', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (7, false, 'Ohmygod Ohmygod Please', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (8, false, 'Out Of Perception', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (9, false, 'None of the above', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (10, true, 'true', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (11, true, 'false', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (12, false, 'null', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (13, false, 'undefined', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (14, true, 'Eclipse', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (15, true, 'IntelliJ', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (16, true, 'Visual Studio Code', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (17, false, 'Internet Explorer', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (18, true, 'SELECT', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (19, false, 'DRAFT', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (20, true, 'OUTER JOIN', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (21, false, 'ERASE', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (22, true, 'SORT', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (23, true, 'CREATE TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (24, false, 'SETUP TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (25, false, 'PRODUCE TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (26, false, 'MAKE TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (27, true, 'Delete a table called customers', 7);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (28, false, 'Create a table called customers', 7);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (29, false, 'Check if a table called customers exists', 7);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (30, true, 'A markup language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (31, false, 'A programming language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (32, false, 'A programming language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (33, false, 'A human language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (34, true, '<html>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (35, false, '<body>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (36, false, '<head>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (37, false, '<all>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (38, true, 'Yes it is', 10);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (39, false, 'No it isn''t', 10);


--
-- TOC entry 3011 (class 0 OID 140574)
-- Dependencies: 210
-- Data for Name: progresses; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (6, NULL, 0, 4, 2);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (7, NULL, 0, 4, 3);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (1, '2023-03-15 11:52:05.801588', 2, 1, 1);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (2, NULL, 1, 1, 2);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (3, '2023-03-15 11:52:05.990897', 1, 2, 2);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (4, '2023-03-15 11:52:06.025828', 1, 2, 4);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_intern, fk_quiz) VALUES (5, NULL, 1, 3, 1);


--
-- TOC entry 3018 (class 0 OID 140594)
-- Dependencies: 217
-- Data for Name: records; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (1, true, 1, 1);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (2, false, 1, 2);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (3, false, 1, 3);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (4, true, 1, 4);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (5, true, 2, 5);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (6, false, 2, 6);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (7, false, 3, 5);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (8, false, 3, 6);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (9, true, 3, 7);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (10, true, 4, 10);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (11, false, 5, 1);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (12, true, 5, 2);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (13, false, 5, 3);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (14, false, 6, 5);


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 203
-- Name: admins_pk_person_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.admins_pk_person_seq', 3, true);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 207
-- Name: interns_pk_person_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.interns_pk_person_seq', 9, true);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 216
-- Name: quizzes_pk_quiz_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.quizzes_pk_quiz_seq', 5, true);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 214
-- Name: questions_pk_question_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questions_pk_question_seq', 10, true);


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 205
-- Name: answers_pk_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.answers_pk_answer_seq', 39, true);


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 211
-- Name: progresses_pk_progress_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.progresses_pk_progress_seq', 7, true);


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 218
-- Name: records_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.records_seq', 14, true);


--
-- PostgreSQL database dump complete
--
