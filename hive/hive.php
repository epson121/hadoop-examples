<?php

// define variables and set to empty values
$query = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $query = $_POST["query"];
}

if ($query != "") {
	$GLOBALS['THRIFT_ROOT'] = '/usr/lib/hive/lib/php';
	require_once $GLOBALS['THRIFT_ROOT'] . '/packages/hive_service/ThriftHive.php';
	require_once $GLOBALS['THRIFT_ROOT'] . '/transport/TSocket.php';
	require_once $GLOBALS['THRIFT_ROOT'] . '/protocol/TBinaryProtocol.php';

	$transport = new TSocket('localhost', 10001);
	$transport->setSendTimeout(600 * 1000);
	$transport->setRecvTimeout(600 * 1000);
	$protocol = new TBinaryProtocol($transport);
	$client = new ThriftHiveClient($protocol);
	$transport->open();
	if (isset($_POST['load_data'])) {
		$client->execute("load data local inpath '/var/www/magento/demo.txt' overwrite into table test");
    }
	$client->execute($query);
	$data = $client->fetchAll();
	if (count($data) > 0) {
		foreach ($data as $line){
			echo $line . "<br>";
		}
	} else {
		echo "Sorry, no results found.";
	}
	$client->close();
	echo "<a href='hive_front.php'>Back</a>";
}
?>
