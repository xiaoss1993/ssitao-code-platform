package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.modular.iam.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private static final String CAPTCHA_PREFIX = "captcha:";
    private static final String SMS_CODE_PREFIX = "sms_code:";
    private static final int CAPTCHA_EXPIRE_MINUTES = 5;
    private static final int SMS_CODE_EXPIRE_MINUTES = 10;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public String generateCaptcha(String uuid) {
        // 生成4位随机数字验证码
        String code = String.format("%04d", new Random().nextInt(10000));

        // 存储到 Redis
        String key = CAPTCHA_PREFIX + uuid;
        redisTemplate.opsForValue().set(key, code, CAPTCHA_EXPIRE_MINUTES, TimeUnit.MINUTES);

        // TODO: 生成验证码图片并返回 Base64 编码
        // 这里暂时返回验证码文本，实际应该返回图片
        log.info("生成验证码: uuid={}, code={}", uuid, code);
        return code;
    }

    @Override
    public boolean verifyCaptcha(String uuid, String code) {
        String key = CAPTCHA_PREFIX + uuid;
        Object savedCode = redisTemplate.opsForValue().get(key);

        if (savedCode == null) {
            return false;
        }

        boolean verified = savedCode.toString().equals(code);

        // 验证成功后删除验证码
        if (verified) {
            redisTemplate.delete(key);
        }

        return verified;
    }

    @Override
    public String sendSmsCode(String mobile) {
        // 生成6位随机数字验证码
        String code = String.format("%06d", new Random().nextInt(1000000));

        // 存储到 Redis
        String key = SMS_CODE_PREFIX + mobile;
        redisTemplate.opsForValue().set(key, code, SMS_CODE_EXPIRE_MINUTES, TimeUnit.MINUTES);

        // TODO: 调用短信服务发送验证码
        log.info("发送短信验证码: mobile={}, code={}", mobile, code);

        return code;
    }

    @Override
    public boolean verifySmsCode(String mobile, String code) {
        String key = SMS_CODE_PREFIX + mobile;
        Object savedCode = redisTemplate.opsForValue().get(key);

        if (savedCode == null) {
            return false;
        }

        boolean verified = savedCode.toString().equals(code);

        // 验证成功后删除验证码
        if (verified) {
            redisTemplate.delete(key);
        }

        return verified;
    }

}
