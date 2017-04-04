<?php
include_once 'dbconn.php';
$sr_id = $_POST['sr_id'];
$sql = "SELECT id,name,price,token FROM tbl_sku";
$result = $conn->query($sql);
$return_array=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $return_array[]=array("column_id"=>$row["id"],"sku_id"=>$row["id"],"sku_name"=>$row["name"],"price"=>$row["price"],"token"=>$row["token"]);
    }
}
$returnOutletVerify['tbl_sku']['data'] = $return_array;
$conn->close();
echo json_encode($returnOutletVerify);