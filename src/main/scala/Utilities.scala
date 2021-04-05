object Utilities extends App {

  // got the words here: http://www.gwicks.net/dictionaries.htm (UK English)
  def englishWords(file: String = "./src/Resources/englishWords.txt") : List[String] = {
    val source = io.Source.fromFile(file)
    val words: List[String] = source.getLines.toList
    source.close()
    words
  }

  // Return a random word
  def randomWord(words : List[String]) : String = {
    words( scala.util.Random.nextInt(words.length) )
  }
  // TODO ADD A SEED FOR TESTING

  // Split a word into separate letters
  def wordSplit(word : String) : List[Char] = {
    word.toList
  }

  // Join the list of characters together with a space in-between.
  def wordJoin(wordlist : List[Char]) : String = {
    wordlist.mkString(" ")
    //other option: wordlist.mkString(" ").toUpperCase
  }

  // Set of upper case letters.
  // TODO Check if can be done only with the previous def comment
  def alphaSet : Set[Char] = {
    ('A' to 'Z').toSet
  }

  // Generate a new guess list based on letter, current matches and actual word.
  def applyGuess(letter : Char, guesslist : List[Char], hanglist : List[Char]) : List[Char] = {
    guesslist.zip(hanglist).map({case(g,h) => if (letter == h) h else g})
  }
}
