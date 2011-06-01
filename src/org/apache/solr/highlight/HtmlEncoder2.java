package org.apache.solr.highlight;

import org.apache.lucene.search.highlight.Encoder;
import org.apache.lucene.search.highlight.SimpleHTMLEncoder;
import org.apache.solr.common.params.SolrParams;

public class HtmlEncoder2 extends HtmlEncoder {

  public Encoder getEncoder(String fieldName, SolrParams params) {
    return new SimpleHTMLEncoder() {
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
