package com.cartisan.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author colin
 */
//@Slf4j
public class RandomUtil {
//    private static final Logger logger = LoggerFactory.getLogger(RandomUtil.class);
    private static final ThreadLocal<Random> THREAD_LOCAL_RANDOM;

    static {
        THREAD_LOCAL_RANDOM = ThreadLocal.withInitial(RandomUtil::createSecureRandom);
    }

    public static IntStream ints(long streamSize) {
        return THREAD_LOCAL_RANDOM.get().ints(streamSize);
    }

    public static void nextBytes(byte[] bytes) {
        THREAD_LOCAL_RANDOM.get().nextBytes(bytes);
    }

    public static int nextInt() {
        return THREAD_LOCAL_RANDOM.get().nextInt();
    }

    private static Random createSecureRandom() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
//            logger.("Couldn't create strong secure random generator; reason: {}.", e.getMessage());
            return new SecureRandom();
        }
    }
}
