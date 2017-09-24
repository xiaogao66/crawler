package com.xg.impl;

import com.xg.util.CharsetUtil;
import com.xg.util.CrawlerHttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Created by xg on 2017/9/10.
 */
public abstract class AbstractCrawler {
    protected String crawl(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new CrawlerHttpGet(url);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);


            try {
                //result = EntityUtils.toString(httpResponse.getEntity(),"gbk");
                //result = EntityUtils.toString(httpResponse.getEntity(), CrawlerUtil.getParseText(SiteEnum.getEnumByUrl(url)).get("charset"));
                // result = EntityUtils.toString(httpResponse.getEntity());
                String charset = CharsetUtil.getCharset(url);
                result = EntityUtils.toString(httpResponse.getEntity(), charset);


            } finally {
                httpResponse.close();
            }
            return result;

        } catch (ConnectTimeoutException e) {

            //throw new  RuntimeException("time out");
            throw new ConnectTimeoutException("time out……");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpClient != null)
                httpClient.close();
        }
    }
}
