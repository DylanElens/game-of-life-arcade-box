up: main.java 
	javac $(shell find . -name "*.java")
	java main
	$(RM) *.class

