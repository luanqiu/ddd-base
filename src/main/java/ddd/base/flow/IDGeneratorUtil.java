package ddd.base.flow;

import ddd.base.ApplicationContextHelper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

public class IDGeneratorUtil {
    private static StringRedisTemplate template = ApplicationContextHelper.getRootApplication().getBean(StringRedisTemplate.class);

    /**
     * 通过Redis获取自增序号
     *
     * @param pre       前缀
     * @param length    序号长度
     * @param increment 是否自增
     * @param isDate    是否只获取年月
     * @return 前缀+日期+序列号
     */
    public static String getRedisNo(String pre, int length, boolean increment, boolean isDate) {
        // 日期
        String date;
        if (isDate) {
            date = LocalDate.now().getYear() + "" + LocalDate.now().getMonthValue();
        } else {
            date = LocalDate.now().format(DateTimeFormatter.ISO_DATE).replace("-", "");
        }
        // 编号
        String key = pre + date;
        String no;
        if (increment) {
            // 自增获取
            Long temp = template.opsForValue().increment(key, 1);
            if (temp == 1) {
                // 过期时间
                template.expire(key, isDate ? 30 : 1, TimeUnit.DAYS);
            }
            no = temp.toString();
        } else {
            // 非自增获取
            no = template.opsForValue().get(key);
            if (StringUtils.isEmpty(no)) {
                no = template.opsForValue().increment(key, 1).toString();
                template.expire(key, isDate ? 30 : 1, TimeUnit.DAYS);
            }
        }

        if (no.length() >= length) {
            return key + no;
        } else {
            // 补齐
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < length - no.length(); i++) {
                temp.append("0");
            }
            return key + temp.toString() + no;
        }
    }

    public static String generateIdMonth(String key, int length) {
        // 自增获取
        Long temp = template.opsForValue().increment(key, 1);
        if (1 == temp) {
            // 过期时间
            template.expire(key, 31, TimeUnit.DAYS);
        }

        String no = String.valueOf(temp);
        if (no.length() >= length) {
            return no;
        }
        // 补齐
        String keyTpl = String.format("%%0%dd", length);
        return String.format(keyTpl, temp);
    }


}