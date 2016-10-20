package org.petr.parsers;


import org.hibernate.validator.constraints.*;
import org.petr.proxy.ProxyServerList;

import javax.validation.constraints.Size;


/**
 * Created by petr on 19.9.16.
 */

public class AnnonceSettings {
    @NotEmpty
    String url;
    Integer nrToScrape;
    Integer startAtTab;
    ProxyServerList proxyServerList;

    public ProxyServerList getProxyServerList() {
        return proxyServerList;
    }

    public void setProxyServerList(ProxyServerList proxyServerList) {
        this.proxyServerList = proxyServerList;
    }

    public Integer getStartAtTab() {
        return startAtTab;
    }

    public void setStartAtTab(Integer startAtTab) {
        this.startAtTab = startAtTab;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNrToScrape() {
        return nrToScrape;
    }

    public void setNrToScrape(Integer nrToScrape) {
        this.nrToScrape = nrToScrape;
    }
}
