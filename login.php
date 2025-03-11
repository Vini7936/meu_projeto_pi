<?php
$host = 'wwww.thyagoquintas.com.br:3306';
$db = 'engenharia_24';
$user = 'engenharia_24';
$pass = 'botocorderosa';
$charset = 'utf8mb4';

$dsn = "mysql:host=$host;dbname=$db;charset=$charset";

$options = [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES => false,
];

try{
    $pdo = new PDO($dsn, $user, $pass, $optins);
    $usuario = $_GET['usuario'];
    $senha = $_GET['senha'];

    $sql = "SELECT USUARIO_ID, USUARIO_NOME, USUARIO_EMAIL, USUARIO_SENHA FROM USUARIO WHERE USUARIO_EMAIL= :usuario AND USUARIO_SENHA = :senha";

    $stmt = $pdo->prepare($sql);
    $stmt->execute(['usuario'=> $usuario, 'senha'=> $senha]);

    $usuarios = $stmt->fetchAll();

    header('Content-Type:application/json');
    echo json_encode($usuario);

}catch(\PDOException $e){
    echo "Erro de conexão: ". $e->getMessage();

    exit;
}
?>