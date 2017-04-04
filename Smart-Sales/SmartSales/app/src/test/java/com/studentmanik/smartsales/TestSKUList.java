package com.studentmanik.smartsales;

import com.studentmanik.smartsales.BusinessObject.SKU;
import com.studentmanik.smartsales.BusinessObject.SKUList;

import org.junit.Test;

/**
 * Created by Ritu on 12/10/2016.
 */
public class TestSKUList {
    @Test
    public void testAddSku() throws Exception {
        SKUList skuList=new SKUList();

        SKU sku1=new SKU(1,"sku1",5.5);
        SKU sku2=new SKU(2,"sku2",5.6);

        skuList.addSku(sku1);



    }
}
