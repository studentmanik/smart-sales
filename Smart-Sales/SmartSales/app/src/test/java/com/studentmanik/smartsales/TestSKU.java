package com.studentmanik.smartsales;

import com.studentmanik.smartsales.BusinessObject.SKU;

import org.junit.Test;

//import static org.junit.Assert.*;
import static junit.framework.Assert.*;

/**
 * Created by Ritu on 12/10/2016.
 */
public class TestSKU {
    @Test
    public void testId() throws Exception {
        SKU sku = new SKU();
        sku.setId(3);
        assertEquals(3, sku.getId());

    }

    @Test
    public void testName() throws Exception {
        SKU sku = new SKU();
        sku.setName("sku1");
        assertEquals("sku1", sku.getName());

    }

    @Test
    public void testPrice() throws Exception {
        SKU sku = new SKU();
        sku.setPrice(5.6);
        assertEquals(5.6, sku.getPrice());


    }

}
