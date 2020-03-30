<?php
$shell = "git log -n 1";

exec($shell, $result, $status);
print_r($result);
$str = "commit: " . $result[0];
$str = $str . "<br />" . "提交者: " . substr($result[1], 8);
$str = $str . "<br />" . "提交时间: " . date("Y-m-d h:i:s a", strtotime(substr($result[2], 8)));
$str = $str . "<br />" . "提交信息: " . substr($result[4], 7);

$email_text = array(
    "to" => "tusdasa@qq.com",
    "title" => "构建通知",
    "text" => $str
);
$email_data = json_encode($email_text);

print_r($email_text);


// send email
$email = curl_init('http://127.0.0.1:8085/send2');
curl_setopt($email, CURLOPT_CUSTOMREQUEST, "POST");
curl_setopt($email, CURLOPT_POSTFIELDS, $email_data);
curl_setopt($email, CURLOPT_RETURNTRANSFER, true);
curl_setopt($email, CURLOPT_HTTPHEADER, array(
    'Content-Type: application/json',
    'Content-Length: ' . strlen($email_data)
));
$email_result = curl_exec($email);

// send qq
$qq_text = array(
    "auto_escape" => 'true',
    "group_id" => 696983243,
    "message" => "本次构建完成\n完成时间: " . date("Y-m-d h:i:s a") . "\n提交说明: \n" . substr($result[4], 7)
);
print_r($qq_text);

$qq_data = json_encode($qq_text);
$qq = curl_init("http://127.0.0.1:7501/send_group_msg");
curl_setopt($qq, CURLOPT_CUSTOMREQUEST, "POST");
curl_setopt($qq, CURLOPT_POSTFIELDS, $qq_data);
curl_setopt($qq, CURLOPT_RETURNTRANSFER, true);
curl_setopt($qq, CURLOPT_HTTPHEADER, array(
    'Authorization: Bearer DvPG5r2LYoW3QnOb',
    'Content-Type: application/json',
    'Content-Length: ' . strlen($qq_data)
));
$qq_result = curl_exec($qq);

?>