/*
 * Copyright 2011 Shinobu Aoki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.apache.solr.highlight;

import org.apache.lucene.search.highlight.Encoder;
import org.apache.solr.common.params.SolrParams;

public class HtmlEncoder2 extends HtmlEncoder {

  public Encoder getEncoder(String fieldName, SolrParams params) {
    return new Encoder() {
      public String encodeText(String originalText) {
        if (originalText == null || originalText.length() == 0) {
          return "";
        }
        StringBuilder result = new StringBuilder(originalText.length());
        for (int index=0; index<originalText.length(); index++) {
          char ch = originalText.charAt(index);
          switch (ch) {
          case '"':
            result.append("&quot;");
            break;
          case '&':
            result.append("&amp;");
            break;
          case '<':
            result.append("&lt;");
            break;
          case '>':
            result.append("&gt;");
            break;
          default:
            result.append(ch);
          }
        }
        return result.toString();
      }
    };
  }
}
