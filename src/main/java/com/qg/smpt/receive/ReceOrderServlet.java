package com.qg.smpt.receive;

import com.qg.smpt.printer.OrderService;
import com.qg.smpt.share.ShareMem;
import com.qg.smpt.web.model.Order;
import com.qg.smpt.web.model.Printer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

/**
 * 接收外卖订单数据的Servlet
 */
@WebServlet(name = "orderservlet")
public class ReceOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商家id
        Integer userId = null;

        try {
            userId = Integer.valueOf(request.getParameter("userId"));
        } catch (ClassCastException e) {

        }
        // 根据商家id获取商家的打印机信息
        List<Printer> printers =  ShareMem.userListMap.get(userId);

        if (printers != null && printers.size() > 0) {
            if (ShareMem.priBufferQueueMap != null) {
                Order order = parseOrder(request);
                // TODO 缺少智能分发算法
                Printer printer = printers.get(0);

                synchronized (ShareMem.priBufferQueueMap) {
                    // 获取该打印机拥有订单队列
                    Queue<Order> orders = ShareMem.priBufferQueueMap.get(printer);
                    orders.add(order);
                }
                OrderService orderService = new OrderService();
                
                orderService.sendBatchOrder(printer);
            }
        }

    }

    private Order parseOrder(HttpServletRequest request) {

        return new Order();

    }
}

