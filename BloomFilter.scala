import scala.io._
import scala.util.hashing.MurmurHash3
import scala.math._

class BloomFilter(private val size: Int, private val numHashes: Int) {
  private val bitArray: Array[Boolean] = Array.fill(size)(false)

  def add(word: String): Unit = {//Add word to bloom filter with use of given number of hashes
    for (i <- 0 until numHashes) {
      val hash = hashString(word, i)
      bitArray(abs(hash % size)) = true
    }
  }

  def mightContain(word: String): Boolean = {//Checking if the word is in text represented by Bloom Filter
    for (i <- 0 until numHashes) {
      val hash = hashString(word, i)
      if (!bitArray(abs(hash % size)))
        return false
    }
    true
  }

  private def hashString(word: String, seed: Int): Int = {//Getting Hashes
    MurmurHash3.stringHash(word, seed)
  }

  def HammingWeight(): Int ={//Hamming weight of filter
    var c = 0
    for(i<- 0 until size)
      if(bitArray(i))
        c+=1
    c
  }

  // Method to get the number of hashes used
  def getNumHashes: Int = numHashes

  // Method to get the size of the bit array
  def getSize: Int = size
}




object Difference {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("Usage: scala CountWordsBloomFilter <file1> <file2>")
      System.exit(1)
    }
    //Reading files
    val file = args(0)
    val filetwo = args(1)

    val fileSource = Source.fromFile(file)
    val fileSourcetwo = Source.fromFile(filetwo)

    val words = fileSource.mkString.toLowerCase().split("\\W+")
    val wordstwo = fileSourcetwo.mkString.toLowerCase().split("\\W+")

    fileSource.close()
    fileSourcetwo.close()
    //Building Bloom filter for second file
    val bloomFilter = new BloomFilter(wordstwo.length *2, 5)
    wordstwo.foreach(bloomFilter.add)
    //Variables to check if Filter works
    var result = Array[String]()
    //Counting words that are in first file and are not in second, and false positives
    for (word <- words) {
      if (!bloomFilter.mightContain(word)) {
        result = result :+ word
      }
    }

    //Difference between the texts
    println(result.toList)

  }
}


object Counting{
  def main(args: Array[String]): Unit = {
    if (args.length <1)
    {
      print("Give filename.")
      System.exit(1)
    }

    //Number of Hash functions and size of a bloom filter
    val k = 6

    //Size of a Bloom Filter, in this case 16KB.
    val m = pow(2,17).toInt
    
    //Reading File.
    val file = args(0)
    val fileSource = Source.fromFile(file)
    val words = fileSource.mkString.toLowerCase().split("\\W+")
    fileSource.close()


    //Creating proper Bloom Filter.
    val bFilter = new BloomFilter(m,k)
    words.foreach(bFilter.add)

    //Calculating number of distinct words in given file.
    val XA = bFilter.HammingWeight()
    val A = -m.toDouble/k.toDouble*log(1-XA.toDouble/m.toDouble)


    println(s"Number of distinct words in a text: ${A.toInt}")
  }
}

