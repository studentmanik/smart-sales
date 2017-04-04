<?php
include_once 'dbconn.php';
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$owner = $_POST['owner'];
$mobile = $_POST['mobile'];
$sr_id = $_POST['sr_id'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];


$sql_query = "INSERT INTO tbl_outlet(name,address,owner,mobile,sr_id,lat,lon,verified,token) VALUES('$name','$address','$owner','$mobile','$sr_id','$lat','$lon',1,uuid_short())";
$result = $conn->query($sql_query);
if ($result>0) {
  $result=array("status"=>$id);
}
$conn->close();
echo json_encode($result);