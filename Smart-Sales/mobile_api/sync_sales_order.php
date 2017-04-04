<?php
include_once 'dbconn.php';
$local_so_id = $_POST['local_so_id'];
$order_time = $_POST['order_time'];
$order_time_stamp = $_POST['order_time_stamp'];
$outlet_id = $_POST['outlet_id'];
$sr_id = $_POST['sr_id'];
$order_amount = $_POST['order_amount'];
$distance = $_POST['distance'];
$dataSalesOrderLines = json_decode($_POST['SalesOrderLine'], true);
$result = Array();
$sql_query = "INSERT INTO tbl_sales_order(local_so_id,order_time,order_time_stamp,outlet_id,sr_id,order_amount,distance) VALUES('$local_so_id','$order_time','$order_time_stamp','$outlet_id','$sr_id','$order_amount','$distance')";
if ($conn->query($sql_query) === TRUE) {
    $so_id = $conn->insert_id;
    for ($i = 0; $i < count($dataSalesOrderLines); $i++) {
        $sku_id=$dataSalesOrderLines[$i]["sku_id"];
        $quantity=$dataSalesOrderLines[$i]["quantity"];
        $unit_price=$dataSalesOrderLines[$i]["unit_price"];
        $total_price=$dataSalesOrderLines[$i]["total_price"];
        $sql = "INSERT INTO tbl_sales_order_line(so_id,sku_id,quantity,unit_price,total_price) VALUES('$so_id','$sku_id','$quantity','$unit_price','$total_price')";
        $conn->query($sql);
    }

    $result = array("status" => $local_so_id);

}


$conn->close();
echo json_encode($result);