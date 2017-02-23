CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `path` varchar(100) NOT NULL,
  `del_yn` enum('N','Y') DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8