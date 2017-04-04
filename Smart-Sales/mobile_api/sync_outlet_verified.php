<?php
include_once 'dbconn.php';
$id = $_POST['id'];
$outlet_id = $_POST['outlet_id'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];
$sql_query = "UPDATE tbl_outlet SET lat='$lat',lon='$lon',verified=1,token=uuid_short() WHERE id='$outlet_id'";
$result = $conn->query($sql_query);
if ($result>0) {
    $result=array("status"=>$id);
}
$conn->close();
echo json_encode($result);