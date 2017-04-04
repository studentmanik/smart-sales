<?php
include_once 'dbconfig.php';

// delete condition
if (isset($_GET['sr_id'])) {
    $sr_id=$_GET['sr_id'];

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
        function view_id(id)
        {
                window.location.href='salesorderline.php?so_id='+id;

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
                <th>SO Id</th>
                <th>Outlet Name</th>
                <th>Order Time</th>
                <th>Order Amount</th>
                <th>Distance</th>
                <th colspan="1">Operations</th>
                </tr>
                <?php
$totalAmount=0;
                $sql_query = "SELECT t1.id, `local_so_id`, t2.name, DATE_FORMAT(order_time_stamp,'%l.%i%p'), `order_amount`,distance FROM tbl_sales_order as t1 inner join tbl_outlet as t2 on t1.outlet_id=t2.id WHERE t1.sr_id=" . $sr_id;
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
                        <td><?php echo $row[5]; ?></td>
                        <td align="center"><a href="javascript:view_id('<?php echo $row[0]; ?>')"><img src="report.png" align="EDIT" /></a></td>

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