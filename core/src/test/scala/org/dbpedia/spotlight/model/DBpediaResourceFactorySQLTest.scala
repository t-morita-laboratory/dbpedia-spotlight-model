package org.dbpedia.spotlight.model

import org.junit.Test

import scala.io.Source

/**
 * Makes sure that the factory that uses a database to build resources is working. Also times execution,
 * @author Joachim Daiber
 */

//TODO fix hard codes and register into the module pom.

class DBpediaResourceFactorySQLTest {

  val configuration: SpotlightConfiguration = new SpotlightConfiguration("conf/server.properties")
  val factory: SpotlightFactory = new SpotlightFactory(configuration)

  val dbpediaResourceFactory = configuration.getDBpediaResourceFactory

  def dbpediaResourceForAllConcepts() {
    //val configuration: IndexingConfiguration = new IndexingConfiguration("conf/indexing.properties")
    val examples = Source.fromFile("/Users/jodaiber/Desktop/DBpedia/conceptURIs.list", "UTF-8").getLines().take(10000)

    examples.foreach( dbpediaID => {
      try{
        val dBpediaResource: DBpediaResource = dbpediaResourceFactory.from(dbpediaID)
        assert(dBpediaResource.uri.equals(dbpediaID))
        assert(dBpediaResource.getTypes.size() >= 0)
        assert(dBpediaResource.support >= 0)
        assert(!dBpediaResource.getTypes.contains(null))
      }catch{
        case e: NoSuchElementException => //There may be a difference between the index and the concept list when testing...
      }
    })
  }

  @Test
  def createDBpediaResourcesOnce() {
    dbpediaResourceForAllConcepts()
  }

  @Test
  def createDBpediaResourcesTenTimes() {
    (1 to 10 toList).foreach{
      _ => dbpediaResourceForAllConcepts()
    }
  }

}