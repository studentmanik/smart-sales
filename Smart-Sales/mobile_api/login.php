<?php
include_once 'dbconn.php';
$user = $_POST['user_name'];
$password = $_POST['password'];
$sql = "SELECT id,full_name FROM tbl_user where user_name='$user' AND password='$password'";
$result = $conn->query($sql);
$return_array=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $return_array[0]=array("user_id"=>$row["id"],"full_name"=>$row["full_name"]);
    }
}
$conn->close();
echo json_encode($return_array);