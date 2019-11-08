package com.example.toolstest;

import net.dgg.utils.database.DggSqlHandleUtil;
import net.dgg.utils.desensitization.DggDesensitizationUtil;
import org.junit.Test;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/23 0023
 * @Description:
 */
public class ToolsTest {

    @Test
    public void desensitizationTest(){
        String idCard = "210768199210122201";
        String trueResult = DggDesensitizationUtil.desensitizationContent(idCard, true);
        System.out.println(trueResult);

        String result = DggDesensitizationUtil.desensitizationContent(idCard);
        System.out.println(result);


        User user1 = new User();
        user1.setName("lisi");
        user1.setPhoneNumber("13212345678");
        DggDesensitizationUtil.desensitizationData(user1, "phoneNumber");
        System.out.println(user1);

        User user2 = new User();
        user2.setName("zhangsan");
        user2.setPhoneNumber("13212345678");
        DggDesensitizationUtil.desensitizationData(user2, "phoneNumber1");
        System.out.println(user2);


    }


    @Test
    public void sqlParamTransferredMeaningTest(){
        String sqlParams = "z'h\\an_gs%an";
        System.out.println(DggSqlHandleUtil.likeSqlHandle(sqlParams));
    }
}
