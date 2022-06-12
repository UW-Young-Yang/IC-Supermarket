run: compile

compile: Main.class
	@java --module-path JavaFXForLinux/lib --add-modules javafx.controls Main
	
Main.class:
	@javac --module-path JavaFXForLinux/lib --add-modules javafx.controls Main.java

test: TestSuperMarket.class TestCommodity.class TestHashTable.class
	@java --module-path JavaFXForLinux/lib --add-modules javafx.controls -jar junit5.jar --class-path . --scan-classpath --details tree

TestSuperMarket.class:
	@javac --module-path JavaFXForLinux/lib --add-modules javafx.controls -cp .:junit5.jar TestSuperMarket.java

TestCommodity.class:
	@javac -cp .:junit5.jar TestCommodity.java

TestHashTable.class:
	@javac -cp .:junit5.jar TestHashTable.java
clean:
	$(RM) *.class
