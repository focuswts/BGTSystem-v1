CREATE DATABASE  IF NOT EXISTS `zanete` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `zanete`;
-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: zanete
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idcateg` int(3) NOT NULL AUTO_INCREMENT,
  `descateg` varchar(40) NOT NULL,
  `codgrupo` int(3) NOT NULL,
  PRIMARY KEY (`idcateg`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Produtos de Limpeza',1),(2,'Materiais para Limpeza',1),(3,'EPI-Equipamentos de Proteção Individual',2),(4,'Uniformes Profissional',2),(5,'Extintores e Acessórios para Incêndio',2),(6,'Gêneros Alimentícios',3),(7,'Utensílios para Cozinha',3);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidades`
--

DROP TABLE IF EXISTS `cidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidades` (
  `codigo` int(5) NOT NULL DEFAULT '0',
  `descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidades`
--

LOCK TABLES `cidades` WRITE;
/*!40000 ALTER TABLE `cidades` DISABLE KEYS */;
INSERT INTO `cidades` VALUES (2,'Cambé'),(3,'Cambé'),(4,'Arapongas');
/*!40000 ALTER TABLE `cidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `idCliente` int(10) NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(255) NOT NULL,
  `telCliente` varchar(255) NOT NULL,
  `emailCliente` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `cpfCliente` varchar(14) NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Felipe Oliveira Bagatim','(43) 99972 - 4112','felipebg2008@hotmail.com','Rua Marechal Deodoro Da Fonseca 194','079.715.199-07'),(2,'Fulano','(43)99972-2343','fulano@hotmail.com','Rua Fulano De Tal','079.715.677-86');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combustivel`
--

DROP TABLE IF EXISTS `combustivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combustivel` (
  `codigo` varchar(8) NOT NULL,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combustivel`
--

LOCK TABLES `combustivel` WRITE;
/*!40000 ALTER TABLE `combustivel` DISABLE KEYS */;
INSERT INTO `combustivel` VALUES ('1','Gasolina'),('2','Álcool'),('3','Diesel'),('4','Flex');
/*!40000 ALTER TABLE `combustivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formasPagamento`
--

DROP TABLE IF EXISTS `formasPagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formasPagamento` (
  `idForma` int(11) NOT NULL,
  `nomeForma` varchar(45) NOT NULL,
  PRIMARY KEY (`idForma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formasPagamento`
--

LOCK TABLES `formasPagamento` WRITE;
/*!40000 ALTER TABLE `formasPagamento` DISABLE KEYS */;
INSERT INTO `formasPagamento` VALUES (1,'Á Vista'),(2,'Á Prazo');
/*!40000 ALTER TABLE `formasPagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionarios` (
  `idfuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` varchar(255) NOT NULL,
  `cpfFuncionario` varchar(14) NOT NULL,
  `idUsuario` int(10) NOT NULL,
  PRIMARY KEY (`idfuncionario`),
  KEY `fk_funcionarios_usuarios1_idx` (`idUsuario`),
  CONSTRAINT `fk_funcionarios_usuarios1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
INSERT INTO `funcionarios` VALUES (2,'Felipe Oliveira Bagatim','079.715.199-07',123);
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos` (
  `idgrupo` int(3) NOT NULL AUTO_INCREMENT,
  `desgrupo` varchar(30) NOT NULL,
  PRIMARY KEY (`idgrupo`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'Limpeza e Higiene'),(2,'Segurança'),(3,'Copa e Restaurante');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemsPedido`
--

DROP TABLE IF EXISTS `itemsPedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemsPedido` (
  `id` int(11) NOT NULL,
  `idProduto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valorUnitario` decimal(18,2) NOT NULL,
  `valorTotal` decimal(18,2) NOT NULL,
  KEY `fk_itemsPedido_2_idx` (`idProduto`),
  KEY `fk_itemsPedido_1_idx` (`id`),
  CONSTRAINT `fk_itemsPedido_1` FOREIGN KEY (`id`) REFERENCES `pedidos` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_itemsPedido_2` FOREIGN KEY (`idProduto`) REFERENCES `produtos` (`idprod`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemsPedido`
--

LOCK TABLES `itemsPedido` WRITE;
/*!40000 ALTER TABLE `itemsPedido` DISABLE KEYS */;
INSERT INTO `itemsPedido` VALUES (1,1,4,7.00,28.00),(1,3,2,11.00,22.00),(2,4,2,15.00,30.00),(2,1,1,7.00,7.00),(3,5,2,8.00,16.00),(4,6,23,4.00,92.00),(4,1,11,7.00,77.00),(5,6,13,4.00,52.00),(6,5,1,8.00,8.00);
/*!40000 ALTER TABLE `itemsPedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `itemsVenda`
--

DROP TABLE IF EXISTS `itemsVenda`;
/*!50001 DROP VIEW IF EXISTS `itemsVenda`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `itemsVenda` AS SELECT 
 1 AS `id`,
 1 AS `desprod`,
 1 AS `quantidade`,
 1 AS `valorUnitario`,
 1 AS `valorTotal`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcas` (
  `codigo` int(3) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,'Fiat'),(2,'Chevrolet'),(3,'Ford'),(4,'Wolksvagen'),(5,'Renaut'),(6,'Citroen');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `data` date NOT NULL,
  `id_Funcionario` int(11) NOT NULL,
  `id_Cliente` int(10) NOT NULL,
  `id_FormaPagamento` int(11) NOT NULL,
  `percentualDesconto` int(11) NOT NULL,
  `valorDesconto` decimal(18,2) NOT NULL,
  `valorTotal` decimal(18,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idCliente_idx` (`id_Cliente`),
  KEY `fk_formaPagamento_idx` (`id_FormaPagamento`),
  CONSTRAINT `fk_formaPagamento` FOREIGN KEY (`id_FormaPagamento`) REFERENCES `formasPagamento` (`idForma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idCliente` FOREIGN KEY (`id_Cliente`) REFERENCES `clientes` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'2017-07-14',2,2,1,10,5.00,45.00),(2,'2017-07-14',2,2,1,15,5.55,31.45),(3,'2017-07-14',2,2,1,10,1.60,14.40),(4,'2017-07-21',2,2,1,10,16.90,152.10),(5,'2017-07-25',2,2,1,10,5.20,46.80),(6,'2017-07-25',2,2,1,10,0.80,7.20);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `idprod` int(11) NOT NULL AUTO_INCREMENT,
  `desprod` varchar(50) NOT NULL,
  `quantidade` double NOT NULL,
  `valunit` double NOT NULL,
  `codcateg` int(3) NOT NULL,
  `ultmov` date NOT NULL,
  PRIMARY KEY (`idprod`)
) ENGINE=InnoDB AUTO_INCREMENT=2526 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'Arroz',-0.8000000000000007,7,6,'2008-02-04'),(2,'Açúcar Cristal',20,1.2,6,'2008-03-06'),(3,'Alcatra',0.01,11,6,'2007-12-10'),(4,'Picanha',28,15,6,'2007-11-26'),(5,'Azeite de Oliva',297,8,6,'2008-03-28'),(6,'Luva descartável',-16,4,3,'2008-03-17'),(7,'Assadeira',20,12,7,'2008-03-25'),(8,'Panela',30,19.12,7,'2008-03-02'),(9,'Bota de borracha',16,17,3,'2008-02-03'),(10,'Água sanitária',12,1.5,1,'2007-03-08'),(11,'Álcool em gel',10,2,2,'2008-01-21'),(12,'Extintor pequeno',3,20,5,'2008-03-10'),(13,'Cones de sinalização',40,8,4,'2007-12-17'),(14,'Uniforme padrão com logotipo',23,42.13,4,'2008-01-08'),(15,'Guarda-pó de manga curta',123.3,31,4,'2008-03-02'),(16,'Jaleco de manga comprida',41,12,4,'2008-03-04'),(17,'Vassoura',30,4.5,2,'2008-03-18'),(18,'Espanador',5,8,2,'2008-02-19'),(19,'Balde de plástico',9,5,2,'2008-03-31'),(20,'Escova de fibra de côco',22,0.87,2,'2008-01-16'),(21,'Sabão neutro',20,0.3,1,'2008-01-25'),(22,'Máscara de oxigênio',7,47,5,'2007-12-10'),(23,'Capacete de segurança',12,16,3,'2008-01-30'),(24,'Garfo',120,2,7,'2007-11-08'),(25,'Rodo de espuma',3,4.8,2,'2008-01-27'),(134,'Cones de sinalização23',40,8,4,'2007-12-17'),(666,'Meu Pau',22,22,4,'2017-05-28'),(2525,'cesar gayzao',66,66.6,4,'2008-01-31');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabmodelo`
--

DROP TABLE IF EXISTS `tabmodelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabmodelo` (
  `codigo` int(5) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `quantidade` double NOT NULL,
  `valor` double NOT NULL,
  `ultmov` date NOT NULL,
  `idcateg` int(3) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabmodelo`
--

LOCK TABLES `tabmodelo` WRITE;
/*!40000 ALTER TABLE `tabmodelo` DISABLE KEYS */;
INSERT INTO `tabmodelo` VALUES (5,'João Aparecido Neves',100,200,'2001-10-10',1),(4,'cccccccccccccccccccccc',111,20,'2008-03-15',3),(6,'Margarida Aparecida Felicio',90,900,'2008-01-04',2),(2,'Sicrano de Tal',32,123.61,'2008-01-31',3),(9,'Fulano de Tal',300,3000,'2008-01-15',1),(10,'Beltrano de Tal',21,345.76,'2008-01-31',4),(13,'QQQQQQQQQQQQQQQQQ',12,874.3,'2008-02-22',2),(1,'Fraldinha',350,780.12,'2008-02-10',2);
/*!40000 ALTER TABLE `tabmodelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos` (
  `numtipo` int(3) NOT NULL DEFAULT '0',
  `descricao` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`numtipo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'GOL 1.0'),(2,'FORD KA'),(3,'UNO 1.0'),(4,'D20'),(5,'C10');
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12345 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'focuswts2','1234','felipebg2009@hotmail.com','FelipeOliveiraBagatim','(43) 99222 - 4112'),(12,'rafinha','123456','rafinha@hotmail.com','Rafinha','(43) 99922 - 1223'),(89,'rafael','1234','rafaelnbau@hotmail.com','rafael Magon2','(43) 99764 - 1123'),(121,'cesar','1234','gabriel_menchao@hotmail.com','Cesar Menchão','(43) 99958 - 1184'),(123,'focuswts','1337','focuswts@hotmail.com','Felipe Oliveira Bagatim','(43) 99972 - 4112'),(666,'meupau','1234','oaisjdioasd','afadsfads','(65) 48456 - 6659'),(12344,'tads','tads2017','tads@hotmail.com','tads','(43) 99972 - 4112');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculos`
--

DROP TABLE IF EXISTS `veiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veiculos` (
  `codveiculo` varchar(6) NOT NULL DEFAULT '',
  `tipo` int(3) NOT NULL DEFAULT '0',
  `placa` varchar(8) NOT NULL DEFAULT '',
  `combustivel` varchar(8) NOT NULL DEFAULT '',
  `custokm` double NOT NULL,
  `idmarca` int(3) NOT NULL,
  PRIMARY KEY (`codveiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculos`
--

LOCK TABLES `veiculos` WRITE;
/*!40000 ALTER TABLE `veiculos` DISABLE KEYS */;
INSERT INTO `veiculos` VALUES ('2',5,'ARE-9876','Diesel',0.92,3),('3',4,'AER-0000','2',1.33,3),('4',1,'DDS-1232','4',1.17,3),('5',4,'HGT-0098','3',1.1,2);
/*!40000 ALTER TABLE `veiculos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!50001 DROP VIEW IF EXISTS `vendas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vendas` AS SELECT 
 1 AS `id`,
 1 AS `data`,
 1 AS `cpfFuncionario`,
 1 AS `cpfCliente`,
 1 AS `nomeForma`,
 1 AS `valorTotal`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `itemsVenda`
--

/*!50001 DROP VIEW IF EXISTS `itemsVenda`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `itemsVenda` AS select `itemsPedido`.`id` AS `id`,`produtos`.`desprod` AS `desprod`,`itemsPedido`.`quantidade` AS `quantidade`,`itemsPedido`.`valorUnitario` AS `valorUnitario`,`itemsPedido`.`valorTotal` AS `valorTotal` from (`itemsPedido` join `produtos` on((`itemsPedido`.`idProduto` = `produtos`.`idprod`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vendas`
--

/*!50001 DROP VIEW IF EXISTS `vendas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vendas` AS select `pedidos`.`id` AS `id`,`pedidos`.`data` AS `data`,`funcionarios`.`cpfFuncionario` AS `cpfFuncionario`,`clientes`.`cpfCliente` AS `cpfCliente`,`formasPagamento`.`nomeForma` AS `nomeForma`,`pedidos`.`valorTotal` AS `valorTotal` from (((`pedidos` join `funcionarios` on((`pedidos`.`id_Funcionario` = `funcionarios`.`idfuncionario`))) join `clientes` on((`pedidos`.`id_Cliente` = `clientes`.`idCliente`))) join `formasPagamento` on((`pedidos`.`id_FormaPagamento` = `formasPagamento`.`idForma`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-02 16:40:16
