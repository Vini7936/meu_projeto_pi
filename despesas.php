SERVER: CODIGO PHP PARA A API
<?php 
$host = 'www.thyagoquintas.com.br:3306';
$db   = 'engenharia_24';
$user = 'engenharia_24';
$pass = 'botocorderosa';
$charset = 'utf8mb4';

$dsn = "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];

try {
    $pdo = new PDO($dsn, $user, $pass, $options);
    $sql = "SELECT * FROM DESPESA_RECEITA";
    $stmt = $pdo->query($sql);

    $despesas = $stmt->fetchAll();
    header('Content-Type: application/json');
    echo json_encode($despesas);
} catch (\PDOException $e) {
    echo "Erro de conexÃ£o: " . $e->getMessage();
    exit;
}
?>
