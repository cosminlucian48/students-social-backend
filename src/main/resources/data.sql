insert into user(id, email, password, first_Name, last_Name, university,faculty, registration_Date,authorities)
values ('100', 'cosminlucian48@yahoo.com', '$2a$10$zc4fpoh9.hDhph3.iUY0AuQUwPXVOCYTWEDR9xyVlzJDKbs/wxKb6',
        'Cosmin', 'Ruja', 'Universitate din Bucuresti','Facultatea de Matematica si Informatica',
        '2021-05-23T18:25:43.511Z','ADMIN'),
       ('99', 'iuliangal@gmail.com', '$2a$10$zc4fpoh9.hDhph3.iUY0AuQUwPXVOCYTWEDR9xyVlzJDKbs/wxKb6', 'Iulian', 'Gal', 'Universitate din Bucuresti', 'Facultatea de Matematica si Informatica',
        '2021-05-23T18:25:43.511Z','ADMIN'),
       ('101', 'cosminruja@yahoo.com', 'Cosmin22', 'Cosmin', 'Ruja', 'Universitate din Bucuresti','Facultatea de Matematica si Informatica',
        '2021-05-23T18:25:43.511Z','USER'),
       ('102', 'andreiionel@yahoo.com', '1234567', 'Andrei', 'Popescu', 'Universitatea din Bucuresti','Facultate de Matematica si Informatica',
        '2021-10-23T18:25:43.511Z','USER'),
       ('103', 'biziday22@yahoo.com', 'bitcoini', 'Laurentiu', 'Suto', 'Universitatea din Iasi','Facultatea de Matematica si Informatica',
        '2020-08-23T18:25:43.511Z','USER'),
       ('104', 'linkedinemployee33@yahoo.com', 'memeslol', 'Percutiu', 'Avramescu', 'Politehnica Bucuresti','Facultatea de Automatizari si Calculatoare',
        '2021-06-20T18:25:43.511Z','USER'),
       ('105', 'pandadaniel12@yahoo.com', 'frontal2223', 'Daniel', 'Ionasiu', 'Politehnica Bucuresti','Facultatea de Electronica, Telecomunicatii şi Tehnologia Informatiei',
        '2021-08-23T18:25:43.511Z','USER'),
       ('106', 'hatzjonule69@yahoo.com', 'frontal2223', 'Dorian', 'Popa', 'Politehnica Cluj', 'Facultatea de Telecomunicatii',
        '2021-11-23T18:25:43.511Z','USER');

insert into subject (id, name, description,user_Id)
values ('101', 'Calculus 1', 'Introduction involving curves and differential equations', '100'),
       ('102', 'Descriptive Geometry', 'Representation of 3-D Models','100'),
       ('103', 'Mechanics', 'Physical phenomena involving motion of objects','100'),
       ('104', 'Object Oriented Programming', 'Introductory course, involving classes and a small project','100'),
       ('105', 'Computer Vision', 'Field of AI which involves manipulation of images and videos','100');

insert into user_subject(id, registration_Date, subject_Id, user_Id)
values ('101', '2021-08-23T18:25:43.511Z', '101', '101'),
       ('102', '2021-08-25T18:25:43.511Z', '104', '101'),
       ('103', '2021-09-24T18:25:43.511Z', '105', '101'),
       ('104', '2021-08-25T18:25:43.511Z', '104', '102'),
       ('105', '2021-10-25T18:25:43.511Z', '105', '102'),
       ('106', '2021-08-25T18:25:43.511Z', '101', '103'),
       ('107', '2021-11-07T18:25:43.511Z', '101', '104'),
       ('108', '2021-11-07T18:28:43.511Z', '102', '104'),
       ('109', '2021-11-07T19:25:43.511Z', '103', '104'),
       ('110', '2021-12-01T20:25:43.511Z', '102', '105'),
       ('111', '2021-12-07T18:25:43.511Z', '103', '105'),
       ('112', '2021-12-07T18:25:43.511Z', '101', '106'),
       ('113', '2021-12-08T19:25:43.511Z', '103', '106'),
       ('114', '2021-12-09T19:25:43.511Z', '104', '106'),
       ('115', '2021-12-10T19:25:43.511Z', '105', '106');

insert into post(id, is_Sticky, post_Date, text, title, subject_Id, user_Id, post_Type)
values ('101', 'true', '2021-08-25T18:25:43.511Z',
        'Importance of functions on the real line, overview - reviewing a mapping of a set f from X to Y',
        'Calculus 1 - Introduction', '101', '101','USER'),
       ('102', 'false', '2021-08-30T18:25:43.511Z',
        'Overview of first and second grade polynomials, with a short introduction to trigonometric and exponential functions',
        'Calculus 1 - Polynomials, functions', '101', '102','USER'),
       ('103', 'true', '2021-10-30T18:25:43.511Z',
        'Importance of differential calculus in 3-D models, introduction to descriptive geometry',
        'Descriptive Geometry - Firs steps', '102', '104','USER'),
       ('104', 'false', '2021-11-02T18:25:43.511Z',
        'Golden rule of mechanics - Whatever you lose in power you gain in displacement, and the use of it',
        'Golden rule of Mechanics', '103', '105','USER'),
       ('105', 'false', '2021-11-08T18:25:43.511Z',
        'Right hand rule - applications in day-to-day life and the usefulness of basic physics equations',
        'Right hand rule', '103', '105','USER'),
       ('106', 'true', '2021-11-09T18:25:43.511Z', 'OOP - Objects and Classes, featuring Andrei Paun, the god of C++',
        'Introduction to OOP', '104', '106','USER'),
       ('107', 'false', '2021-11-10T18:25:43.511Z',
        'Installing a python interpreter and learning basic funtions of the OpenCV library',
        'Short tutorial for Python', '105', '102','USER');

insert into comments(id, comments_Date, text, post_Id, user_Id,comment_type)
values ('101', '2021-08-25T19:21:43.511Z', 'Great lecture.', '101', '103','USER'),
       ('102', '2021-08-25T21:21:43.511Z', 'Thanks. Hoped you would like it!', '101', '101','USER'),
       ('103', '2021-08-30T19:25:43.511Z', 'Exponential functions are very interesting.', '102', '101','USER'),
       ('104', '2021-11-01T12:25:43.511Z', 'Love the practical application of this subject.', '103', '105','USER'),
       ('105', '2021-11-02T12:25:43.511Z', 'Yeah, wish we could get a second semester of this stuff.', '103', '104','USER'),
       ('106', '2021-11-03T09:25:43.511Z', 'A lot of people do not understand the golden role, which is sad.', '104',
        '106','USER'),
       ('107', '2021-11-09T11:21:43.511Z',
        'In our country we call this the drill rule, because it follows the rotation of a drill, fun stuff.', '105',
        '104','USER'),
       ('108', '2021-11-09T21:25:43.511Z', 'Surely wont mind more lectures from this proffesor!', '106', '102','USER'),
       ('109', '2021-11-09T22:25:43.511Z', 'I heard he only gives out high grades.', '106', '101','USER'),
       ('110', '2021-11-10T18:25:43.511Z', 'I love python!', '107', '106','USER');


/*
HTTPS:Localhost:8080/h2-console-students/


SELECT * FROM COMMENTS ;
SELECT * FROM POST;
SELECT * FROM USER;
SELECT * FROM USER_SUBJECT;
SELECT * FROM SUBJECT;


 */