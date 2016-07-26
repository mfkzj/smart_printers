package com.qg.smpt.web.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.qg.smpt.web.model.Order;


@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);


    Order selectByPrimaryKey(Integer id);

    List<Order> selectByUser(Integer userId);


    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);



//    int updateByPrimaryKeyWithBLOBs(Order record);


    int updateByPrimaryKey(Order record);
}