package demo.iptest.service;

import com.example.demo.iptest.consultmessagemapper.ConsultMessageMapper;
import com.example.demo.iptest.entity.ConsultIPRegion;
import com.example.demo.iptest.entity.ConsultMessage;
import com.example.demo.iptest.mapper.ConsultIPRegionMapper;
import net.dgg.resource.center.common.utils.PhoneSearchFast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenxin
 * @date 2019/4/8 14:19
 */
@Service
public class IpRegionService {
    @Autowired
    private ConsultIPRegionMapper regionMapper;

    @Autowired
    private ConsultMessageMapper messageMapper;

    @Value("${startDate}")
    private String startDate;

    @Value("${endDate}")
    private String endDate;

    public void getIPRegionList() throws ParseException, InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startD = simpleDateFormat.parse(startDate);
        Date endD = simpleDateFormat.parse(endDate);
        long days = (endD.getTime() - startD.getTime()) / (1000 * 3600 * 24);
        Calendar calendar = Calendar.getInstance();
        Date date = startD;
        String dateStr = startDate;
        CountDownLatch downLatch = new CountDownLatch((int) days);

        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < days; i++) {
            String finalStr = dateStr;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    List<ConsultIPRegion> list = regionMapper.getList(finalStr);
                    int limit = (list.size() + 100 - 1) / 100;
                    List<List<ConsultIPRegion>> lists = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(
                            a -> list.stream().skip(a * 100).limit(100).parallel().collect(Collectors.toList()))
                            .collect(Collectors.toList());

                    lists.stream().forEach(ids ->{
                        List<ConsultMessage> messages = messageMapper.listByPrimaryKey(ids);
                        for (ConsultMessage message : messages) {
                            System.out.println(message);
                            message.setTel_city(getTelCity(message.getTel()));
                            regionMapper.updateList(message);
                            System.out.println(message);
                        }

                    });

                 /* List<ConsultIPRegion> list1 = list.subList(0,5);
                    List<ConsultMessage> messages = messageMapper.listByPrimaryKey(list.get(0).getConsult_message_id());
                    for (ConsultMessage message : messages) {
                        message.setTel_city(getTelCity(message.getTel()));
                    }
                    regionMapper.updateList(messages);*/
                    downLatch.countDown();

                }
            });
            calendar.setTime(date);
            calendar.add(Calendar.DATE,1);
            date = calendar.getTime();
            dateStr = simpleDateFormat.format(date);
        }
        downLatch.await();
    }

    public String getTelCity(String tel) {
        if(StringUtils.isEmpty(tel)){
            return null;
        }
        PhoneSearchFast finder = PhoneSearchFast.getInstance();
        String result = finder.get(tel);
      /*  if(StringUtils.isEmpty(result)){
            return null;
        }*/
        String[] valueResult = result.split("\\|");
        String city =null;
        String provinces = null;
        if(valueResult.length ==1){
            provinces = valueResult[0];
        }
        if(valueResult.length >=2){
            city = valueResult[1];
        }
        System.out.println(tel);
        return StringUtils.isEmpty(city) ? provinces : city;
    }
}
