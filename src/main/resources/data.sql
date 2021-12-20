insert into user(id,email, password , first_Name, last_Name, university, registration_Date)
values
('1','cosminruja@yahoo.com','Cosmin22','Cosmin','Ruja','Universitate din Bucuresti','2021-05-23T18:25:43.511Z'),
('2','andreiionel@yahoo.com','1234567','Andrei','Popescu','Universitatea din Bucuresti','2021-10-23T18:25:43.511Z'),
('3','biziday22@yahoo.com','bitcoini','Laurentiu','Suto','Universitatea din Bucuresti','2020-08-23T18:25:43.511Z'),
('4','linkedinemployee33@yahoo.com','memeslol','Percutiu','Avramescu','Politehnica Bucuresti','2021-06-20T18:25:43.511Z'),
('5','pandadaniel12@yahoo.com','frontal2223','Daniel','Ionasiu','Politehnica Bucuresti','2021-08-23T18:25:43.511Z'),
('6','hatzjonule69@yahoo.com','frontal2223','Dorian','Popa','Politehnica Bucuresti','2021-11-23T18:25:43.511Z');

insert into subject (id, name, description)
values
('1', 'Calculus 1','Introduction involving curves and differential equations'),
('2', 'Descriptive Geometry','Representation of 3-D Models'),
('3', 'Mechanics','Physical phenomena involving motion of objects'),
('4', 'Object Oriented Programming', 'Introductory course, involving classes and a small project'),
('5', 'Computer Vision', 'Field of AI which involves manipulation of images and videos');

insert into user_subject(id, registration_Date, subject_Id,user_Id)
values
('1', '2021-08-23T18:25:43.511Z', '1','1'),
('2', '2021-08-25T18:25:43.511Z', '4','1'),
('3', '2021-09-24T18:25:43.511Z', '5','1'),
('4', '2021-08-25T18:25:43.511Z', '4','2'),
('5', '2021-10-25T18:25:43.511Z', '5','2'),
('6', '2021-08-25T18:25:43.511Z', '1','3'),
('7', '2021-11-07T18:25:43.511Z', '1','4'),
('8', '2021-11-07T18:28:43.511Z', '2','4'),
('9', '2021-11-07T19:25:43.511Z', '3','4'),
('10', '2021-12-01T20:25:43.511Z', '2','5'),
('11', '2021-12-07T18:25:43.511Z', '3','5'),
('12', '2021-12-07T18:25:43.511Z', '1','6'),
('13', '2021-12-08T19:25:43.511Z', '3','6'),
('14', '2021-12-09T19:25:43.511Z', '4','6'),
('15', '2021-12-10T19:25:43.511Z', '5','6');

insert into post(id, is_Sticky, post_Date, text, title, subject_Id, user_Id)
values
('1', 'true','2021-08-25T18:25:43.511Z','Importance of functions on the real line, overview - reviewing a mapping of a set f from X to Y', 'Calculus 1 - Introduction','1','1'),
('2', 'false','2021-08-30T18:25:43.511Z','Overview of first and second grade polynomials, with a short introduction to trigonometric and exponential functions','Calculus 1 - Polynomials, functions','1','2'),
('3', 'true', '2021-10-30T18:25:43.511Z', 'Importance of differential calculus in 3-D models, introduction to descriptive geometry', 'Descriptive Geometry - Firs steps','2','4'),
('4', 'false','2021-11-02T18:25:43.511Z', 'Golden rule of mechanics - Whatever you lose in power you gain in displacement, and the use of it','Golden rule of Mechanics','3','5'),
('5', 'false', '2021-11-08T18:25:43.511Z', 'Right hand rule - applications in day-to-day life and the usefulness of basic physics equations' , 'Right hand rule', '3','5'),
('6', 'true', '2021-11-09T18:25:43.511Z', 'OOP - Objects and Classes, featuring Andrei Paun, the god of C++', 'Introduction to OOP', '4','6'),
('7', 'false', '2021-11-10T18:25:43.511Z' , 'Installing a python interpreter and learning basic funtions of the OpenCV library','Short tutorial for Python', '5','2');

insert into comments(id, comments_Date, text, post_Id, user_Id)
values
('1', '2021-08-25T19:21:43.511Z', 'Great lecture.', '1','3'),
('2', '2021-08-25T21:21:43.511Z', 'Thanks. Hoped you would like it!', '1','1'),
('3', '2021-08-30T19:25:43.511Z', 'Exponential functions are very interesting.', '2','1'),
('4', '2021-11-01T12:25:43.511Z', 'Love the practical application of this subject.', '3','5'),
('5', '2021-11-02T12:25:43.511Z', 'Yeah, wish we could get a second semester of this stuff.', '3','4'),
('6', '2021-11-03T09:25:43.511Z', 'A lot of people do not understand the golden role, which is sad.', '4', '6'),
('7', '2021-11-09T11:21:43.511Z', 'In our country we call this the drill rule, because it follows the rotation of a drill, fun stuff.', '5','4'),
('8', '2021-11-09T21:25:43.511Z', 'Surely wont mind more lectures from this proffesor!', '6','2'),
('9', '2021-11-09T22:25:43.511Z', 'I heard he only gives out high grades.', '6', '1'),
('10', '2021-11-10T18:25:43.511Z', 'I love python!', '7', '6');


/*
HTTPS:Localhost:8080/h2-console-students/


SELECT * FROM COMMENTS ;
SELECT * FROM POST;
SELECT * FROM USER;
SELECT * FROM USER_SUBJECT;
SELECT * FROM SUBJECT;


 */