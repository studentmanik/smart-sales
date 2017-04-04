<?php
include_once 'dbconn.php';
$sr_id = $_POST['sr_id'];
$sql = "SELECT id,name,address,owner,mobile,sr_id,lat,lon,verified,token FROM tbl_outlet where sr_id='$sr_id'";
$result = $conn->query($sql);
$return_array=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $return_array[]=array(
            "column_id"=>$row["id"],
            "outlet_id"=>$row["id"],
            "name"=>$row["name"],
            "address"=>$row["address"],
            "owner"=>$row["owner"],
            "mobile"=>$row["mobile"],
            "sr_id"=>$row["sr_id"],
            "lat"=>$row["lat"],
            "lon"=>$row["lon"],
            "verified"=>$row["verified"],
            "token"=>$row["token"]);
    }
}
$returnOutletVerify['tbl_outlet']['data'] = $return_array;
$conn->close();
echo json_encode($returnOutletVerify);