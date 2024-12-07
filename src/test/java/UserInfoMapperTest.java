import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.cartisan.CartisanApplication;
import com.cartisan.entity.UserInfo;
import com.cartisan.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CartisanApplication.class)
@Slf4j
public class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testSelect() {
        log.info("------ selectAll method test ------");
        final List<UserInfo> userInfos = userInfoMapper.selectList(null);

        log.info(Convert.toStr(userInfos.size()));
        log.info(JSONUtil.toJsonStr(userInfos));
    }
}
