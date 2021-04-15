package com;

import com.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ping.dai
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ProducerApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProducerTest {

    @Autowired
    OrderService directOrderServiceImpl;

    @Autowired
    OrderService fanoutOrderServiceImpl;

    @Autowired
    OrderService topicOrderServiceImpl;

    @Test
    public void test(){
        directOrderServiceImpl.takeOrder(1L,1L,1);
        fanoutOrderServiceImpl.takeOrder(2L,2L,2);
        topicOrderServiceImpl.takeOrder(3L,3L,3);
    }
}
