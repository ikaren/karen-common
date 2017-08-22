package org.karen.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 文件服务器域名替换工具
 * Created by Karen.Wang on 217/8/22.
 */
public class UrlDomainUtil {
    private static Pattern pattern = Pattern.compile("^((http://)|(https://)).*$");

    public static String joinDomain(String domain, String url) {
        if (StringUtils.isBlank(url))
            return url;
        if (!needDomain(url))
            return url;
        url = domain + url;
        return url;
    }

    /**
     * 给URL拼接上域名
     *
     * @param urls
     * @return
     */
    public static List<String> joinDomainBatch(String domain, List<String> urls) {

        if (CollectionUtils.isEmpty(urls))
            return null;
        for (int i = 0; i < urls.size(); i++) {
            urls.set(i, joinDomain(domain, urls.get(i)));
        }
        return urls;
    }

    /**
     * 删除URL中的域名
     *
     * @param domain
     * @param url
     * @return
     */
    public static String removeDomain(String domain, String url) {
        if (StringUtils.isNotBlank(url)) {
            url = url.replaceAll(domain, "");
        }
        return url;
    }

    /**
     * 批量删除URL的域名前缀
     *
     * @param urls
     * @return
     */
    public static List<String> removeDomainBatch(String domain, List<String> urls) {

        List<String> imgs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(urls)) {
            for (String url : urls) {
                String removeDomain = removeDomain(domain, url);
                if (StringUtils.isNotBlank(removeDomain)) {
                    imgs.add(removeDomain);
                }

            }
        } else {
            return urls;
        }
        return imgs;
    }

    /**
     * 判断是否需要拼接域名
     *
     * @param url
     * @return
     */
    private static boolean needDomain(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        return !pattern.matcher(url).matches();
    }

}
