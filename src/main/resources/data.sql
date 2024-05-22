INSERT INTO `student` (`id`, `full_name`, `address`, `email`, `phone`)
VALUES
	(1, 'David Murphy', '87 O\'Connell St D1', 'davidmurphy@hotmail.com', '08612084567'),
	(2, 'Seamus O\'Reilly', '9 Thomas St - Dublin D8', 'seamusoreilly@gmail.com', '08587659812'),
	(3, 'Ciara Fitzpatrick', '23 South Williams Street - Dublin 2', NULL, '09865458968'),
	(4, 'Naill Byrne', '37 Crayfish Alley - Marino D7', 'nbyrne@gmail.com', '08745682372'),
	(5, 'Sara McGregor', '2 St Patrick St - Rialto D12', 'saramcgregor2@microsoft.com', '08612343621'),
	(6, 'Christina Scott', '32a Lamb Alley - Christchurch D8', 'christinas@yahoo.ie', '08598645678'),
	(7, 'Mary McAlpine', '8 South Circular Road - Dublin', 'mmcalpine@gmail.com', '08735686436'),
	(8, 'John Mayer', '65 Hampfrey Court Parliament St - Dublin 1', 'johnmayer@microsoft.com', '08457759756'),
	(9, 'Ciarán O\'Connor', '1 Parnell Street - Dublin 1', 'ciaranoconnor@hotmail.com', '08743443534'),
	(10, 'Susan Boyle', '87 Sheriff Street - Dublin 7', 'susanb@yahoo.com', '08965455487');

INSERT INTO `course` (`id`, `name`, `shift`)
VALUES
	(1, 'Msc Cyber Security', 'MORNING'),
	(2, 'System Information', 'MORNING'),
	(3, 'Nutrition', 'MORNING'),
	(4, 'Medicine', 'EVENING'),
	(5, 'Arts', 'AFTERNOON'),
	(6, 'Music', 'AFTERNOON'),
	(7, 'Politics', 'EVENING'),
	(8, 'Social Communication', 'AFTERNOON'),
	(9, 'Laws', 'AFTERNOON'),
	(10, 'Vet', 'AFTERNOON');

INSERT INTO `subject` (`id`, `name`, `professor`)
VALUES
	(1, 'Math', 'John Boyle'),
	(2, 'Network', 'Cesar DiAnno'),
	(3, 'Data Forensics', 'Anna Hoffmann'),
	(4, 'Malware', 'David Copper'),
	(5, 'Programming Logic', 'Arthur Brown'),
	(6, 'Data Structure', 'Renee Lavinski'),
	(7, 'Computer Architecture', 'Bill Ward'),
	(8, 'Operational Systems', 'Anne McCarty'),
	(9, 'Graphs', 'Anthony LaVey'),
	(10, 'Artificial Intelligence', 'Caterine Lloyd'),
	(11, 'Anatomy', 'Andy Shades'),
	(12, 'Philosophy', 'Eric Young'),
	(13, 'Genetics', 'Bon Johnson'),
	(14, 'Immunology', 'James Mustaine'),
	(15, 'Microbiology', 'Vincent Paul'),
	(16, 'Civil Rights', 'Tony Claire'),
	(17, 'Constituitions', 'Hebert Richards'),
	(18, 'Psychology', 'Juliann Ford'),
	(19, 'Process General Theory', 'Martin Hudges'),
	(20, 'Criminal Law', 'Terry Boghard'),
	(21, 'Human Physiology', 'Tereza Hopes'),
	(22, 'Physiology applied to Nutrition', 'Kate Winsley'),
	(23, 'Dietry I', 'Aoibhinn Murphy'),
	(24, 'Cytology', 'Naomi Woods'),
	(25, 'Plastics', 'Jerry Pardier'),
	(26, 'Drawing Language I', 'Carlos Nakamura'),
	(27, 'Aesthetics and Phylosophy', 'Joann McCoy'),
	(28, 'Photography Language', 'Joe Christopher'),
	(29, 'Expression and Communication through arts', 'Leonardo Fantini'),
	(30, 'Tridimension Language', 'Roy Moore');

INSERT INTO `courses_students` (`course_id`, `student_id`)
VALUES
	(1, 1),
	(2, 2),
	(1, 3),
	(5, 4),
	(5, 5),
	(3, 6),
	(9, 7),
	(3, 8),
	(4, 9),
	(4, 10);

INSERT INTO `courses_subjects` (`course_id`, `subject_id`)
VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(2, 1),
	(2, 2),
	(2, 5),
	(2, 6),
	(2, 7),
	(2, 8),
	(2, 9),
	(2, 10),
	(3, 21),
	(3, 22),
	(3, 23),
	(3, 24),
	(4, 11),
	(4, 12),
	(4, 13),
	(4, 14),
	(4, 15),
	(5, 25),
	(5, 26),
	(5, 27),
	(5, 28),
	(5, 29),
	(5, 30),
	(9, 16),
	(9, 17),
	(9, 18),
	(9, 19),
	(9, 20);

INSERT INTO `grade` (`id`, `student_id`, `subject_id`, `score`)
VALUES
	(1, 1, 1, 'A'),
	(2, 1, 2, 'B+'),
	(3, 1, 3, 'B'),
	(4, 1, 4, 'B+'),
	(5, 1, 5, 'A+'),
	(6, 2, 1, 'B-'),
	(7, 2, 2, 'B-'),
	(8, 2, 5, 'C'),
	(9, 2, 6, 'B-'),
	(10, 2, 7, 'A'),
	(11, 2, 8, 'A-'),
	(12, 2, 9, 'B'),
	(13, 2, 10, 'B'),
	(14, 3, 1, 'B+'),
	(15, 3, 2, 'B-'),
	(16, 3, 3, 'A'),
	(17, 3, 4, 'C+'),
	(18, 3, 5, 'D');

INSERT INTO `users` (`id`, `username`, `password`, `roles`)
VALUES
	(1, 'raphael', '$2a$12$BTVzVyNHvNc1VYpzDAMk5OAVRAiwCPESnbgsQzIIGDWMlLb2ClYwG', 'ADMIN'),
	(2, 'user', '$2a$12$u5rQ9a8WaOhGhAvNDNQvyefXF9a1Uw1VDraqG4KIp6HksnzB/GPJ2', 'USER');