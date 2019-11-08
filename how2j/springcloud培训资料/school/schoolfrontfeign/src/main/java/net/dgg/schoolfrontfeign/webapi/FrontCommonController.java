/**
 * FileName: FrontCommonController
 * Author:   tumq
 * Date:     2019/7/3 13:15
 * Description:
 */
package net.dgg.schoolfrontfeign.webapi;

import model.vo.SchoolInfo;
import net.dgg.schoolfrontfeign.feignserver.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@RestController
@RequestMapping("common")
public class FrontCommonController {
    @Autowired
    private CommonService commonService;

    @RequestMapping("/getschoolinfo")
    public Mono<SchoolInfo> getSchoolInfo(){
        return Mono.just(commonService.getSchoolInfo());
    }
}