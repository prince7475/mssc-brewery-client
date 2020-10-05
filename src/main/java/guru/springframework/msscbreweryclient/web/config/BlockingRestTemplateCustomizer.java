package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {


    private final Integer poolingHttpClientConnectionManagerMaxTotal;
    private final Integer poolingHttpClientConnectionDefaultMaxPerRoute;
    private final Integer requestConfigConnectionRequestTimeout;
    private final Integer requestConfigSocketTimeout;




    public BlockingRestTemplateCustomizer(@Value("${sfg.poolingHttpClientConnectionManagerMaxTotal}") Integer poolingHttpClientConnectionManagerMaxTotal,
                                          @Value("${sfg.poolingHttpClientConnectionDefaultMaxPerRoute}") Integer poolingHttpClientConnectionDefaultMaxPerRoute,
                                          @Value("${sfg.requestConfigConnectionRequestTimeout}")Integer requestConfigConnectionRequestTimeout,
                                          @Value("${sfg.requestConfigSocketTimeout}") Integer requestConfigSocketTimeout) {
        this.poolingHttpClientConnectionManagerMaxTotal = poolingHttpClientConnectionManagerMaxTotal;
        this.poolingHttpClientConnectionDefaultMaxPerRoute = poolingHttpClientConnectionDefaultMaxPerRoute;
        this.requestConfigConnectionRequestTimeout = requestConfigConnectionRequestTimeout;
        this.requestConfigSocketTimeout = requestConfigSocketTimeout;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }


    private ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(poolingHttpClientConnectionManagerMaxTotal);
        clientConnectionManager.setDefaultMaxPerRoute(poolingHttpClientConnectionDefaultMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(requestConfigConnectionRequestTimeout)
                .setSocketTimeout(requestConfigSocketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(clientConnectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

}
