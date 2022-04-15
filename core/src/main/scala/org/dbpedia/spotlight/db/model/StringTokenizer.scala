package org.dbpedia.spotlight.db.model

import opennlp.tools.util.Span
import org.dbpedia.spotlight.model.Text
import com.worksap.nlp.sudachi

trait StringTokenizer {

  def tokenize(text: Text): Seq[String]

  def tokenize(text: String): Seq[String]
  def tokenizePos(text: String): Array[Span]

  def setThreadSafe(isThreadSafe: Boolean)
}
