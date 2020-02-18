<?php

    $email_to = "tusdasa@qq.com";
    $email_title = "构建通知";
    $str = "";
    $shell = "hg log --limit 1";
    exec($shell, $result, $status);
	for($a =0 ; $a < count($result) ;$a++){
		$str = $str."<br />".$result[$a];
	}
	$data = array("to" => $email_to, "title" => $email_title, "text" => $str);
	$data_string = json_encode($data);
	$ch = curl_init('http://127.0.0.1:8085/send2');
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($ch, CURLOPT_POSTFIELDS,$data_string);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER,true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
        'Content-Type: application/json',
        'Content-Length: ' . strlen($data_string))
    );
    $result = curl_exec($ch);
    echo $result
?>