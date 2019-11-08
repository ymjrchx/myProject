/**
 * FileName: CommonServiceFallBack
 * Author:   tumq
 * Date:     2019/7/3 13:11
 * Description:
 */
package net.dgg.schoolfrontfeign.feignserver;

import feign.hystrix.FallbackFactory;
import model.vo.SchoolInfo;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@Component
public class CommonServiceFallBack implements FallbackFactory<CommonService> {

    @Override
    public CommonService create(Throwable throwable) {
        return new CommonService() {

            @Override
            public SchoolInfo getSchoolInfo() {
                SchoolInfo schoolInfo=new SchoolInfo();
                schoolInfo.setSchoolName("默认大学");
                schoolInfo.setSchoolAddress("chengdu");
                return schoolInfo;
            }
        };
    }
}