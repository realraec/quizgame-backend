PGDMP     0                    {            quizgame %   12.13 (Ubuntu 12.13-0ubuntu0.20.04.1)    15.0 H    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    90454    quizgame    DATABASE     p   CREATE DATABASE quizgame WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C.UTF-8';
    DROP DATABASE quizgame;
                dev    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1259    123374    admins    TABLE     >  CREATE TABLE public.admins (
    pk_person bigint NOT NULL,
    email character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role smallint NOT NULL,
    username character varying(255) NOT NULL
);
    DROP TABLE public.admins;
       public         heap    dev    false    6            �            1259    123372    admins_pk_person_seq    SEQUENCE     }   CREATE SEQUENCE public.admins_pk_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.admins_pk_person_seq;
       public          dev    false    207    6            �           0    0    admins_pk_person_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.admins_pk_person_seq OWNED BY public.admins.pk_person;
          public          dev    false    206            �            1259    123385    answers    TABLE     �   CREATE TABLE public.answers (
    pk_answer bigint NOT NULL,
    is_correct boolean NOT NULL,
    wording character varying(255),
    fk_question bigint NOT NULL
);
    DROP TABLE public.answers;
       public         heap    dev    false    6            �            1259    123383    answers_pk_answer_seq    SEQUENCE     ~   CREATE SEQUENCE public.answers_pk_answer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.answers_pk_answer_seq;
       public          dev    false    6    209            �           0    0    answers_pk_answer_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.answers_pk_answer_seq OWNED BY public.answers.pk_answer;
          public          dev    false    208            �            1259    123393    interns    TABLE     c  CREATE TABLE public.interns (
    pk_person bigint NOT NULL,
    email character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role smallint NOT NULL,
    username character varying(255) NOT NULL,
    company character varying(255)
);
    DROP TABLE public.interns;
       public         heap    dev    false    6            �            1259    123391    interns_pk_person_seq    SEQUENCE     ~   CREATE SEQUENCE public.interns_pk_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.interns_pk_person_seq;
       public          dev    false    6    211            �           0    0    interns_pk_person_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.interns_pk_person_seq OWNED BY public.interns.pk_person;
          public          dev    false    210            �            1259    107809    journeys    TABLE     �   CREATE TABLE public.journeys (
    pk_journey bigint NOT NULL,
    date_and_time_of_completion timestamp(6) without time zone,
    duration_in_seconds integer NOT NULL,
    score integer NOT NULL,
    fk_intern bigint,
    fk_quiz bigint
);
    DROP TABLE public.journeys;
       public         heap    dev    false    6            �            1259    107807    journeys_pk_journey_seq    SEQUENCE     �   CREATE SEQUENCE public.journeys_pk_journey_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.journeys_pk_journey_seq;
       public          dev    false    204    6            �           0    0    journeys_pk_journey_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.journeys_pk_journey_seq OWNED BY public.journeys.pk_journey;
          public          dev    false    203            �            1259    123404 
   progresses    TABLE     �   CREATE TABLE public.progresses (
    pk_progress bigint NOT NULL,
    date_and_time_of_completion timestamp(6) without time zone,
    duration_in_seconds integer NOT NULL,
    score integer NOT NULL,
    fk_intern bigint,
    fk_quiz bigint
);
    DROP TABLE public.progresses;
       public         heap    dev    false    6            �            1259    123402    progresses_pk_progress_seq    SEQUENCE     �   CREATE SEQUENCE public.progresses_pk_progress_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.progresses_pk_progress_seq;
       public          dev    false    6    213            �           0    0    progresses_pk_progress_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.progresses_pk_progress_seq OWNED BY public.progresses.pk_progress;
          public          dev    false    212            �            1259    107775    question_records_seq    SEQUENCE     ~   CREATE SEQUENCE public.question_records_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.question_records_seq;
       public          dev    false    6            �            1259    123412 	   questions    TABLE     �   CREATE TABLE public.questions (
    pk_question bigint NOT NULL,
    max_duration_in_seconds integer NOT NULL,
    wording character varying(255),
    fk_quiz bigint NOT NULL
);
    DROP TABLE public.questions;
       public         heap    dev    false    6            �            1259    123410    questions_pk_question_seq    SEQUENCE     �   CREATE SEQUENCE public.questions_pk_question_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.questions_pk_question_seq;
       public          dev    false    6    215            �           0    0    questions_pk_question_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.questions_pk_question_seq OWNED BY public.questions.pk_question;
          public          dev    false    214            �            1259    123420    quizzes    TABLE     �   CREATE TABLE public.quizzes (
    pk_quiz bigint NOT NULL,
    summary character varying(255),
    title character varying(255)
);
    DROP TABLE public.quizzes;
       public         heap    dev    false    6            �            1259    123418    quizzes_pk_quiz_seq    SEQUENCE     |   CREATE SEQUENCE public.quizzes_pk_quiz_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.quizzes_pk_quiz_seq;
       public          dev    false    6    217            �           0    0    quizzes_pk_quiz_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.quizzes_pk_quiz_seq OWNED BY public.quizzes.pk_quiz;
          public          dev    false    216            �            1259    123429    records    TABLE     �   CREATE TABLE public.records (
    id bigint NOT NULL,
    is_success boolean NOT NULL,
    time_taken_in_seconds integer NOT NULL,
    fk_question bigint,
    fk_progress bigint
);
    DROP TABLE public.records;
       public         heap    dev    false    6            �            1259    123370    records_seq    SEQUENCE     u   CREATE SEQUENCE public.records_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.records_seq;
       public          dev    false    6                       2604    123377    admins pk_person    DEFAULT     t   ALTER TABLE ONLY public.admins ALTER COLUMN pk_person SET DEFAULT nextval('public.admins_pk_person_seq'::regclass);
 ?   ALTER TABLE public.admins ALTER COLUMN pk_person DROP DEFAULT;
       public          dev    false    207    206    207                       2604    123388    answers pk_answer    DEFAULT     v   ALTER TABLE ONLY public.answers ALTER COLUMN pk_answer SET DEFAULT nextval('public.answers_pk_answer_seq'::regclass);
 @   ALTER TABLE public.answers ALTER COLUMN pk_answer DROP DEFAULT;
       public          dev    false    209    208    209                       2604    123396    interns pk_person    DEFAULT     v   ALTER TABLE ONLY public.interns ALTER COLUMN pk_person SET DEFAULT nextval('public.interns_pk_person_seq'::regclass);
 @   ALTER TABLE public.interns ALTER COLUMN pk_person DROP DEFAULT;
       public          dev    false    211    210    211                       2604    107812    journeys pk_journey    DEFAULT     z   ALTER TABLE ONLY public.journeys ALTER COLUMN pk_journey SET DEFAULT nextval('public.journeys_pk_journey_seq'::regclass);
 B   ALTER TABLE public.journeys ALTER COLUMN pk_journey DROP DEFAULT;
       public          dev    false    203    204    204                       2604    123407    progresses pk_progress    DEFAULT     �   ALTER TABLE ONLY public.progresses ALTER COLUMN pk_progress SET DEFAULT nextval('public.progresses_pk_progress_seq'::regclass);
 E   ALTER TABLE public.progresses ALTER COLUMN pk_progress DROP DEFAULT;
       public          dev    false    212    213    213                        2604    123415    questions pk_question    DEFAULT     ~   ALTER TABLE ONLY public.questions ALTER COLUMN pk_question SET DEFAULT nextval('public.questions_pk_question_seq'::regclass);
 D   ALTER TABLE public.questions ALTER COLUMN pk_question DROP DEFAULT;
       public          dev    false    214    215    215            !           2604    123423    quizzes pk_quiz    DEFAULT     r   ALTER TABLE ONLY public.quizzes ALTER COLUMN pk_quiz SET DEFAULT nextval('public.quizzes_pk_quiz_seq'::regclass);
 >   ALTER TABLE public.quizzes ALTER COLUMN pk_quiz DROP DEFAULT;
       public          dev    false    216    217    217            �          0    123374    admins 
   TABLE DATA           a   COPY public.admins (pk_person, email, firstname, lastname, password, role, username) FROM stdin;
    public          dev    false    207   �S       �          0    123385    answers 
   TABLE DATA           N   COPY public.answers (pk_answer, is_correct, wording, fk_question) FROM stdin;
    public          dev    false    209   >T       �          0    123393    interns 
   TABLE DATA           k   COPY public.interns (pk_person, email, firstname, lastname, password, role, username, company) FROM stdin;
    public          dev    false    211   V       �          0    107809    journeys 
   TABLE DATA           {   COPY public.journeys (pk_journey, date_and_time_of_completion, duration_in_seconds, score, fk_intern, fk_quiz) FROM stdin;
    public          dev    false    204   �W       �          0    123404 
   progresses 
   TABLE DATA           ~   COPY public.progresses (pk_progress, date_and_time_of_completion, duration_in_seconds, score, fk_intern, fk_quiz) FROM stdin;
    public          dev    false    213   �W       �          0    123412 	   questions 
   TABLE DATA           [   COPY public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) FROM stdin;
    public          dev    false    215   X       �          0    123420    quizzes 
   TABLE DATA           :   COPY public.quizzes (pk_quiz, summary, title) FROM stdin;
    public          dev    false    217   �Y       �          0    123429    records 
   TABLE DATA           b   COPY public.records (id, is_success, time_taken_in_seconds, fk_question, fk_progress) FROM stdin;
    public          dev    false    218   Z       �           0    0    admins_pk_person_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.admins_pk_person_seq', 3, true);
          public          dev    false    206            �           0    0    answers_pk_answer_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.answers_pk_answer_seq', 39, true);
          public          dev    false    208            �           0    0    interns_pk_person_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.interns_pk_person_seq', 9, true);
          public          dev    false    210            �           0    0    journeys_pk_journey_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.journeys_pk_journey_seq', 1, true);
          public          dev    false    203            �           0    0    progresses_pk_progress_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.progresses_pk_progress_seq', 7, true);
          public          dev    false    212            �           0    0    question_records_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.question_records_seq', 1, false);
          public          dev    false    202            �           0    0    questions_pk_question_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.questions_pk_question_seq', 10, true);
          public          dev    false    214            �           0    0    quizzes_pk_quiz_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.quizzes_pk_quiz_seq', 5, true);
          public          dev    false    216            �           0    0    records_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.records_seq', 51, true);
          public          dev    false    205            %           2606    123382    admins admins_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (pk_person);
 <   ALTER TABLE ONLY public.admins DROP CONSTRAINT admins_pkey;
       public            dev    false    207            +           2606    123390    answers answers_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (pk_answer);
 >   ALTER TABLE ONLY public.answers DROP CONSTRAINT answers_pkey;
       public            dev    false    209            -           2606    123401    interns interns_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.interns
    ADD CONSTRAINT interns_pkey PRIMARY KEY (pk_person);
 >   ALTER TABLE ONLY public.interns DROP CONSTRAINT interns_pkey;
       public            dev    false    211            #           2606    107814    journeys journeys_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.journeys
    ADD CONSTRAINT journeys_pkey PRIMARY KEY (pk_journey);
 @   ALTER TABLE ONLY public.journeys DROP CONSTRAINT journeys_pkey;
       public            dev    false    204            3           2606    123409    progresses progresses_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.progresses
    ADD CONSTRAINT progresses_pkey PRIMARY KEY (pk_progress);
 D   ALTER TABLE ONLY public.progresses DROP CONSTRAINT progresses_pkey;
       public            dev    false    213            5           2606    123417    questions questions_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.questions
    ADD CONSTRAINT questions_pkey PRIMARY KEY (pk_question);
 B   ALTER TABLE ONLY public.questions DROP CONSTRAINT questions_pkey;
       public            dev    false    215            7           2606    123428    quizzes quizzes_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.quizzes
    ADD CONSTRAINT quizzes_pkey PRIMARY KEY (pk_quiz);
 >   ALTER TABLE ONLY public.quizzes DROP CONSTRAINT quizzes_pkey;
       public            dev    false    217            9           2606    123433    records records_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.records
    ADD CONSTRAINT records_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.records DROP CONSTRAINT records_pkey;
       public            dev    false    218            '           2606    123435 #   admins uk_47bvqemyk6vlm0w7crc3opdd4 
   CONSTRAINT     _   ALTER TABLE ONLY public.admins
    ADD CONSTRAINT uk_47bvqemyk6vlm0w7crc3opdd4 UNIQUE (email);
 M   ALTER TABLE ONLY public.admins DROP CONSTRAINT uk_47bvqemyk6vlm0w7crc3opdd4;
       public            dev    false    207            /           2606    123439 $   interns uk_8x2kesn59mmh1iqgdcqrqu5hg 
   CONSTRAINT     `   ALTER TABLE ONLY public.interns
    ADD CONSTRAINT uk_8x2kesn59mmh1iqgdcqrqu5hg UNIQUE (email);
 N   ALTER TABLE ONLY public.interns DROP CONSTRAINT uk_8x2kesn59mmh1iqgdcqrqu5hg;
       public            dev    false    211            1           2606    123441 $   interns uk_iwk0emkb17jjc7hmsmbou7o6p 
   CONSTRAINT     c   ALTER TABLE ONLY public.interns
    ADD CONSTRAINT uk_iwk0emkb17jjc7hmsmbou7o6p UNIQUE (username);
 N   ALTER TABLE ONLY public.interns DROP CONSTRAINT uk_iwk0emkb17jjc7hmsmbou7o6p;
       public            dev    false    211            )           2606    123437 #   admins uk_mi8vkhus4xbdbqcac2jm4spvd 
   CONSTRAINT     b   ALTER TABLE ONLY public.admins
    ADD CONSTRAINT uk_mi8vkhus4xbdbqcac2jm4spvd UNIQUE (username);
 M   ALTER TABLE ONLY public.admins DROP CONSTRAINT uk_mi8vkhus4xbdbqcac2jm4spvd;
       public            dev    false    207            ;           2606    123447 &   progresses fkg49vrkwjfs9w985csfwt731m8    FK CONSTRAINT     �   ALTER TABLE ONLY public.progresses
    ADD CONSTRAINT fkg49vrkwjfs9w985csfwt731m8 FOREIGN KEY (fk_intern) REFERENCES public.interns(pk_person);
 P   ALTER TABLE ONLY public.progresses DROP CONSTRAINT fkg49vrkwjfs9w985csfwt731m8;
       public          dev    false    2861    211    213            =           2606    123457 %   questions fkhr4vwkhjpnfb2t5jm3b92l9x2    FK CONSTRAINT     �   ALTER TABLE ONLY public.questions
    ADD CONSTRAINT fkhr4vwkhjpnfb2t5jm3b92l9x2 FOREIGN KEY (fk_quiz) REFERENCES public.quizzes(pk_quiz);
 O   ALTER TABLE ONLY public.questions DROP CONSTRAINT fkhr4vwkhjpnfb2t5jm3b92l9x2;
       public          dev    false    215    2871    217            <           2606    123452 &   progresses fkiih1xpxf49ipykq8chyjw60jk    FK CONSTRAINT     �   ALTER TABLE ONLY public.progresses
    ADD CONSTRAINT fkiih1xpxf49ipykq8chyjw60jk FOREIGN KEY (fk_quiz) REFERENCES public.quizzes(pk_quiz);
 P   ALTER TABLE ONLY public.progresses DROP CONSTRAINT fkiih1xpxf49ipykq8chyjw60jk;
       public          dev    false    217    2871    213            :           2606    123442 #   answers fkkjjp09o72uiuu70v5627ywav9    FK CONSTRAINT     �   ALTER TABLE ONLY public.answers
    ADD CONSTRAINT fkkjjp09o72uiuu70v5627ywav9 FOREIGN KEY (fk_question) REFERENCES public.questions(pk_question);
 M   ALTER TABLE ONLY public.answers DROP CONSTRAINT fkkjjp09o72uiuu70v5627ywav9;
       public          dev    false    2869    215    209            >           2606    123462 #   records fklq5s813cauf30k3hl87buq9mh    FK CONSTRAINT     �   ALTER TABLE ONLY public.records
    ADD CONSTRAINT fklq5s813cauf30k3hl87buq9mh FOREIGN KEY (fk_question) REFERENCES public.questions(pk_question);
 M   ALTER TABLE ONLY public.records DROP CONSTRAINT fklq5s813cauf30k3hl87buq9mh;
       public          dev    false    218    2869    215            ?           2606    123467 #   records fkotpbwkbi38mnvkd2djy7wr67g    FK CONSTRAINT     �   ALTER TABLE ONLY public.records
    ADD CONSTRAINT fkotpbwkbi38mnvkd2djy7wr67g FOREIGN KEY (fk_progress) REFERENCES public.progresses(pk_progress);
 M   ALTER TABLE ONLY public.records DROP CONSTRAINT fkotpbwkbi38mnvkd2djy7wr67g;
       public          dev    false    218    213    2867            �   �   x�M�M
�0@���0�I.ШE�P�UDp���2v���BK���	�z��R=F��9B�d0����Z=��T`]�$��ϔ�%N6���;z[��\��s�rŒ)yF�o_�-ngͿá�Zݝ6�V�؋3�~8y9r      �   �  x��R�j�0}��B�B���{]
�W��il���B_��x�F��,/ٿόӒ����\$ϙq���"���Ś`}�u���9���TA�^�P�»�W]���|���p�n�Y�'�����z2�n�A;��%��|t��Sj W$���F�+�_�B��Y���Pw"t��?Kx�Qf� %��1t�4����j"f蔕�=zg<�tט�}Cx���Fe�6��v"s�����-!z�<xVxe+oe�csQ��2�B ���N��&��#&BY��V��f^N��Y)ӝ���d�
�r�/��9RE�o��-�[�����6` �DP�R���q�?�%�i��e�Z��n�j�@��b��S�~�Q�8�#�O(}*����SM&�;e_��u:�]��>��<!̺UO�6�Ʈ'@[���A�E�<y�ў�.�������p      �   �  x�]�Kn�0�u�� M��wA�JZ��n�F��c*�%?�m�q.6�ˡ��_����x���SiT����*&dʍ�s�BQ�5���s��/�.`B{�z�R��d	��N0�^!%ΰ�#��˱(�KI�������HV��
�ּ��)����m����
2�w�/��yJ� ���bڣ�BϨb��i�O[Gl���8���&,g��F���+�!���~�hl��)7�k�`�Г-�Mk$r���>��4/h�Gp�-�Qs�>y4����8Jt^hLZ�V6��!��r:�z�Eu%z�Ԓ�c�#{��4a_f�p�2D�y���t���(�!�����y�Z��8������r�T��t�ʲPU��˼��9%���;�&      �      x������ � �      �   7   x�3���4 BCNC.#ǈ��1rL.S����1*3Gp��b���� ���      �   j  x�mR�N�@}n�b������@TH�`P!�AM��Ӳa���[�ޙVK">�̙s��~�Eo�<��?���Pyʀ�R"���!�e��Pq���w�%��3� ��:��>:Ժa�Y�U�	��+a@b�&4���_;�J�^PN!�?!�n�-	�0��ޖ���L�	�1��ک@�i�5��q)�t����a��˗9|U��qt_t,�r|��:B�E0T�m	�2��e7�lN�s����)d��J2�W2�cX�דW^���n>���M;�u�����=�FSTX�XzX=�� ��{�YA�l�hs���?@B�2F|I�T���A{r��'W�D�������w��A�����8�gq0i�!      �   x   x�3���,V(,ͬR ҉I��%
%�
^�e�
E��E����y�
9�y饉驜 .#\��}�kJp����룐�X�]Z�f�	.-���
�%�9����%h�Lq���8g�=... rV\4      �   W   x�=���0Cѳ=�Ӥ�.����E�PnO�W,<Ѐ �IK�bOz��b�8*Ț{1�y�k�Y�T�6����6�O�}��6�/��^     