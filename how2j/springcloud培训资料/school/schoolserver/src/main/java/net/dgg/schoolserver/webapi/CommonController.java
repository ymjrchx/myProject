/**
 * FileName: CommonController
 * Author:   tumq
 * Date:     2019/7/3 12:56
 * Description:
 */
package net.dgg.schoolserver.webapi;

import io.swagger.annotations.ApiOperation;
import model.vo.SchoolInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Richard
 * @create 2019/7/3
 */
@RestController
@RequestMapping("/common")

public class CommonController {

    @ApiOperation(value="列表", notes="列表")
    @RequestMapping("/getschoolinfo")
    public SchoolInfo getSchoolInfo(){
        SchoolInfo schoolInfo=new SchoolInfo();
        schoolInfo.setSchoolName("清华大学");
        schoolInfo.setSchoolAddress("beijing");
        return schoolInfo;

    }

}