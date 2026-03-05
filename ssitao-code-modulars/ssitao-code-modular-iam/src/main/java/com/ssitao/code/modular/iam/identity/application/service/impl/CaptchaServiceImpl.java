package com.ssitao.code.modular.iam.identity.application.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.ssitao.code.modular.iam.identity.api.dto.CaptchaResultDTO;
import com.ssitao.code.modular.iam.identity.application.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * 验证码缓存Key前缀
     */
    private static final String CAPTCHA_KEY_PREFIX = "captcha:";

    /**
     * 验证码过期时间（秒）：5分钟
     */
    private static final int CAPTCHA_EXPIRE_SECONDS = 300;

    /**
     * 验证码长度
     */
    private static final int CAPTCHA_LENGTH = 4;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public CaptchaResultDTO generateCaptcha() {
        // 生成验证码ID
        String captchaId = IdUtil.fastSimpleUUID();

        // 创建验证码图片（数字+字母）
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(120, 40, CAPTCHA_LENGTH, 30);
        captcha.setBackground(Color.WHITE);

        // 获取验证码答案
        String captchaCode = captcha.getCode();

        // 存储到Redis
        String cacheKey = CAPTCHA_KEY_PREFIX + captchaId;
        redisTemplate.opsForValue().set(cacheKey, captchaCode.toLowerCase(), CAPTCHA_EXPIRE_SECONDS, TimeUnit.SECONDS);

        log.debug("生成验证码，captchaId={}", captchaId);

        return CaptchaResultDTO.builder()
                .captchaId(captchaId)
                .captchaImage(captcha.getImageBase64())
                .captchaType(3) // 数字+字母
                .captchaLength(CAPTCHA_LENGTH)
                .expireSeconds(CAPTCHA_EXPIRE_SECONDS)
                .build();
    }

    @Override
    public boolean verifyCaptcha(String captchaId, String captchaCode) {
        if (captchaId == null || captchaCode == null) {
            return false;
        }

        String cacheKey = CAPTCHA_KEY_PREFIX + captchaId;

        try {
            // 获取存储的验证码
            Object cachedCode = redisTemplate.opsForValue().get(cacheKey);
            if (cachedCode == null) {
                log.warn("验证码已过期，captchaId={}", captchaId);
                return false;
            }

            // 验证（不区分大小写）
            boolean matches = cachedCode.toString().equalsIgnoreCase(captchaCode.trim());

            if (matches) {
                // 验证成功后删除验证码（防止重复使用）
                redisTemplate.delete(cacheKey);
                log.debug("验证码验证成功，captchaId={}", captchaId);
            } else {
                log.warn("验证码错误，captchaId={}, input={}", captchaId, captchaCode);
            }

            return matches;
        } catch (Exception e) {
            log.error("验证码验证异常，captchaId={}", captchaId, e);
            return false;
        }
    }

    @Override
    public CaptchaResultDTO refreshCaptcha(String captchaId) {
        // 删除旧验证码
        if (captchaId != null) {
            String oldCacheKey = CAPTCHA_KEY_PREFIX + captchaId;
            redisTemplate.delete(oldCacheKey);
        }

        // 生成新验证码
        return generateCaptcha();
    }
}
