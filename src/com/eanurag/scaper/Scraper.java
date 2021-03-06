package com.eanurag.scaper;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.eanurag.dao.DataBaseManager;
import com.eanurag.objects.ScrapedURL;
import com.eanurag.objects.URL;

public class Scraper {

	private final static Logger logger = Logger.getLogger(Scraper.class);

	public ScrapedURL scrape(URL url) {

		logger.info("Begin scrapping:" + url.getURL());

		Document doc = null;
		ScrapedURL scrapedLinks = url.getLinks();

		try {
			doc = Jsoup.connect(url.getURL()).get();

			Elements links = doc.getElementsByTag("a");

			for (Element link : links) {
//				logger.info(link.attr("abs:href"));
				scrapedLinks.getScrapedLinks().add(new URL(link.attr("abs:href")));
			}
			url.setLinks(scrapedLinks);
			DataBaseManager.getInstance().writeData(url);

		} catch (Exception e) {
			logger.error("Error in Scrapper:"+url.getURL(),e);
		}
		
		return scrapedLinks;
	}

}
