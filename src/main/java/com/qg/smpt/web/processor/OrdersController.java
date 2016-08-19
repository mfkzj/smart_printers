package com.qg.smpt.web.processor;

import com.qg.smpt.receive.ReceOrderServlet;
import com.qg.smpt.util.JsonUtil;
import com.qg.smpt.util.Level;
import com.qg.smpt.util.OrderBuilder;
import com.qg.smpt.web.model.Constant;
import com.qg.smpt.web.model.Order;
import com.qg.smpt.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tisong on 7/31/16.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @RequestMapping(value="/{userId}/{orderNumbers}/{expe}", method= RequestMethod.GET)
    @ResponseBody
    public void buildOrders(@PathVariable int userId, @PathVariable int orderNumbers, @PathVariable int expe) {

        Integer expeNumbers = 0;

        try {
            ReceOrderServlet receOrderServlet = new ReceOrderServlet();
            for (int i = 0; i < orderNumbers; i++) {
                Order order = OrderBuilder.produceOrder(false,false,4);
                receOrderServlet.doGet(userId, order);
            }

            for (int i = 0; i < expe; i++) {
                Order order = OrderBuilder.produceOrder(true,false,4);
                receOrderServlet.doGet(userId, order);
            }
        } catch (Exception e) {

        }
    }

    @RequestMapping(value="/{userId}/{correctNum}/{exceNum}/{index}", method= RequestMethod.GET)
    @ResponseBody
    public void buildOrdersExce(@PathVariable int userId,@PathVariable int correctNum, @PathVariable int exceNum, @PathVariable int index) {

        Integer expeNumbers = 0;

        try {
            ReceOrderServlet receOrderServlet = new ReceOrderServlet();
            for (int i = 0; i < correctNum; i++) {
                Order order = OrderBuilder.produceOrder(false,false,4);
                receOrderServlet.doGet(userId, order);
            }

            for (int i = 0; i < exceNum; i++) {
                Order order = OrderBuilder.produceOrder(false,true,index);
                receOrderServlet.doGet(userId, order);
            }
        } catch (Exception e) {

        }
    }
}
