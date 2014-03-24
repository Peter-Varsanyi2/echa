javac -cp src;src\slf4j-api-1.7.6.jar %1/BotStarter.java
java -cp src;src\slf4j-api-1.7.6.jar;src\logback-core-1.1.1.jar;src\logback-classic-1.1.1.jar bot.BotStarter
