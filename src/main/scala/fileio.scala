// /////////////////////////////////////////// //
// Fureteur - https://github.com/gip/fureteur  //
// /////////////////////////////////////////// //

/*
package fureteur.fileio

import fureteur.sync._
import fureteur.data._
import fureteur.control._
import fureteur.config._
*/

// Taking URLs in batches from a file
class fileBatchPrefetcher(config: Config, control: Control)
  extends genericBatchProducer[Data](config.getInt("batch_size"),
    config.getInt("threshold_in_batches"),
    config.getLongOption("timeout_ms"), control) {

  val file = config("file_name")

  log.info("Opening " + file)
  val data = scala.io.Source.fromFile(file).getLines.toArray
  var index = 0
  var batch = 0

  override def getBatch(sz: Int): Option[List[Data]] = {
    if (index > data.size) {
      return None
    }
    index += sz
    batch += 1
    val listURL = data.slice(index - sz, index).toList
    log.info("Fetched " + listURL.length.toString + " entrie(s) from " + file)
    val d = Data.empty
    Some(listURL map (e => d ++ List(("fetch_url", e), ("batch", batch.toString))))
  }
}


class fileBatchWriteback(config: Config, control: Control) extends genericBatchReseller[Data](control) {

  //val log = Logging(context.system, this)
  val fname = config("file_name")
  val file = new java.io.FileWriter(fname)

  def resell(batch: List[Data]) = {
    log.info("Writing " + batch.length.toString + " entrie(s) to " + file)
    def doit(b: List[Data]): Unit = {
      b match {
        case x :: xs => {
          val s = x.toJson + "\n";
//          log.info("Writing " + s + " contain to " + file)
          file.write(s);
          doit(xs)
        }
        case Nil =>
      }
    }
    doit(batch)
  }

}