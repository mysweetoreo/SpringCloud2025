package com.gjc.cloud.service;

import com.gjc.cloud.entities.Pay;

import java.util.List;

public interface PayService {
     int add(Pay pay);
     int delete(Integer id);
     int update(Pay pay);
     Pay getById(Integer id);
     List<Pay> getAll();
}
