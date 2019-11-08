/**
 * FileName: CommonService
 * Author:   tumq
 * Date:     2019/7/3 13:10
 * Description:
 */
package net.dgg.schoolfrontfeign.feignserver;

import model.vo.SchoolInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@FeignClient(value = "school-server",fallbackFactory = CommonServiceFallBack.class)
public interface CommonService {
    @RequestMapping("/common/getschoolinfo")
    public SchoolInfo getSchoolInfo();
}