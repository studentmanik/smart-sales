<?php
include_once 'dbconfig.php';

// delete condition
if (isset($_GET['so_id'])) {
    $so_id=$_GET['so_id'];
}

// delete condition
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Smart Sales System</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <script type="text/javascript">
        function edt_id(id)
        {
            if(confirm('Sure to edit ?'))
            {
                window.location.href='edit_outlet.php?edit_id='+id;
            }
        }
        function delete_id(id)
        {
            if(confirm('Sure to Delete ?'))
            {
                window.location.href='outlet.php?delete_id='+id;
            }
        }
    </script>
</head>
<body>
<center>

    <div id="header">
        <div id="content">
            <label>Smart Sales System</label>
        </div>
    </div>

    <div id="body">
        <div id="content">
            <table align="center">
                <tr>
                    <th colspan="5"><a href="index.php">Add Sales Representative.</a></th>
                </tr>
                <tr>
                    <th colspan="5"><a href="sku.php">Add SKU.</a></th>
                </tr><tr>
                    <th colspan="5"><a href="add_outlet.php">Add Outlet.</a></th>
                </tr>
                <th>SKU Name</th>
                <th>Quantity</th>
                <th>Unit Price</th>
                <th>Order Amount</th>
                </tr>
                <?php
$totalAmount=0;
                $sql_query = "SELECT t1.id,t2.name,t1.quantity,t1.unit_price,t1.total_price FROM `tbl_sales_order_line` AS t1 INNER JOIN tbl_sku AS t2 ON t1.sku_id=t2.id where t1.so_id=" . $so_id;
                $result_set=mysql_query($sql_query);
                while($row=mysql_fetch_row($result_set))
                {
                    $totalAmount+=$row[4];
                    ?>
                    <tr>
                        <td><?php echo $row[1]; ?></td>
                        <td><?php echo $row[2]; ?></td>
                        <td><?php echo $row[3]; ?></td>
                        <td><?php echo $row[4]; ?></td>

                    </tr>
                    <?php
                }


                ?>

                <tr>

                    <td></td>
                    <td></td>
                    <td>Total Amount</td>
                    <td><?php echo $totalAmount; ?></td>

                </tr>
            </table>
        </div>
    </div>

</center>
</body>
</html>