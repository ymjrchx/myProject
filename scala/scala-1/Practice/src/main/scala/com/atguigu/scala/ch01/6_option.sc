val scores = Map("Alice" -> 1729, "Fred" -> 42)

scores.get("Alicqe") match {
  case Some(score111) => println(score111)
  case None => println("No score")
}

val alicesScore = scores.get("Alice")
if (alicesScore.isEmpty) { println("No score")
} else println(alicesScore.get)

println(alicesScore.getOrElse("No score"))

println(scores.getOrElse("Alic33e", "No score"))

for (score <- scores.get("Alice")) println(score)

scores.get("Alice").foreach(println _)