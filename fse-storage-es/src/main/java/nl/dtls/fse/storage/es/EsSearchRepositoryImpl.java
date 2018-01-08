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
package nl.dtls.fse.storage.es;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.searchbox.client.JestClient;
import nl.dtls.fse.model.SearchResult;
import nl.dtls.fse.storage.SearchRepository;

@Component
public class EsSearchRepositoryImpl implements SearchRepository {
	private static final Logger logger = LoggerFactory.getLogger(EsSearchRepositoryImpl.class);

	@Autowired
	private JestClient client;

	@Override
	public Collection<SearchResult> search(String query) {
		try {
			client.execute(null);
		} catch (IOException e) {
			logger.warn("Coult not execute ES request", e);
		}

		return null;
	}

	@Override
	public Collection<Object> list() {
		// TODO Auto-generated method stub
		return null;
	}
}
