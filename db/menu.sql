CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `del_yn` enum('N','Y') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8