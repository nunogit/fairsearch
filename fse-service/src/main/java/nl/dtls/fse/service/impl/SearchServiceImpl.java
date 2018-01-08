/**
 * The MIT License
 * Copyright © 2017 DTLS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package nl.dtls.fse.service.impl;

import java.net.URL;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.dtls.fse.crawler.lib.Crawler;
import nl.dtls.fse.crawler.lib.impl.CrawlerImpl;
import nl.dtls.fse.model.SearchResult;
import nl.dtls.fse.service.SearchService;
import nl.dtls.fse.storage.SearchRepository;

@Component
public class SearchServiceImpl implements SearchService {
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Autowired
	private SearchRepository repository;

	private Crawler crawler;

	public SearchServiceImpl() {
		crawler = new CrawlerImpl();
	}

	@Override
	public Collection<SearchResult> search(String query) {
		logger.info("Searching for {}", query);

		return repository.search(query);
	}

	@Override
	public Collection<Object> list() {
		logger.info("Listing known endpoints");

		return repository.list();
	}

	@Override
	public void crawl(URL endpoint) {
		logger.info("Starting to crawl {}", endpoint);

		crawler.crawl(endpoint);
	}
}
