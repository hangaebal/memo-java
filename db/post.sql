CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `title` varchar(100) NOT NULL,
  `year` varchar(50) DEFAULT NULL,
  `contents` text,
  `del_yn` enum('N','Y') DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8