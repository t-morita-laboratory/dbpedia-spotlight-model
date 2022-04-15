package org.dbpedia.spotlight.db.tokenize

import org.dbpedia.spotlight.db.model.{Stemmer, StringTokenizer}
import org.dbpedia.spotlight.model.Text

abstract class BaseStringTokenizer(stemmer: Stemmer) extends StringTokenizer {

  protected def tokenizeUnstemmed(text: String): Seq[String]
  def tokenize(text: String): Seq[String] = tokenizeUnstemmed(text).map( stemmer.stem(_) ).filter(t => !t.isEmpty && !t.matches(".*\\s+.*"))
  def tokenize(text: Text): Seq[String] = tokenize(text.text)

}
