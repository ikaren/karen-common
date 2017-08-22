package org.karen.common.util;


import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 17/8/22.
 */
public class ImageUtil {

    /**
     * 常用分隔符
     */
    public static String split1 = ","; // 英文逗号
    public static String split2 = "-"; // 英文横杠
    public static String split3 = "_"; // 英文下划线


    /**
     * 转换图片集合为拼接字符串
     *
     * @param imgList   图片集合
     * @param separator 分隔符
     * @return
     */
    public static String convertImgListToString(List<String> imgList, String separator) {
        String image = "";
        if (CollectionUtils.isNotEmpty(imgList)) {
            image = StringUtils.join(imgList.toArray(), separator);
        }

        return image;
    }

    /**
     * 分割图片字符串为集合
     *
     * @param img       图片字符串
     * @param separator 分隔符
     * @return
     */
    public static List<String> splitImgToList(String img, String separator) {
        List<String> imgList = new ArrayList<>();
        if (StringUtils.isNotBlank(img)) {
            String[] arr = img.split(separator);
            imgList = Lists.newArrayList(arr);
        }
        return imgList;
    }

    /**
     * 转换图片字符串为集合，并拼接上域名。（用分隔符分隔）
     *
     * @param img       图片地址
     * @param separator 分隔符
     * @param domain    域名
     * @return
     */
    public static List<String> imgToList(String img, String separator, String domain) {
        List<String> imgList = ImageUtil.splitImgToList(img, separator);
        if (StringUtils.isNotBlank(domain)) {
            imgList = UrlDomainUtil.joinDomainBatch(domain, imgList); //拼接域名
        }
        return imgList;
    }

    /**
     * 转换图片集合为字符串，并去除域名。（用分隔符分隔）
     *
     * @param imgList   图片地址集合
     * @param separator 分隔符
     * @param domain    域名
     * @return
     */
    public static String listToImg(List<String> imgList, String separator, String domain) {
        if (StringUtils.isNotBlank(domain)) {
            imgList = UrlDomainUtil.removeDomainBatch(domain, imgList); // 去除域名
        }
        String img = ImageUtil.convertImgListToString(imgList, separator);
        return img;
    }
}
