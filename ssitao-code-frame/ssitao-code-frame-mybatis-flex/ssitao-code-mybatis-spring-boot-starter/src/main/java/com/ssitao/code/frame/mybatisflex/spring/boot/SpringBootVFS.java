/*
 *    Copyright 2015-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.ssitao.code.frame.mybatisflex.spring.boot;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Boot VFS 实现
 * <p>
 * 为 MyBatis 提供 Spring Boot 环境下的虚拟文件系统支持，
 * 用于在可执行 JAR 包中正确加载资源文件。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SpringBootVFS extends VFS {

    /**
     * URL 解码字符集
     */
    private static Charset urlDecodingCharset;

    static {
        setUrlDecodingCharset(Charset.defaultCharset());
    }

    /**
     * 资源解析器
     */
    private final ResourcePatternResolver resourceResolver;

    /**
     * 构造函数
     */
    public SpringBootVFS() {
        this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
    }

    /**
     * 设置 URL 解码字符集
     * <p>
     * 默认为系统默认字符集
     *
     * @param charset URL 解码字符集
     */
    public static void setUrlDecodingCharset(Charset charset) {
        urlDecodingCharset = charset;
    }

    /**
     * 保留子包名称
     *
     * @param baseUrlString 基础 URL
     * @param resource 资源
     * @param rootPath 根路径
     * @return 完整的子包名称
     */
    private static String preserveSubpackageName(final String baseUrlString, final Resource resource,
                                                 final String rootPath) {
        try {
            return rootPath + (rootPath.endsWith("/") ? "" : "/") + Normalizer
                .normalize(URLDecoder.decode(resource.getURL().toString(), urlDecodingCharset.name()), Normalizer.Form.NFC)
                .substring(baseUrlString.length());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 判断 VFS 是否有效
     *
     * @return 始终返回 true
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * 列出指定路径下的资源
     *
     * @param url 资源 URL
     * @param path 资源路径
     * @return 资源列表
     * @throws IOException IO 异常
     */
    @Override
    protected List<String> list(URL url, String path) throws IOException {
        String urlString = URLDecoder.decode(url.toString(), urlDecodingCharset.name());
        String baseUrlString = urlString.endsWith("/") ? urlString : urlString.concat("/");
        Resource[] resources = resourceResolver.getResources(baseUrlString + "**/*.class");
        return Stream.of(resources).map(resource -> preserveSubpackageName(baseUrlString, resource, path))
            .collect(Collectors.toList());
    }

}
