package com.gjc.cloud.controller;

import com.gjc.cloud.entities.Pay;
import com.gjc.cloud.entities.PayDTO;
import com.gjc.cloud.resp.ResultData;
import com.gjc.cloud.resp.ReturnCodeEnum;
import com.gjc.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;


    @PostMapping(value ="/pay/add" )
    @Operation(summary = "新增",description = "新增支付流水，json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success(i);
    }
    @PutMapping(value = "/pay/update")
    //@Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值："+i);
    }


    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if(id == -4) throw new RuntimeException("id 不能为负数");
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value ="/pay/error")
    public ResultData<Integer> payError(){
        Integer integer = new Integer(200);
        try {
            System.out.println("come in payError test");
            int age = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(integer);
    }
}
